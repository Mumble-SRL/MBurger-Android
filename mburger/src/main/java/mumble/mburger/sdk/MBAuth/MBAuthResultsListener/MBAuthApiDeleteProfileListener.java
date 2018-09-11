package mumble.mburger.sdk.MBAuth.MBAuthResultsListener;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_ChangePassword;

/**
 * Interface to use with {@link mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_DeleteProfile}, and similar, methods,
 * returns nothing.
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBAuthApiDeleteProfileListener {
    void onProfileDeleted();
    void onProfileDeletedError(String error);
}
