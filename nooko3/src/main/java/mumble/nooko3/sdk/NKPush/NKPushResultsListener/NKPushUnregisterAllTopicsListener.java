package mumble.nooko3.sdk.NKPush.NKPushResultsListener;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Interface to use with {@link mumble.nooko3.sdk.NKPush.NKPushAsyncTasks.NKPushAsyncTask_UnregisterAllTopics (Context)}, and similar, methods,
 * send the push token
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKPushUnregisterAllTopicsListener {
    void onAllTopicsUnregistered();
    void onAllTopicsUnregisteredError(String error);
}
