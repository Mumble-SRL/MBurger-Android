package mumble.nooko3.sdk.NKControllers.NKAdminResultsListeners;

import mumble.nooko3.sdk.NKAdminAsyncTasks.NKAdminAsyncTask_deleteSection;
import mumble.nooko3.sdk.NKConstants.NKConstants;

/**
 * Interface to use with {@link mumble.nooko3.sdk.NKAdminAsyncTasks.NKAdminAsyncTask_addSection (Context)}, and similar, methods,
 * returns the id of the added section
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKAdminApiAddSectionListener {
    void onSectionAdded(long section_id);
    void onSectionAddedError(String error);
}
