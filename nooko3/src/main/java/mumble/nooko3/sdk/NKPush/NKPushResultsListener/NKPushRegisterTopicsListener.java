package mumble.nooko3.sdk.NKPush.NKPushResultsListener;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Interface to use with {@link NKPushRegisterTopicsListener (Context)}, and similar, methods,
 * send the push token
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKPushRegisterTopicsListener {
    void onTopicsRegistered();
    void onTopicsRegisteredError(String error);
}
