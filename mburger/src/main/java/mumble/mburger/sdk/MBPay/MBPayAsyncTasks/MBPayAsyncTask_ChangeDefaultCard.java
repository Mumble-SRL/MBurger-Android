package mumble.mburger.sdk.MBPay.MBPayAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.MBPay.MBPayResultsListener.MBPayApiChangeDefaultCardListener;

/**
 * Created by Enrico on 29/08/2016.
 */
public class MBPayAsyncTask_ChangeDefaultCard extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    @NonNull
    private WeakReference<Context> weakContext;

    /**
     * New default card id
     */
    @NonNull
    private String card_id;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = MBAPIConstants.ACTION_CHANGE_DEFAULT_CARD;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private MBPayApiChangeDefaultCardListener listener;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public MBPayAsyncTask_ChangeDefaultCard(Context context, String card_id) {
        this.weakContext = new WeakReference<>(context);
        this.card_id = card_id;
    }

    public MBPayAsyncTask_ChangeDefaultCard(Context context, String custom_action, String card_id) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.card_id = card_id;
    }

    public MBPayAsyncTask_ChangeDefaultCard(Context context, MBPayApiChangeDefaultCardListener listener, String card_id) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.card_id = card_id;
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

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        String api = MBApiManagerConfig.API_CARDS + "/" + card_id + MBApiManagerConfig.API_DEFAULT_PART;
        map = MBAPIManager3.callApi(weakContext.get(), api, values, MBApiManagerConfig.MODE_GET, false, false);
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
                    listener.onDefaultCardChangedError(error);
                } else {
                    listener.onDefaultCardChanged();
                }
            }
        }
    }

}

