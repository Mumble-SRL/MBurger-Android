package mumble.nooko3.sdk.NKAuth.NKAuthResultsListener;

import mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks.NKAuthAsyncTask_Authenticate;
import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Interface to use with {@link NKAuthAsyncTask_Authenticate (Context)}, and similar, methods,
 * returns the authentication token
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKAuthApiAuthenticateListener {
    void onAuthenticationSuccess(String jwt_token);
    void onAuthenticationError(String error);
}
