package mumble.mburger.sdk.MBPay.MBPayAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.Common.MBParser;
import mumble.mburger.sdk.MBPay.MBPayData.MBShopifyShippingMethods;
import mumble.mburger.sdk.MBPay.MBPayResultsListener.MBPayApiShopifyShippingMethods;

/**
 * Created by Enrico on 29/08/2016.
 */
public class MBPayAsyncTask_ShopifyShipping extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    @NonNull
    private WeakReference<Context> weakContext;

    /**
     * Total price
     */
    private double price;

    /**
     * Total weight
     */
    private double weight;

    /**
     * Country
     */
    private String country;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = MBAPIConstants.ACTION_SHOPIFY_SHIPPING;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private MBPayApiShopifyShippingMethods listener;
    private MBShopifyShippingMethods shipping_methods;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public MBPayAsyncTask_ShopifyShipping(Context context, double price, double weight, String country) {
        this.weakContext = new WeakReference<>(context);
        this.price = price;
        this.weight = weight;
        this.country = country;
    }

    public MBPayAsyncTask_ShopifyShipping(Context context, String custom_action, double price, double weight, String country) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.price = price;
        this.weight = weight;
        this.country = country;
    }

    public MBPayAsyncTask_ShopifyShipping(Context context, MBPayApiShopifyShippingMethods listener, double price, double weight, String country) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.price = price;
        this.weight = weight;
        this.country = country;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        putValuesAndCall();
        if (MBApiManagerUtils.hasMapOkResults(map, false)) {
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
        values.put("price", Double.toString(price));
        values.put("weight", Double.toHexString(weight));
        values.put("country", country);

        map = MBAPIManager3.callApi(weakContext.get(), MBApiManagerConfig.API_CHECKOUT_SHOPIFY_SHIPPINGS, values,
                MBApiManagerConfig.MODE_GET, true, false);
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
                    listener.onShippingMethodsObtainedError(error);
                } else {
                    listener.onShippingMethodsObtained(shipping_methods);
                }
            }
        }
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jShippings = jPayload.getJSONObject("body");
            shipping_methods = MBParser.parseShippingMethods(jShippings);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

