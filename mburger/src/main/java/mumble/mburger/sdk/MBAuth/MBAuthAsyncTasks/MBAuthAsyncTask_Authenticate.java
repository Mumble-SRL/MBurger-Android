package mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.Common.MBApiManager.MBApiPayloadKeys;
import mumble.mburger.sdk.MBAuth.MBAuthResultsListener.MBAuthApiAuthenticateListener;

/**
 * Created by Enrico on 29/08/2016.
 */
public class MBAuthAsyncTask_Authenticate extends AsyncTask<Void, Void, Void> {

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
     * Contracts data
     */
    @Nullable
    private JSONArray contracts;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = MBAPIConstants.ACTION_AUTHENTICATE;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private MBAuthApiAuthenticateListener listener;

    /**
     * JWT token registration
     */
    private String jwt_token;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public MBAuthAsyncTask_Authenticate(Context context, String email, String password, JSONArray contracts) {
        this.weakContext = new WeakReference<>(context);
        this.email = email;
        this.password = password;
        this.contracts = contracts;
    }

    public MBAuthAsyncTask_Authenticate(Context context, String custom_action, String email, String password, JSONArray contracts) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.email = email;
        this.password = password;
        this.contracts = contracts;
    }

    public MBAuthAsyncTask_Authenticate(Context context, MBAuthApiAuthenticateListener listener, String email, String password, JSONArray contracts) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.email = email;
        this.password = password;
        this.contracts = contracts;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        putValuesAndCall();
        if (MBApiManagerUtils.hasMapOkResults(map, true)) {
            getPayload((String) map.get(MBApiManagerConfig.AM_PAYLOAD));
            result = MBApiManagerConfig.RESULT_OK;
        } else {
            if (map.containsKey(MBApiManagerConfig.AM_RESULT)) {
                result = (int) map.get(MBApiManagerConfig.AM_RESULT);
            } else {
                result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
            }

            if (map.containsKey(MBApiManagerConfig.AM_ERROR)) {
                error = (String) map.get(MBApiManagerConfig.AM_ERROR);
            } else {
                error = MBCommonMethods.getErrorMessageFromResult(weakContext.get(), result);
            }
        }
        return null;
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        values.put("mode", "email");

        if(contracts != null){
            if(contracts.length() != 0) {
                values.put("contracts", contracts.toString());
            }
        }

        map = MBAPIManager3.callApi(weakContext.get(), MBApiManagerConfig.API_AUTHENTICATE, values,
                MBApiManagerConfig.MODE_POST, true, false);
    }

    protected void onPostExecute(Void postResult) {
        if (weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                i.putExtra(MBApiPayloadKeys.key_jwt_token, jwt_token);
                MBAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onAuthenticationError(error);
                } else {
                    listener.onAuthenticationSuccess(jwt_token, false);
                }
            }
        }
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jObj = jPayload.getJSONObject("body");
            jwt_token = jObj.getString("access_token");
            MBCommonMethods.setAccessToken(weakContext.get(), jwt_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

