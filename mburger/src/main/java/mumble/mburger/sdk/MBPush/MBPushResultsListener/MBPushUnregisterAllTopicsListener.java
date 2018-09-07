package mumble.mburger.sdk.MBPush.MBPushResultsListener;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBPush.MBPushAsyncTasks.MBPushAsyncTask_UnregisterAllTopics;

/**
 * Interface to use with {@link MBPushAsyncTask_UnregisterAllTopics (Context)}, and similar, methods,
 * send the push token
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBPushUnregisterAllTopicsListener {
    void onAllTopicsUnregistered();
    void onAllTopicsUnregisteredError(String error);
}
