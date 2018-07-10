package mumble.nooko3.sdk.NKPay.NKPayAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.nooko3.sdk.Common.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.Common.NKApiManager.NKAPIManager3;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.Common.NKCommonMethods;
import mumble.nooko3.sdk.Common.NKConstants.NKAPIConstants;
import mumble.nooko3.sdk.NKPay.NKPayResultsListener.NKPayApiSubscribeListener;

/**
 * Created by Enrico on 29/08/2016.
 */
public class NKPayAsyncTask_Subscribe extends AsyncTask<Void, Void, Void> {

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
     * Stripe™ token which will create a new customer automatically, not required
     */
    @Nullable
    private String token;

    /**
     * Discount code
     */
    @Nullable
    private String discount_code;

    /**
     * Eventual metadata
     */
    @Nullable
    private String meta;

    /**
     * Quantity of subscriptions to register, -1 if not useful
     */
    private int quantity;

    /**
     * Eventual trial days, -1 if not useful
     */
    @Nullable
    private int trial_days;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = NKAPIConstants.ACTION_SUBSCRIBE;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private NKPayApiSubscribeListener listener;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public NKPayAsyncTask_Subscribe(Context context, String subscription, String token, String discount_code,
                                    String meta, int quantity, int trial_days) {
        this.weakContext = new WeakReference<>(context);
        this.subscription = subscription;
        this.token = token;
        this.discount_code = discount_code;
        this.meta = meta;
        this.quantity = quantity;
        this.trial_days = trial_days;
    }

    public NKPayAsyncTask_Subscribe(Context context, String custom_action, String subscription, String token,
                                    String discount_code, String meta, int quantity, int trial_days) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.subscription = subscription;
        this.token = token;
        this.discount_code = discount_code;
        this.meta = meta;
        this.quantity = quantity;
        this.trial_days = trial_days;
    }

    public NKPayAsyncTask_Subscribe(Context context, NKPayApiSubscribeListener listener, String subscription, String token,
                                    String discount_code, String meta, int quantity, int trial_days) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.subscription = subscription;
        this.token = token;
        this.discount_code = discount_code;
        this.meta = meta;
        this.quantity = quantity;
        this.trial_days = trial_days;
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

        if (token != null) {
            values.put("token", token);
        }

        if (discount_code != null) {
            values.put("discount_code", discount_code);
        }

        if (meta != null) {
            values.put("meta", meta);
        }

        if (quantity != -1) {
            values.put("quantity", Integer.toString(quantity));
        }

        if (trial_days != -1) {
            values.put("trial_days", Integer.toString(trial_days));
        }

        map = NKAPIManager3.callApi(weakContext.get(), NKApiManagerConfig.API_SUBSCRIBE, values,
                NKApiManagerConfig.MODE_POST, false, false);
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
                    listener.onSubscriptionError(error);
                } else {
                    listener.onSubscriptionSuccess();
                }
            }
        }
    }

}

