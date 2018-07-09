package mumble.nooko3.sdk.NKPay.NKPayAsyncTasks;

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
import mumble.nooko3.sdk.NKPay.NKPayResultsListener.NKPayApiCancelSubscriptionListener;
import mumble.nooko3.sdk.NKPay.NKPayResultsListener.NKPayApiResumeSubscriptionListener;

/**
 * Created by Enrico on 29/08/2016.
 */
public class NKPayAsyncTask_ResumeSubscription extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    @NonNull
    private WeakReference<Context> weakContext;

    /**
     * Subscription Stripe™ item name
     */
    @NonNull
    private String subscription;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = NKAPIConstants.ACTION_RESUME_SUBSCRIPTION;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private NKPayApiResumeSubscriptionListener listener;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public NKPayAsyncTask_ResumeSubscription(Context context, String subscription) {
        this.weakContext = new WeakReference<>(context);
        this.subscription = subscription;
    }

    public NKPayAsyncTask_ResumeSubscription(Context context, String custom_action, String subscription) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.subscription = subscription;
    }

    public NKPayAsyncTask_ResumeSubscription(Context context, NKPayApiResumeSubscriptionListener listener, String subscription) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.subscription = subscription;
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

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        values.put("subscription", subscription);
        map = NKAPIManager3.callApi(weakContext.get(), NKApiManagerConfig.API_SUBSCRIBE_RESUME, values,
                NKApiManagerConfig.MODE_POST, false);
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
                    listener.onSubscriptionResumeError(error);
                } else {
                    listener.onSubscriptionResume();
                }
            }
        }
    }

}

