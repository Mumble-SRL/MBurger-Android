package mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBApiPayloadKeys;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.MBAuth.MBAuthData.MBAuthUser;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.MBAuth.MBAuthResultsListener.MBAuthApiProfileListener;
import mumble.mburger.sdk.Common.MBParser;

/**
 * Created by Enrico on 29/08/2016.
 */
public class MBAuthAsyncTask_Profile extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    @NonNull
    private WeakReference<Context> weakContext;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = MBAPIConstants.ACTION_PROFILE;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private MBAuthApiProfileListener listener;

    /**
     * User obtained from API
     */
    private MBAuthUser user;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public MBAuthAsyncTask_Profile(Context context) {
        this.weakContext = new WeakReference<>(context);
    }

    public MBAuthAsyncTask_Profile(Context context, String custom_action) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
    }

    public MBAuthAsyncTask_Profile(Context context, MBAuthApiProfileListener listener) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
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
        map = MBAPIManager3.callApi(weakContext.get(), MBApiManagerConfig.API_PROFILE, values,
                MBApiManagerConfig.MODE_GET, true, false);
    }

    protected void onPostExecute(Void postResult) {
        if (weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                i.putExtra(MBApiPayloadKeys.key_profile, user);
                MBAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
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
            user = MBParser.parseUser(jObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

