package mumble.mburger.sdk.MBPay.MBPayResultsListener;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_Subscribe;

/**
 * Interface to use with {@link MBPayAsyncTask_Subscribe (Context)}, and similar, methods,
 * returns the authentication token
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBPayApiSubscribeListener {
    void onSubscriptionSuccess();

    void onSubscriptionError(String error);
}
