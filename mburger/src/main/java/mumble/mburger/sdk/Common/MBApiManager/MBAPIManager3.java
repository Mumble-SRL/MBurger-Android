package mumble.mburger.sdk.Common.MBApiManager;

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
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import mumble.mburger.R;
import mumble.mburger.sdk.Common.MBCachingHelper;
import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.Common.MBConstants.MBUserConstants;

/**
 * Static method class that wraps up web API calls and handles errors returning them to the user
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBAPIManager3 {

    /**
     * Call api or check for cache if internet connection is not available
     */
    public static Map<String, Object> callApi(Context context, String api, ContentValues data, int type,
                                              boolean payload, boolean pushApi) {
        Map<String, Object> map = new HashMap<>();
        MBCachingHelper helper3 = new MBCachingHelper(context);
        if (MBApiManagerUtils.isNetworkAvailable(context)) {
            MBApiManagerUtils.installCertificates(context);
            map = callForData(context, api, data, type, payload, pushApi);
            if (map != null) {
                if (MBUserConstants.cachingEnabled) {
                    if (payload) {
                        if (MBApiManagerUtils.hasMapOkResults(map, true)) {
                            String api_params = MBApiManagerUtils.createApiValuesString(api, data);
                            helper3.addRequest(api_params, (String) map.get(MBApiManagerConfig.AM_RESPONSE));
                        }
                    }
                }
            } else {
                if (!MBApiManagerUtils.isNetworkAvailable(context)) {
                    map.put(MBApiManagerConfig.AM_RESULT, MBApiManagerConfig.COMMON_INTERNAL_ERROR);
                } else {
                    map.put(MBApiManagerConfig.AM_RESULT, MBApiManagerConfig.COMMON_INTERNAL_ERROR);
                }
            }
        } else {
            if (MBUserConstants.cachingEnabled) {
                addNecessaryPostData(context, data);
                String api_params = MBApiManagerUtils.createApiValuesString(api, data);
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
                        map.put(MBApiManagerConfig.AM_RESULT, MBApiManagerConfig.COMMON_NOINTERNET);
                    }
                } else {
                    map.put(MBApiManagerConfig.AM_RESULT, MBApiManagerConfig.COMMON_NOINTERNET);
                }
            } else {
                map.put(MBApiManagerConfig.AM_RESULT, MBApiManagerConfig.COMMON_NOINTERNET);
            }
        }
        return map;
    }

    /**
     * Call api and retrieve data from web, if an error is present it will be saved inside the returning Map
     */
    private static Map<String, Object> callForData(Context context, String api, ContentValues postData, int mode,
                                                   boolean payload, boolean pushApi) {
        StringBuilder builder;
        Map<String, Object> map = new HashMap<>();
        HttpURLConnection urlConnection = null;
        BufferedReader rd;

        try {
            switch (mode) {
                case MBApiManagerConfig.MODE_GET:
                    urlConnection = initializeGetConnection(context, api, postData, pushApi);
                    break;

                case MBApiManagerConfig.MODE_POST:
                    urlConnection = initializePostDeleteConnection(context, api, postData, "POST", pushApi);
                    break;

                case MBApiManagerConfig.MODE_DELETE:
                    urlConnection = initializePostDeleteConnection(context, api, postData, "DELETE", pushApi);
                    break;

                case MBApiManagerConfig.MODE_PUT:
                    urlConnection = initializePostDeleteConnection(context, api, postData, "PUT", pushApi);
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
            map.put(MBApiManagerConfig.AM_RESULT, MBApiManagerConfig.COMMON_TIMEOUT);
            MBApiManagerUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
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
                        MBApiManagerUtils.doLogging("NOOKO3 ERROR" + builder);
                        map.put(MBApiManagerConfig.AM_ERROR, context.getString(R.string.internal_error));
                        map.put(MBApiManagerConfig.AM_RESULT, MBApiManagerConfig.COMMON_INTERNAL_ERROR);

                        if (iResponse == MBApiManagerConfig.RESULT_REFRESH) {
                            map.put(MBApiManagerConfig.AM_RESULT, MBApiManagerConfig.RESULT_REFRESH);
                        } else {
                            map.put(MBApiManagerConfig.AM_RESULT, MBApiManagerConfig.COMMON_INTERNAL_ERROR);
                            MBApiManagerUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
                            e.printStackTrace();
                        }
                    } else {
                        map.put(MBApiManagerConfig.AM_RESULT, MBApiManagerConfig.COMMON_INTERNAL_ERROR);
                        MBApiManagerUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
                        e.printStackTrace();
                    }
                } else {
                    map.put(MBApiManagerConfig.AM_RESULT, MBApiManagerConfig.COMMON_INTERNAL_ERROR);
                    MBApiManagerUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
                    e.printStackTrace();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (JSONException e) {
            map.put(MBApiManagerConfig.AM_RESULT, MBApiManagerConfig.COMMON_INTERNAL_ERROR);
            MBApiManagerUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Takes the API response and encapsulate it in a JSON, retieving payload and result
     */
    private static Map<String, Object> parseResponse(HttpURLConnection urlConnection, StringBuilder builder,
                                                     boolean payload) throws IOException, JSONException {
        Map<String, Object> map = new HashMap<>();
        int iResponse = urlConnection.getResponseCode();
        MBApiManagerUtils.doLogging("NOOKO3 RESPONSE " + Integer.toString(iResponse));
        MBApiManagerUtils.doLogging("NOOKO3 RESPONSE " + builder.toString());
        if (MBApiManagerUtils.checkResponse(iResponse)) {
            String s = builder.toString().trim();
            JSONTokener tokener = new JSONTokener(s);
            JSONObject jObj = new JSONObject(tokener);
            setResponseInMap(jObj, map, payload);
        } else {
            map.put(MBApiManagerConfig.AM_RESULT, iResponse);
        }

        map.put(MBApiManagerConfig.AM_RESPONSE, builder.toString());
        return map;
    }

    private static void setResponseInMap(JSONObject jResp, Map<String, Object> map, boolean payload) throws JSONException {
        int status_code = jResp.getJSONObject("response").getInt("status_code");
        if (status_code == MBApiManagerConfig.STATUS_CODE_OK) {
            map.put(MBApiManagerConfig.AM_RESULT, MBApiManagerConfig.RESULT_OK);
            if (payload) {
                map.put(MBApiManagerConfig.AM_PAYLOAD, jResp.getJSONObject("response").toString());
            }
        } else {
            String error = jResp.getJSONObject("response").getString("message_localized");
            map.put(MBApiManagerConfig.AM_RESULT, status_code);
            map.put(MBApiManagerConfig.AM_ERROR, error);
        }
    }

    /**
     * Initialize connection with method (POST, PUT, DELETE) with all data inside the ContentValues
     */
    private static HttpsURLConnection initializePostDeleteConnection(Context context, String api, ContentValues postData,
                                                                     String method, final boolean pushApi)
            throws IOException {

        HttpsURLConnection urlConnection = null;
        URL url = null;
        HostnameVerifier hostnameVerifier = null;
        if (MBUserConstants.devMode) {
            if (pushApi) {
                url = new URL(MBApiManagerConfig.endpoint_push + api);
            } else {
                url = new URL(MBApiManagerConfig.endpoint_dev + api);
            }
            hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                    if (pushApi) {
                        return hv.verify(MBApiManagerConfig.SERVER_HOSTNAME_PUSH, session);
                    } else {
                        return hv.verify(MBApiManagerConfig.SERVER_HOSTNAME_DEV, session);
                    }
                }
            };
        } else {
            if (pushApi) {
                url = new URL(MBApiManagerConfig.endpoint_push + api);
            } else {
                url = new URL(MBApiManagerConfig.endpoint + api);
            }
            hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                    if (pushApi) {
                        return hv.verify(MBApiManagerConfig.SERVER_HOSTNAME_PUSH, session);
                    } else {
                        return hv.verify(MBApiManagerConfig.SERVER_HOSTNAME, session);
                    }
                }
            };
        }

        addNecessaryPostData(context, postData);
        urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestMethod(method);
        urlConnection.setHostnameVerifier(hostnameVerifier);
        //urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConnection.setRequestProperty("X-MBurger-Token", MBUserConstants.apiKey);
        urlConnection.setRequestProperty("X-MBurger-Version", "2");
        urlConnection.setRequestProperty("Accept", "application/json");
        if (MBCommonMethods.hasLoggedIn(context)) {
            urlConnection.setRequestProperty("Authorization", "Bearer " + MBCommonMethods.getAccessToken(context));
        }

        if(pushApi){
            urlConnection.setRequestProperty("X-MPush-Token", MBUserConstants.pushKey);
        }

        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(false);

        OutputStream os = urlConnection.getOutputStream();

        DataOutputStream writer = new DataOutputStream(urlConnection.getOutputStream());
        writer.writeBytes(MBApiManagerUtils.getQuery(postData));
        writer.flush();
        writer.close();
        os.close();

        return urlConnection;
    }

    /**
     * Initialize a GET connection with all data inside the ContentValues
     */
    private static HttpsURLConnection initializeGetConnection(Context context, String api,
                                                              ContentValues postData, final boolean pushApi) throws IOException {
        addNecessaryPostData(context, postData);

        HttpsURLConnection urlConnection = null;
        URL url = null;
        HostnameVerifier hostnameVerifier = null;
        if (MBUserConstants.devMode) {
            if (pushApi) {
                url = new URL(MBApiManagerConfig.endpoint_push + api + MBApiManagerUtils.getGETQuery(postData));
            } else {
                url = new URL(MBApiManagerConfig.endpoint_dev + api + MBApiManagerUtils.getGETQuery(postData));
            }
            hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                    if (pushApi) {
                        return hv.verify(MBApiManagerConfig.SERVER_HOSTNAME_PUSH, session);
                    } else {
                        return hv.verify(MBApiManagerConfig.SERVER_HOSTNAME_DEV, session);
                    }
                }
            };
        } else {
            if (pushApi) {
                url = new URL(MBApiManagerConfig.endpoint_push + api + MBApiManagerUtils.getGETQuery(postData));
            } else {
                url = new URL(MBApiManagerConfig.endpoint + api + MBApiManagerUtils.getGETQuery(postData));
            }
            hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                    if (pushApi) {
                        return hv.verify(MBApiManagerConfig.SERVER_HOSTNAME_PUSH, session);
                    } else {
                        return hv.verify(MBApiManagerConfig.SERVER_HOSTNAME, session);
                    }
                }
            };
        }

        addNecessaryPostData(context, postData);
        urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setHostnameVerifier(hostnameVerifier);
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(false);
        urlConnection.setRequestMethod("GET");
        //urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        urlConnection.setRequestProperty("X-MBurger-Token", MBUserConstants.apiKey);
        urlConnection.setRequestProperty("X-MBurger-Version", "2");
        urlConnection.setRequestProperty("Accept", "application/json");
        if (MBCommonMethods.hasLoggedIn(context)) {
            urlConnection.setRequestProperty("Authorization", "Bearer " + MBCommonMethods.getAccessToken(context));
        }

        if(pushApi){
            urlConnection.setRequestProperty("X-MPush-Token", MBUserConstants.pushKey);
        }

        return urlConnection;
    }

    /**
     * Adds basic data common to all calls, handled automatically so the developer has not to worry about it
     */
    private static void addNecessaryPostData(Context context, ContentValues postData) {
        postData.put("device_id", Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
        postData.put("os", "android");
    }

}
