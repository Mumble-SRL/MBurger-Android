package mumble.nooko3.sdk.NKControllers.NKAuthResultsListener;

import mumble.nooko3.sdk.NKAuthAsyncTasks.NKAuthAsyncTask_Authenticate;
import mumble.nooko3.sdk.NKAuthAsyncTasks.NKAuthAsyncTask_Register;
import mumble.nooko3.sdk.NKConstants.NKConstants;

/**
 * Interface to use with {@link mumble.nooko3.sdk.NKAuthAsyncTasks.NKAuthAsyncTask_ForgotPassword}, and similar, methods,
 * returns nothing.
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKAuthApiForgotPasswordListener {
    void onForgotPasswordRequested();
    void onForgotPasswordRequestedError(String error);
}
