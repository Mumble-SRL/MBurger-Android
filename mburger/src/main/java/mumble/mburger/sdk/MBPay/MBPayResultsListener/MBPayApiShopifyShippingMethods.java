package mumble.mburger.sdk.MBPay.MBPayResultsListener;

import java.util.ArrayList;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_GetCards;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_ShopifyShipping;
import mumble.mburger.sdk.MBPay.MBPayData.MBShopifyShippingMethods;
import mumble.mburger.sdk.MBPay.MBPayData.MBStripeCard;

/**
 * Interface to use with {@link MBPayAsyncTask_ShopifyShipping (Context)}, and similar, methods,
 * returns the authentication token
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBPayApiShopifyShippingMethods {
    void onShippingMethodsObtained(MBShopifyShippingMethods shipping_methods);
    void onShippingMethodsObtainedError(String error);
}
