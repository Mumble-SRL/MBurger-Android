package mumble.mburger.sdk.MBPay.MBPayResultsListener;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_ResumeSubscription;

/**
 * Interface to use with {@link MBPayAsyncTask_ResumeSubscription (Context)}, and similar, methods,
 * returns the authentication token
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBPayApiResumeSubscriptionListener {
    void onSubscriptionResume();

    void onSubscriptionResumeError(String error);
}
