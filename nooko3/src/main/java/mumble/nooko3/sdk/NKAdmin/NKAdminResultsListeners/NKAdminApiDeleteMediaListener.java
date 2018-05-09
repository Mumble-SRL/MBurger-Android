package mumble.nooko3.sdk.NKAdmin.NKAdminResultsListeners;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Interface to use with {@link mumble.nooko3.sdk.NKAdmin.NKAdminAsyncTasks.NKAdminAsyncTask_deleteMedia (Context)}, and similar, methods,
 * returns the id of the deleted section
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKAdminApiDeleteMediaListener {
    void onMediaDeleteSuccess(long media_id);
    void onMediaDeleteError(String error);
}
