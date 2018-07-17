package mumble.mburger.sdk.MBAuth.MBAuthResultsListener;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_ForgotPassword;

/**
 * Interface to use with {@link MBAuthAsyncTask_ForgotPassword}, and similar, methods,
 * returns nothing.
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBAuthApiForgotPasswordListener {
    void onForgotPasswordRequested();
    void onForgotPasswordRequestedError(String error);
}
