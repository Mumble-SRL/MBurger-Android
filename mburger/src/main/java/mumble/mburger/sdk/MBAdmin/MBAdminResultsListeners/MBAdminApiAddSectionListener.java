package mumble.mburger.sdk.MBAdmin.MBAdminResultsListeners;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks.MBAdminAsyncTask_addSection;

/**
 * Interface to use with {@link MBAdminAsyncTask_addSection (Context)}, and similar, methods,
 * returns the id of the added section
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBAdminApiAddSectionListener {
    void onSectionAdded(long section_id);
    void onSectionAddedError(String error);
}
