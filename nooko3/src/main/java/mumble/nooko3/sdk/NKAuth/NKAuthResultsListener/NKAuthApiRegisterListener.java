package mumble.nooko3.sdk.NKAuth.NKAuthResultsListener;

import mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks.NKAuthAsyncTask_Authenticate;
import mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks.NKAuthAsyncTask_Register;
import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Interface to use with {@link NKAuthAsyncTask_Register}, and similar, methods,
 * returns nothing, to obtain jwt token should call {@link NKAuthAsyncTask_Authenticate}
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKAuthApiRegisterListener {
    void onRegistrationSuccess();
    void onRegistrationError(String error);
}
