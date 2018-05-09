package mumble.nooko3.sdk.NKAuth.NKAuthResultsListener;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Interface to use with {@link mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks.NKAuthAsyncTask_ChangePassword}, and similar, methods,
 * returns nothing.
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKAuthApiChangePasswordListener {
    void onPasswordChanged();
    void onPasswordChangedError(String error);
}
