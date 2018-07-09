package mumble.nooko3.sdk.Common.NKApiManager;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import mumble.nooko3.R;
import mumble.nooko3.sdk.Common.NKConstants.NKConstants;
import mumble.nooko3.sdk.Common.NKConstants.NKUserConstants;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Static method class that wraps up web API admin calls using POST connection and OKHTTP library
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKApiManagerOKHTTPPost {

    public static Map<String, Object> callApi(Context context, String api, RequestBody requestBody, boolean payload) {
        Map<String, Object> map = new HashMap<>();
        Request request = new Request.Builder()
                .url(NKApiManagerConfig.endpoint + api)
                .header("X-Nooko-Token", NKUserConstants.apiKey)
                .header("X-Nooko-Version", "2")
                .header("Accept", "application/json")
                .post(requestBody)
                .build();

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
                NKApiManagerUtils.doLogging("NOOKO3 RESPONSE " + Integer.toString(response.code()));
                NKApiManagerUtils.doLogging("NOOKO3 RESPONSE " + response.body().toString());
                map.put(NKApiManagerConfig.AM_RESULT, NKApiManagerConfig.RESULT_OK);
                JSONTokener tokener = new JSONTokener(response.body().string());
                JSONObject jObj = new JSONObject(tokener);
                setResponseInMap(jObj, map, payload);
            } else {
                String body = response.body().string();
                NKApiManagerUtils.doLogging("NOOKO3 ERROR" + body);
                map.put(NKApiManagerConfig.AM_ERROR, context.getString(R.string.internal_error));
                map.put(NKApiManagerConfig.AM_RESULT, NKApiManagerConfig.COMMON_INTERNAL_ERROR);
            }
        } catch (IOException e) {
            NKApiManagerUtils.doLogging("NOOKO3 " + e.getStackTrace().toString());
            map.put(NKApiManagerConfig.AM_ERROR, context.getString(R.string.internal_error));
            map.put(NKApiManagerConfig.AM_RESULT, NKApiManagerConfig.COMMON_INTERNAL_ERROR);
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

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

}
