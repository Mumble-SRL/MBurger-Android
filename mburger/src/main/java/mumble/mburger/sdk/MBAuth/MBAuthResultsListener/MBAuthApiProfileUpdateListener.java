package mumble.mburger.sdk.MBAuth.MBAuthResultsListener;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBAuth.MBAuthData.MBAuthUser;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_Profile;

/**
 * Interface to use with {@link MBAuthAsyncTask_Profile (Context)}, and similar, methods,
 * returns the profile associated with the stored token or the given token.
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBAuthApiProfileUpdateListener {
    void onProfileObtained(MBAuthUser user);
    void onProfileObtainedError(String error);
}
