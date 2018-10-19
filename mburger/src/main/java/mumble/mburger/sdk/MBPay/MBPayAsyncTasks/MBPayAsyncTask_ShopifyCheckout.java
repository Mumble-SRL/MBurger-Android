package mumble.mburger.sdk.MBPay.MBPayAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.MBPay.MBPayResultsListener.MBPayApiSubscribeListener;

/**
 * Created by Enrico on 29/08/2016.
 */
public class MBPayAsyncTask_ShopifyCheckout extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    @NonNull
    private WeakReference<Context> weakContext;

    /**
     * Shopify order
     */
    @NonNull
    private long order_id;

    /**
     * Stripe™ token which will create a new customer automatically, not required
     */
    @Nullable
    private String token;

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

    public MBPayAsyncTask_ShopifyCheckout(Context context, long order_id, String token) {
        this.weakContext = new WeakReference<>(context);
        this.order_id = order_id;
        this.token = token;
    }

    public MBPayAsyncTask_ShopifyCheckout(Context context, String custom_action, long order_id, String token) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.order_id = order_id;
        this.token = token;
    }

    public MBPayAsyncTask_ShopifyCheckout(Context context, MBPayApiSubscribeListener listener, long order_id, String token) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.order_id = order_id;
        this.token = token;
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
        values.put("order_id", Long.toString(order_id));
        values.put("token", token);

        map = MBAPIManager3.callApi(weakContext.get(), MBApiManagerConfig.API_CHECKOUT_SHOPIFY, values,
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

