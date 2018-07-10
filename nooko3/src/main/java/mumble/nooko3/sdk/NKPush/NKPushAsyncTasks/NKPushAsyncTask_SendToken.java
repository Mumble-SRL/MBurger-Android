package mumble.nooko3.sdk.NKPush.NKPushAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.nooko3.sdk.Common.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.Common.NKApiManager.NKAPIManager3;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.Common.NKCommonMethods;
import mumble.nooko3.sdk.Common.NKConstants.NKAPIConstants;
import mumble.nooko3.sdk.NKPush.NKPushResultsListener.NKPushSendTokenListener;

public class NKPushAsyncTask_SendToken extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    @NonNull
    private WeakReference<Context> weakContext;

    /**
     * Android device id
     */
    @NonNull
    private String device_id;

    /**
     * Push token obtained from Firebase
     */
    @NonNull
    private String token;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = NKAPIConstants.ACTION_CANCEL_SUBSCRIPTION;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private NKPushSendTokenListener listener;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public NKPushAsyncTask_SendToken(Context context, String device_id, String token) {
        this.weakContext = new WeakReference<>(context);
        this.device_id = device_id;
        this.token = token;
    }

    public NKPushAsyncTask_SendToken(Context context, String cutom_action, String device_id, String token) {
        this.weakContext = new WeakReference<>(context);
        this.action = cutom_action;
        this.device_id = device_id;
        this.token = token;
    }

    public NKPushAsyncTask_SendToken(Context context, NKPushSendTokenListener listener, String device_id, String token) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.device_id = device_id;
        this.token = token;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        putValuesAndCall();
        if (NKApiManagerUtils.hasMapOkResults(map, false)) {
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

    @Override
    protected void onPostExecute(Void aVoid) {
        if (weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onTokenSentError(error);
                } else {
                    listener.onTokenSent();
                }
            }
        }
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        values.put("token", token);
        values.put("device_id", device_id);
        values.put("platform", "and");
        map = NKAPIManager3.callApi(weakContext.get(),
                NKApiManagerConfig.API_TOKENS_PUSH, values, NKApiManagerConfig.MODE_POST, false, true);
    }
}
