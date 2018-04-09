package mumble.nooko3.sdk.NKControllers.NKApiManager;
import android.content.ContentValues;
import android.content.Context;

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
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKControllers.NKCachingHelper;
import mumble.nooko3.sdk.NKConstants.NKUserConstants;
import mumble.nooko3.R;

/**
 * Static method class that wraps up web API calls and handles errors returning them to the user
 *
 * @author  Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKAPIManager3 {

    /**Call api or check for cache if internet connection is not available*/
    public static Map<String, Object> callApi(Context context, String api, ContentValues data, int type, boolean payload) {
        Map<String, Object> map = new HashMap<>();
        NKCachingHelper helper3 = new NKCachingHelper(context);
        if (NKApiManagerUtils.isNetworkAvailable(context)) {
            NKApiManagerUtils.installCertificates(context);
            map = callForData(context, api, data, type, payload);
            if (map != null) {
                if (NKUserConstants.cachingEnabled) {
                    if (payload) {
                        if (NKApiManagerUtils.hasMapOkResults(map, true)) {
                            String api_params = NKApiManagerUtils.createApiValuesString(api, data);
                            helper3.addRequest(api_params, (String) map.get(NKApiManagerConfig.AM_RESPONSE));
                        }
                    }
                }
            } else {
                if (!NKApiManagerUtils.isNetworkAvailable(context)) {
                    map.put(NKApiManagerConfig.AM_RESULT, NKApiManagerConfig.COMMON_INTERNAL_ERROR);
                } else {
                    map.put(NKApiManagerConfig.AM_RESULT, NKApiManagerConfig.COMMON_INTERNAL_ERROR);
                }
            }
        } else {
            if (NKUserConstants.cachingEnabled) {
                addNecessaryPostData(context, data);
                String api_params = NKApiManagerUtils.createApiValuesString(api, data);
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
                        map.put(NKApiManagerConfig.AM_RESULT, NKApiManagerConfig.COMMON_NOINTERNET);
                    }
                } else {
                    map.put(NKApiManagerConfig.AM_RESULT, NKApiManagerConfig.COMMON_NOINTERNET);
                }
            } else {
                map.put(NKApiManagerConfig.AM_RESULT, NKApiManagerConfig.COMMON_NOINTERNET);
            }
        }
        return map;
    }

    /**Call api and retrieve data from web, if an error is present it will be saved inside the returning Map*/
    private static Map<String, Object> callForData(Context context, String api, ContentValues postData, int mode, boolean payload) {
        StringBuilder builder;
        Map<String, Object> map = new HashMap<>();
        HttpURLConnection urlConnection = null;
        BufferedReader rd;

        try {
            switch (mode) {
                case NKApiManagerConfig.MODE_GET:
                    urlConnection = initializeGetConnection(context, api, postData);
                    break;

                case NKApiManagerConfig.MODE_POST:
                    urlConnection = initializePostDeleteConnection(context, api, postData, "POST");
                    break;

                case NKApiManagerConfig.MODE_DELETE:
                    urlConnection = initializePostDeleteConnection(context, api, postData, "DELETE");
                    break;

                case NKApiManagerConfig.MODE_PUT:
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
            map.put(NKApiManagerConfig.AM_RESULT, NKApiManagerConfig.COMMON_TIMEOUT);
            NKApiManagerUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
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
                        NKApiManagerUtils.doLogging("NOOKO3 ERROR" + builder);
                        map.put(NKApiManagerConfig.AM_ERROR, context.getString(R.string.internal_error));
                        map.put(NKApiManagerConfig.AM_RESULT, NKApiManagerConfig.COMMON_INTERNAL_ERROR);

                        if (iResponse == NKApiManagerConfig.RESULT_REFRESH) {
                            map.put(NKApiManagerConfig.AM_RESULT, NKApiManagerConfig.RESULT_REFRESH);
                        } else {
                            map.put(NKApiManagerConfig.AM_RESULT, NKApiManagerConfig.COMMON_INTERNAL_ERROR);
                            NKApiManagerUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
                            e.printStackTrace();
                        }
                    } else {
                        map.put(NKApiManagerConfig.AM_RESULT, NKApiManagerConfig.COMMON_INTERNAL_ERROR);
                        NKApiManagerUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
                        e.printStackTrace();
                    }
                } else {
                    map.put(NKApiManagerConfig.AM_RESULT, NKApiManagerConfig.COMMON_INTERNAL_ERROR);
                    NKApiManagerUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
                    e.printStackTrace();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (JSONException e) {
            map.put(NKApiManagerConfig.AM_RESULT, NKApiManagerConfig.COMMON_INTERNAL_ERROR);
            NKApiManagerUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
            e.printStackTrace();
        }
        return map;
    }

    /**Takes the API response and encapsulate it in a JSON, retieving payload and result*/
    private static Map<String, Object> parseResponse(HttpURLConnection urlConnection, StringBuilder builder,
                                                    boolean payload) throws IOException, JSONException {
        Map<String, Object> map = new HashMap<>();
        int iResponse = urlConnection.getResponseCode();
        NKApiManagerUtils.doLogging("NOOKO3 RESPONSE " + Integer.toString(iResponse));
        NKApiManagerUtils.doLogging("NOOKO3 RESPONSE " + builder.toString());
        if (NKApiManagerUtils.checkResponse(iResponse)) {
            String s = builder.toString().trim();
            JSONTokener tokener = new JSONTokener(s);
            JSONObject jObj = new JSONObject(tokener);
            setResponseInMap(jObj, map, payload);
        } else {
            map.put(NKApiManagerConfig.AM_RESULT, iResponse);
        }

        map.put(NKApiManagerConfig.AM_RESPONSE, builder.toString());
        return map;
    }

    private static void setResponseInMap(JSONObject jResp, Map<String, Object> map, boolean payload) throws JSONException {
        int status_code = jResp.getJSONObject("response").getInt("status_code");
        if (status_code == NKApiManagerConfig.STATUS_CODE_OK) {
            map.put(NKApiManagerConfig.AM_RESULT, NKApiManagerConfig.RESULT_OK);
            if (payload) {
                map.put(NKApiManagerConfig.AM_PAYLOAD, jResp.getJSONObject("response").toString());
            }
        } else {
            String error = jResp.getJSONObject("response").getString("message_localized");
            map.put(NKApiManagerConfig.AM_RESULT, status_code);
            map.put(NKApiManagerConfig.AM_ERROR, error);
        }
    }

    /**Initialize connection with method (POST, PUT, DELETE) with all data inside the ContentValues*/
    private static HttpsURLConnection initializePostDeleteConnection(Context context, String api, ContentValues postData, String method)
            throws IOException {
        URL url = new URL(NKApiManagerConfig.endpoint + api);
        addNecessaryPostData(context, postData);

        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                return hv.verify(NKApiManagerConfig.SERVER_HOSTNAME, session);
            }
        };

        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestMethod(method);
        urlConnection.setHostnameVerifier(hostnameVerifier);
        //urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConnection.setRequestProperty("X-Nooko-Token", NKUserConstants.apiKey);
        urlConnection.setRequestProperty("X-Nooko-Version", "2");
        urlConnection.setRequestProperty("Accept", "application/json");

        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(false);

        OutputStream os = urlConnection.getOutputStream();

        DataOutputStream writer = new DataOutputStream(urlConnection.getOutputStream());
        writer.writeBytes(NKApiManagerUtils.getQuery(postData));
        writer.flush();
        writer.close();
        os.close();

        return urlConnection;
    }

    /**Initialize a GET connection with all data inside the ContentValues*/
    private static HttpsURLConnection initializeGetConnection(Context context, String api, ContentValues postData) throws IOException{
        URL url = new URL(NKApiManagerConfig.endpoint + api + NKApiManagerUtils.getGETQuery(postData));
        addNecessaryPostData(context, postData);

        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                return hv.verify(NKApiManagerConfig.SERVER_HOSTNAME, session);
            }
        };

        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setHostnameVerifier(hostnameVerifier);
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(false);
        urlConnection.setRequestMethod("GET");
        //urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConnection.setRequestProperty("X-Nooko-Token", NKUserConstants.apiKey);
        urlConnection.setRequestProperty("X-Nooko-Version", "2");
        urlConnection.setRequestProperty("Accept", "application/json");
        return urlConnection;
    }

    /**Adds basic data common to all calls, handled automatically so the developer has not to worry about it*/
    private static void addNecessaryPostData(Context context, ContentValues postData) {
    }

}
