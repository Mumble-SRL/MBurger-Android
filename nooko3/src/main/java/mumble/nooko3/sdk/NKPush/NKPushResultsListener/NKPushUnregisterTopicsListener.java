package mumble.nooko3.sdk.NKPush.NKPushResultsListener;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Interface to use with {@link mumble.nooko3.sdk.NKPush.NKPushAsyncTasks.NKPushAsyncTask_UnregisterTopics (Context)}, and similar, methods,
 * send the push token
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKPushUnregisterTopicsListener {
    void onTopicsUnregistered();
    void onTopicsUnregisteredError(String error);
}
