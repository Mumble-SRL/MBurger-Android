package mumble.nooko3.sdk.NKPay.NKPayResultsListener;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Interface to use with {@link mumble.nooko3.sdk.NKPay.NKPayAsyncTasks.NKPayAsyncTask_ResumeSubscription (Context)}, and similar, methods,
 * returns the authentication token
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKPayApiResumeSubscriptionListener {
    void onSubscriptionResume();

    void onSubscriptionResumeError(String error);
}
