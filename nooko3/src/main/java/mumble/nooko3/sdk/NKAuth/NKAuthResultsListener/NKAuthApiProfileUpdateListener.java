package mumble.nooko3.sdk.NKAuth.NKAuthResultsListener;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKAuth.NKAuthData.NKAuthUser;

/**
 * Interface to use with {@link mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks.NKAuthAsyncTask_Profile (Context)}, and similar, methods,
 * returns the profile associated with the stored token or the given token.
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKAuthApiProfileUpdateListener {
    void onProfileObtained(NKAuthUser user);
    void onProfileObtainedError(String error);
}
