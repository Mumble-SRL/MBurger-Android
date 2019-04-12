package mumble.mburger.sdk.MBPay.MBPayAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.MBPay.MBPayResultsListener.MBPayApiSubscribeListener;

/**
 * Created by Enrico on 29/08/2016.
 */
public class MBPayAsyncTask_Subscribe extends AsyncTask<Void, Void, Void> {

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
    private String action = MBAPIConstants.ACTION_SUBSCRIBE;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private MBPayApiSubscribeListener listener;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public MBPayAsyncTask_Subscribe(Context context, String subscription, String token, String discount_code,
                                    String meta, int quantity, int trial_days) {
        this.weakContext = new WeakReference<>(context);
        this.subscription = subscription;
        this.token = token;
        this.discount_code = discount_code;
        this.meta = meta;
        this.quantity = quantity;
        this.trial_days = trial_days;
    }

    public MBPayAsyncTask_Subscribe(Context context, String custom_action, String subscription, String token,
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

    public MBPayAsyncTask_Subscribe(Context context, MBPayApiSubscribeListener listener, String subscription, String token,
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

        map = MBAPIManager3.callApi(weakContext.get(), MBApiManagerConfig.API_SUBSCRIBE, values,
                MBApiManagerConfig.MODE_POST, false, false);
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
                    listener.onSubscriptionError(error);
                } else {
                    listener.onSubscriptionSuccess();
                }
            }
        }
    }

}

