package mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.nooko3.sdk.Common.NKConstants.NKAPIConstants;
import mumble.nooko3.sdk.Common.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.Common.NKApiManager.NKAPIManager3;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiPayloadKeys;
import mumble.nooko3.sdk.NKAuth.NKAuthResultsListener.NKAuthApiAuthenticateListener;
import mumble.nooko3.sdk.Common.NKCommonMethods;

/**
 * Created by Enrico on 29/08/2016.
 */
public class NKAuthAsyncTask_Authenticate extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    @NonNull
    private WeakReference<Context> weakContext;

    /**
     * Authentication email
     */
    @NonNull
    private String email;

    /**
     * Authentication password
     */
    @NonNull
    private String password;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = NKAPIConstants.ACTION_AUTHENTICATE;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private NKAuthApiAuthenticateListener listener;

    /**
     * JWT token registration
     */
    private String jwt_token;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public NKAuthAsyncTask_Authenticate(Context context, String email, String password) {
        this.weakContext = new WeakReference<>(context);
        this.email = email;
        this.password = password;
    }

    public NKAuthAsyncTask_Authenticate(Context context, String custom_action, String email, String password) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.email = email;
        this.password = password;
    }

    public NKAuthAsyncTask_Authenticate(Context context, NKAuthApiAuthenticateListener listener, String email, String password) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.email = email;
        this.password = password;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        putValuesAndCall();
        if (NKApiManagerUtils.hasMapOkResults(map, true)) {
            getPayload((String) map.get(NKApiManagerConfig.AM_PAYLOAD));
            result = NKApiManagerConfig.RESULT_OK;
        } else {
            if (map.containsKey(NKApiManagerConfig.AM_RESULT)) {
                result = (int) map.get(NKApiManagerConfig.AM_RESULT);
            } else {
                result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
            }

            if (map.containsKey(NKApiManagerConfig.AM_ERROR)) {
                error = (String) map.get(NKApiManagerConfig.AM_ERROR);
            } else {
                error = NKCommonMethods.getErrorMessageFromResult(weakContext.get(), result);
            }
        }
        return null;
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        values.put("mode", "email");
        values.put("user_id", "1");
        map = NKAPIManager3.callApi(weakContext.get(), NKApiManagerConfig.API_AUTHENTICATE, values, NKApiManagerConfig.MODE_POST, true);
    }

    protected void onPostExecute(Void postResult) {
        if (weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                i.putExtra(NKApiPayloadKeys.key_jwt_token, jwt_token);
                NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onAuthenticationError(error);
                } else {
                    listener.onAuthenticationSuccess(jwt_token);
                }
            }
        }
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jObj = jPayload.getJSONObject("body");
            jwt_token = jObj.getString("access_token");
            NKCommonMethods.setAccessToken(weakContext.get(), jwt_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

