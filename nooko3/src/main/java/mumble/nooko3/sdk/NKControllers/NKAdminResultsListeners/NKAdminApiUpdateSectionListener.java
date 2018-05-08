package mumble.nooko3.sdk.NKControllers.NKAdminResultsListeners;

import mumble.nooko3.sdk.NKConstants.NKConstants;

/**
 * Interface to use with {@link mumble.nooko3.sdk.NKAdminAsyncTasks.NKAdminAsyncTask_updateSection (Context)}, and similar, methods,
 * returns the id of the updated section
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKAdminApiUpdateSectionListener {
    void onSectionUpdated(long section_id);
    void onSectionUpdatedError(String error);
}
