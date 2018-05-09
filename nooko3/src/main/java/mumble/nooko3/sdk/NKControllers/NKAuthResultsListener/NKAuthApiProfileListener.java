package mumble.nooko3.sdk.NKControllers.NKAuthResultsListener;

import mumble.nooko3.sdk.NKAuthAsyncTasks.NKAuthAsyncTask_Authenticate;
import mumble.nooko3.sdk.NKAuthData.NKAuthUser;
import mumble.nooko3.sdk.NKConstants.NKConstants;

/**
 * Interface to use with {@link mumble.nooko3.sdk.NKAuthAsyncTasks.NKAuthAsyncTask_Profile (Context)}, and similar, methods,
 * returns the profile associated with the stored token or the given token.
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKAuthApiProfileListener {
    void onProfileObtained(NKAuthUser user);
    void onProfileObtainedError(String error);
}
