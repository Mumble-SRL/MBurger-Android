package mumble.nooko3.sdk.NKClient.NKAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.nooko3.sdk.Common.NKConstants.NKAPIConstants;
import mumble.nooko3.sdk.Common.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.Common.NKApiManager.NKAPIManager3;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.NKClient.NKApiResultsLIsteners.NKApiSendLiveMessageListener;
import mumble.nooko3.sdk.Common.NKCommonMethods;
import mumble.nooko3.sdk.NKData.NKProjects.NKProject;

public class NKAsyncTask_sendLiveMessage extends AsyncTask<Void, Void, Void> {

    private WeakReference<Context> weakContext;
    private String action = NKAPIConstants.ACTION_SEND_LIVE_MESSAGE;
    private NKApiSendLiveMessageListener listener;

    private String sender_name, text;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private NKProject project;

    public NKAsyncTask_sendLiveMessage(Context context, String sender_name, String text) {
        this.weakContext = new WeakReference<>(context);
        this.text = text;
        this.sender_name = sender_name;
    }

    public NKAsyncTask_sendLiveMessage(Context context, String sender_name, String text,
                                       NKApiSendLiveMessageListener listener) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.text = text;
        this.sender_name = sender_name;
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

    protected void onPostExecute(Void postResult) {
        if (weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
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
        map = NKAPIManager3.callApi(weakContext.get(), NKApiManagerConfig.API_SEND_LIVE_MESSAGE, values,
                NKApiManagerConfig.MODE_POST, true);
    }
}
