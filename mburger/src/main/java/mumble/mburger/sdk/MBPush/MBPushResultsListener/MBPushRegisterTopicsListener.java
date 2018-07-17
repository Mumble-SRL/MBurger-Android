package mumble.mburger.sdk.MBPush.MBPushResultsListener;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBPush.MBPushAsyncTasks.MBPushAsyncTask_RegisterTopics;

/**
 * Interface to use with {@link MBPushAsyncTask_RegisterTopics (Context)}, and similar, methods,
 * send the push token
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBPushRegisterTopicsListener {
    void onTopicsRegistered();
    void onTopicsRegisteredError(String error);
}
