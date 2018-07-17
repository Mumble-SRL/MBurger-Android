package mumble.mburger.sdk.Common.MBApiManager;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import mumble.mburger.R;
import mumble.mburger.sdk.Common.MBConstants.MBUserConstants;
import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Static method class that wraps up web API admin calls using POST connection and OKHTTP library
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBApiManagerOKHTTPPost {

    public static Map<String, Object> callApi(Context context, String api, RequestBody requestBody, boolean payload) {
        Map<String, Object> map = new HashMap<>();
        Request request = null;
        if(MBUserConstants.devMode){
            request = new Request.Builder()
                    .url(MBApiManagerConfig.endpoint_dev + api)
                    .header("X-Nooko-Token", MBUserConstants.apiKey)
                    .header("X-Nooko-Version", "2")
                    .header("Accept", "application/json")
                    .post(requestBody)
                    .build();
        }
        else{
            request = new Request.Builder()
                    .url(MBApiManagerConfig.endpoint + api)
                    .header("X-Nooko-Token", MBUserConstants.apiKey)
                    .header("X-Nooko-Version", "2")
                    .header("Accept", "application/json")
                    .post(requestBody)
                    .build();
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Call call = client.newCall(request);
        Response response = null;

        try {
            response = call.execute();
            if (response.isSuccessful()) {
                MBApiManagerUtils.doLogging("NOOKO3 RESPONSE " + Integer.toString(response.code()));
                MBApiManagerUtils.doLogging("NOOKO3 RESPONSE " + response.body().toString());
                map.put(MBApiManagerConfig.AM_RESULT, MBApiManagerConfig.RESULT_OK);
                JSONTokener tokener = new JSONTokener(response.body().string());
                JSONObject jObj = new JSONObject(tokener);
                setResponseInMap(jObj, map, payload);
            } else {
                String body = response.body().string();
                MBApiManagerUtils.doLogging("NOOKO3 ERROR" + body);
                map.put(MBApiManagerConfig.AM_ERROR, context.getString(R.string.internal_error));
                map.put(MBApiManagerConfig.AM_RESULT, MBApiManagerConfig.COMMON_INTERNAL_ERROR);
            }
        } catch (IOException e) {
            MBApiManagerUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
            map.put(MBApiManagerConfig.AM_ERROR, context.getString(R.string.internal_error));
            map.put(MBApiManagerConfig.AM_RESULT, MBApiManagerConfig.COMMON_INTERNAL_ERROR);
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

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

}
