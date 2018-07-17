package mumble.mburger.sdk.MBPay.MBPayResultsListener;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_CancelSubscription;

/**
 * Interface to use with {@link MBPayAsyncTask_CancelSubscription (Context)}, and similar, methods,
 * returns the authentication token
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBPayApiCancelSubscriptionListener {
    void onSubscriptionCancelled();

    void onSubscriptionCancelledError(String error);
}
