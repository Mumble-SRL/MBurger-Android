package mumble.mburger.sdk.MBAdmin.MBAdminResultsListeners;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks.MBAdminAsyncTask_updateSection;

/**
 * Interface to use with {@link MBAdminAsyncTask_updateSection (Context)}, and similar, methods,
 * returns the id of the updated section
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBAdminApiUpdateSectionListener {
    void onSectionUpdated(long section_id);
    void onSectionUpdatedError(String error);
}
