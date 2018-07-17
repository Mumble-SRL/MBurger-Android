package mumble.mburger.sdk.MBPush.MBPushResultsListener;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBPush.MBPushAsyncTasks.MBPushAsyncTask_SendToken;

/**
 * Interface to use with {@link MBPushAsyncTask_SendToken (Context)}, and similar, methods,
 * send the push token
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBPushSendTokenListener {
    void onTokenSent();
    void onTokenSentError(String error);
}
