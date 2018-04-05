package mumble.nooko3.sdk.NControllers.NApiManager;
import android.content.ContentValues;
import android.content.Context;
import android.provider.Settings;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import mumble.nooko3.sdk.NConstants.Const;
import mumble.nooko3.sdk.NControllers.CachingHelper;
import mumble.nooko3.sdk.NConstants.UserConst;
import mumble.nooko3.R;

/**
 * Static method class that wrps up web API calls and handles errors returning them to the user
 *
 * @author  Enrico Ori
 * @version {@value Const#version}
 */
public class NAPIManager3 {

    /**Call api or check for cache*/
    public static Map<String, Object> callApi(Context context, String api, ContentValues data, int type, boolean payload) {
        Map<String, Object> map = new HashMap<>();
        CachingHelper helper3 = new CachingHelper(context);
        if (NAMUtils.isNetworkAvailable(context)) {
            NAMUtils.installCertificates(context);
            map = callForData(context, api, data, type, payload);
            if (map != null) {
                if (UserConst.cachingEnabled) {
                    if (payload) {
                        if (NAMUtils.hasMapOkResults(map, true)) {
                            String api_params = NAMUtils.createApiValuesString(api, data);
                            helper3.addRequest(api_params, (String) map.get(NAMCONF.AM_PAYLOAD));
                        }
                    }
                }
            } else {
                if (!NAMUtils.isNetworkAvailable(context)) {
                    map.put(NAMCONF.AM_RESULT, NAMCONF.COMMON_INTERNAL_ERROR);
                } else {
                    map.put(NAMCONF.AM_RESULT, NAMCONF.COMMON_INTERNAL_ERROR);
                }
            }
        } else {
            if (UserConst.cachingEnabled) {
                addNecessaryPostData(context, data);
                String api_params = NAMUtils.createApiValuesString(api, data);
                if (helper3.isDataAlreadyInDBorNot(api_params)) {
                    String res3 = helper3.getRequestResponse(api_params);
                    if (res3 != null) {
                        try {
                            JSONTokener tokener = new JSONTokener(res3);
                            JSONObject jObj = new JSONObject(tokener);
                            setResponseInMap(jObj, map, payload);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        map.put(NAMCONF.AM_RESULT, NAMCONF.COMMON_NOINTERNET);
                    }
                } else {
                    map.put(NAMCONF.AM_RESULT, NAMCONF.COMMON_NOINTERNET);
                }
            } else {
                map.put(NAMCONF.AM_RESULT, NAMCONF.COMMON_NOINTERNET);
            }
        }
        return map;
    }

    /**Call api and retrieve data*/
    private static Map<String, Object> callForData(Context context, String api, ContentValues postData, int mode, boolean payload) {
        StringBuilder builder;
        Map<String, Object> map = new HashMap<>();
        HttpURLConnection urlConnection = null;
        BufferedReader rd;

        try {
            switch (mode) {
                case NAMCONF.MODE_GET:
                    urlConnection = initializeGetConnection(context, api, postData);
                    break;

                case NAMCONF.MODE_POST:
                    urlConnection = initializePostDeleteConnection(context, api, postData, "POST");
                    break;

                case NAMCONF.MODE_DELETE:
                    urlConnection = initializePostDeleteConnection(context, api, postData, "DELETE");
                    break;

                case NAMCONF.MODE_PUT:
                    urlConnection = initializePostDeleteConnection(context, api, postData, "PUT");
                    break;
            }

            InputStream is = urlConnection.getInputStream();
            rd = new BufferedReader(new InputStreamReader(is));

            builder = new StringBuilder();
            for (String line; (line = rd.readLine()) != null; ) {
                builder.append(line).append("\n");
            }

            map = parseResponse(urlConnection, builder, payload);
        } catch (SocketTimeoutException e) {
            map.put(NAMCONF.AM_RESULT, NAMCONF.COMMON_TIMEOUT);
            NAMUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
            e.printStackTrace();
        } catch (IOException e) {
            try {
                if (urlConnection != null) {
                    int iResponse = urlConnection.getResponseCode();
                    InputStream error = urlConnection.getErrorStream();
                    if (error != null) {
                        rd = new BufferedReader(new InputStreamReader(error));

                        builder = new StringBuilder();
                        for (String line; (line = rd.readLine()) != null; ) {
                            builder.append(line).append("\n");
                        }

                        map = new HashMap<>();
                        NAMUtils.doLogging("NOOKO3 ERROR" + builder);
                        map.put(NAMCONF.AM_ERROR, context.getString(R.string.internal_error));
                        map.put(NAMCONF.AM_RESULT, NAMCONF.COMMON_INTERNAL_ERROR);

                        if (iResponse == NAMCONF.RESULT_REFRESH) {
                            map.put(NAMCONF.AM_RESULT, NAMCONF.RESULT_REFRESH);
                        } else {
                            map.put(NAMCONF.AM_RESULT, NAMCONF.COMMON_INTERNAL_ERROR);
                            NAMUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
                            e.printStackTrace();
                        }
                    } else {
                        map.put(NAMCONF.AM_RESULT, NAMCONF.COMMON_INTERNAL_ERROR);
                        NAMUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
                        e.printStackTrace();
                    }
                } else {
                    map.put(NAMCONF.AM_RESULT, NAMCONF.COMMON_INTERNAL_ERROR);
                    NAMUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
                    e.printStackTrace();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (JSONException e) {
            map.put(NAMCONF.AM_RESULT, NAMCONF.COMMON_INTERNAL_ERROR);
            NAMUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
            e.printStackTrace();
        }
        return map;
    }

    /**Takes the API response and encapsulate it in a JSON, retieving payload and result*/
    private static Map<String, Object> parseResponse(HttpURLConnection urlConnection, StringBuilder builder,
                                                    boolean payload) throws IOException, JSONException {
        Map<String, Object> map = new HashMap<>();
        int iResponse = urlConnection.getResponseCode();
        NAMUtils.doLogging("NOOKO3 RESPONSE " + Integer.toString(iResponse));
        NAMUtils.doLogging("NOOKO3 RESPONSE " + builder.toString());
        if (NAMUtils.checkResponse(iResponse)) {
            String s = builder.toString().trim();
            JSONTokener tokener = new JSONTokener(s);
            JSONObject jObj = new JSONObject(tokener);
            setResponseInMap(jObj, map, payload);
        } else {
            map.put(NAMCONF.AM_RESULT, iResponse);
        }

        return map;
    }

    private static void setResponseInMap(JSONObject jResp, Map<String, Object> map, boolean payload) throws JSONException {
        int status_code = jResp.getJSONObject("response").getInt("status_code");
        if (status_code == NAMCONF.STATUS_CODE_OK) {
            map.put(NAMCONF.AM_RESULT, NAMCONF.RESULT_OK);
            if (payload) {
                map.put(NAMCONF.AM_PAYLOAD, jResp.getJSONObject("response").toString());
            }
        } else {
            String error = jResp.getJSONObject("response").getString("message_localized");
            map.put(NAMCONF.AM_RESULT, status_code);
            map.put(NAMCONF.AM_ERROR, error);
        }
    }

    /**Initialize connection with method (POST, PUT, DELETE)*/
    private static HttpURLConnection initializePostDeleteConnection(Context context, String api, ContentValues postData, String method)
            throws IOException {
        URL url = new URL(NAMCONF.endpoint + api);
        addNecessaryPostData(context, postData);

        /*HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                return hv.verify(AMCONF.SERVER_HOSTNAME, session);
            }
        };*/

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(method);
        //urlConnection.setHostnameVerifier(hostnameVerifier);
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(false);

        OutputStream os = urlConnection.getOutputStream();

        DataOutputStream writer = new DataOutputStream(urlConnection.getOutputStream());
        writer.writeBytes(NAMUtils.getQuery(postData));
        writer.flush();
        writer.close();
        os.close();

        return urlConnection;
    }

    /**Initialize GET connection*/
    private static HttpURLConnection initializeGetConnection(Context context, String api, ContentValues postData) throws IOException{
        URL url = new URL(NAMCONF.endpoint + api + NAMUtils.getGETQuery(postData));
        addNecessaryPostData(context, postData);

        /*HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                return hv.verify(AMCONF.SERVER_HOSTNAME, session);
            }
        };*/

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        //urlConnection.setHostnameVerifier(hostnameVerifier);
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(false);
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        return urlConnection;
    }

    /**Adds basic data common to all calls, handled automatically so the developer has not to worry about it*/
    private static void addNecessaryPostData(Context context, ContentValues postData) {
        postData.put("device_id", Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
        postData.put("os", NAMCONF.OS);
        postData.put("locale", Locale.getDefault().getLanguage());
        postData.put("version", NAMCONF.API_VERSION);
        postData.put("api_key", NAMCONF.API_KEY);
    }

}
