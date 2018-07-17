package mumble.mburger.sdk.MBClient.MBAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.MBClient.MBApiResultsLIsteners.MBApiSendLiveMessageListener;
import mumble.mburger.sdk.MBClient.MBData.MBProjects.MBProject;

public class MBAsyncTask_sendLiveMessage extends AsyncTask<Void, Void, Void> {

    private WeakReference<Context> weakContext;
    private String action = MBAPIConstants.ACTION_SEND_LIVE_MESSAGE;
    private MBApiSendLiveMessageListener listener;

    private String sender_name, text;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private MBProject project;

    public MBAsyncTask_sendLiveMessage(Context context, String sender_name, String text) {
        this.weakContext = new WeakReference<>(context);
        this.text = text;
        this.sender_name = sender_name;
    }

    public MBAsyncTask_sendLiveMessage(Context context, String sender_name, String text,
                                       MBApiSendLiveMessageListener listener) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.text = text;
        this.sender_name = sender_name;
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

    protected void onPostExecute(Void postResult) {
        if (weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                MBAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onSendLiveMessageApiError(error);
                } else {
                    listener.onSendLiveMessageApiResult();
                }
            }
        }
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        values.put("text", text);
        values.put("sender_name", sender_name);
        map = MBAPIManager3.callApi(weakContext.get(), MBApiManagerConfig.API_SEND_LIVE_MESSAGE, values,
                MBApiManagerConfig.MODE_POST, true, false);
    }
}
