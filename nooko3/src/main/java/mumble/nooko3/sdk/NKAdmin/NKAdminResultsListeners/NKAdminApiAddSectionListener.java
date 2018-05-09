package mumble.nooko3.sdk.NKAdmin.NKAdminResultsListeners;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Interface to use with {@link mumble.nooko3.sdk.NKAdmin.NKAdminAsyncTasks.NKAdminAsyncTask_addSection (Context)}, and similar, methods,
 * returns the id of the added section
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKAdminApiAddSectionListener {
    void onSectionAdded(long section_id);
    void onSectionAddedError(String error);
}
