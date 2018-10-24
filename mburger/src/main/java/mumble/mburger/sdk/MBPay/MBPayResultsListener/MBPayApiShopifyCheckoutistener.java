package mumble.mburger.sdk.MBPay.MBPayResultsListener;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_Subscribe;

/**
 * Interface to use with {@link mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_ShopifyCheckout (Context)}, and similar, methods,
 * returns the authentication token
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBPayApiShopifyCheckoutistener {
    void onShopifyCheckoutSuccess();

    void onShopifyCheckoutError(String error);
}
