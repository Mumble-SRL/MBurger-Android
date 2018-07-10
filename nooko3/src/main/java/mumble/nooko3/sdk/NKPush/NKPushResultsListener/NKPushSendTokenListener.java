package mumble.nooko3.sdk.NKPush.NKPushResultsListener;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks.NKAuthAsyncTask_Authenticate;

/**
 * Interface to use with {@link NKPushSendTokenListener (Context)}, and similar, methods,
 * send the push token
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKPushSendTokenListener {
    void onTokenSent();
    void onTokenSentError(String error);
}
