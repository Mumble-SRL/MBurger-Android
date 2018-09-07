package mumble.mburger.sdk.MBPush.MBPushAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.json.JSONArray;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.MBPush.MBPushResultsListener.MBPushRegisterTopicsListener;

public class MBPushAsyncTask_RegisterTopics extends AsyncTask<Void, Void, Void> {

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
     * Topics to register on
     */
    @NonNull
    private JSONArray topics;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = MBAPIConstants.ACTION_REGISTER_TOPICS;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private MBPushRegisterTopicsListener listener;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public MBPushAsyncTask_RegisterTopics(Context context, String device_id, JSONArray topics) {
        this.weakContext = new WeakReference<>(context);
        this.device_id = device_id;
        this.topics = topics;
    }

    public MBPushAsyncTask_RegisterTopics(Context context, String custom_action, String device_id, JSONArray topics) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.device_id = device_id;
        this.topics = topics;
    }

    public MBPushAsyncTask_RegisterTopics(Context context, MBPushRegisterTopicsListener listener, String device_id, JSONArray topics) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.device_id = device_id;
        this.topics = topics;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        putValuesAndCall();
        if (MBApiManagerUtils.hasMapOkResults(map, false)) {
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

    @Override
    protected void onPostExecute(Void aVoid) {
        if (weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                MBAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onTopicsRegisteredError(error);
                } else {
                    listener.onTopicsRegistered();
                }
            }
        }
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        values.put("topics", topics.toString());
        values.put("device_id", device_id);
        map = MBAPIManager3.callApi(weakContext.get(),
                MBApiManagerConfig.API_REGISTER_TOPICS, values, MBApiManagerConfig.MODE_POST, false, true);
    }
}
