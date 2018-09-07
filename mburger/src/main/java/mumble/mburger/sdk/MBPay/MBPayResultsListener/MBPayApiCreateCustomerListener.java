package mumble.mburger.sdk.MBPay.MBPayResultsListener;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_CreateCustomer;

/**
 * Interface to use with {@link MBPayAsyncTask_CreateCustomer (Context)}, and similar, methods,
 * returns the authentication token
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBPayApiCreateCustomerListener {
    void onCustomerCreated();
    void onCustomerCreatedError(String error);
}
