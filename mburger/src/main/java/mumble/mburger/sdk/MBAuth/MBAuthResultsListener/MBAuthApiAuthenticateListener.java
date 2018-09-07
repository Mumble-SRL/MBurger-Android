package mumble.mburger.sdk.MBAuth.MBAuthResultsListener;

import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_Authenticate;
import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Interface to use with {@link MBAuthAsyncTask_Authenticate (Context)}, and similar, methods,
 * returns the authentication token
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBAuthApiAuthenticateListener {
    void onAuthenticationSuccess(String jwt_token);
    void onAuthenticationError(String error);
}
