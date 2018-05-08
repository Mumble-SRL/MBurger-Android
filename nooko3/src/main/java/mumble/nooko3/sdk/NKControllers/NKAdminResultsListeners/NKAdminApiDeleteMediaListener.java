package mumble.nooko3.sdk.NKControllers.NKAdminResultsListeners;

import mumble.nooko3.sdk.NKAdminAsyncTasks.NKAdminAsyncTask_deleteSection;
import mumble.nooko3.sdk.NKConstants.NKConstants;

/**
 * Interface to use with {@link mumble.nooko3.sdk.NKAdminAsyncTasks.NKAdminAsyncTask_deleteMedia (Context)}, and similar, methods,
 * returns the id of the deleted section
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKAdminApiDeleteMediaListener {
    void onMediaDeleteSuccess(long media_id);
    void onMediaDeleteError(String error);
}
