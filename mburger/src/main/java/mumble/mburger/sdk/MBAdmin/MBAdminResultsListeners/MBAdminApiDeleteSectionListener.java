package mumble.mburger.sdk.MBAdmin.MBAdminResultsListeners;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks.MBAdminAsyncTask_deleteSection;

/**
 * Interface to use with {@link MBAdminAsyncTask_deleteSection (Context)}, and similar, methods,
 * returns the id of the deleted section
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBAdminApiDeleteSectionListener {
    void onSectionDeleteSuccess(long section_id);
    void onSectionDeleteError(String error);
}
