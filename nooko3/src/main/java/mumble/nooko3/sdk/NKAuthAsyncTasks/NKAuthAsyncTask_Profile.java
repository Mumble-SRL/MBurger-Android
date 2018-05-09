package mumble.nooko3.sdk.NKAuthAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.nooko3.sdk.NKAuthData.NKAuthUser;
import mumble.nooko3.sdk.NKConstants.NKAPIConstants;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKAPIManager3;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.NKControllers.NKAuthResultsListener.NKAuthApiProfileListener;
import mumble.nooko3.sdk.NKControllers.NKAuthResultsListener.NKAuthApiRegisterListener;
import mumble.nooko3.sdk.NKControllers.NKCommonMethods;
import mumble.nooko3.sdk.NKControllers.NKParser;

/**
 * Created by Enrico on 29/08/2016.
 */
public class NKAuthAsyncTask_Profile extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    @NonNull
    private WeakReference<Context> weakContext;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = NKAPIConstants.ACTION_PROFILE;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private NKAuthApiProfileListener listener;

    /**
     * User obtained from API
     */
    private NKAuthUser user;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public NKAuthAsyncTask_Profile(Context context) {
        this.weakContext = new WeakReference<>(context);
    }

    public NKAuthAsyncTask_Profile(Context context, String custom_action) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
    }

    public NKAuthAsyncTask_Profile(Context context, NKAuthApiProfileListener listener) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
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
        values.put("user_id", "1");
        map = NKAPIManager3.callApi(weakContext.get(), NKApiManagerConfig.API_PROFILE, values, NKApiManagerConfig.MODE_POST, true);
    }

    protected void onPostExecute(Void postResult) {
        if (weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onProfileObtainedError(error);
                } else {
                    listener.onProfileObtained(user);
                }
            }
        }
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jObj = jPayload.getJSONObject("body");
            user = NKParser.parseUser(jObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

