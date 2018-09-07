package mumble.mburger.sdk.MBAdmin.MBAdminResultsListeners;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks.MBAdminAsyncTask_deleteMedia;

/**
 * Interface to use with {@link MBAdminAsyncTask_deleteMedia (Context)}, and similar, methods,
 * returns the id of the deleted section
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBAdminApiDeleteMediaListener {
    void onMediaDeleteSuccess(long media_id);
    void onMediaDeleteError(String error);
}
