package mumble.nooko3.sdk.NKAdmin.NKAdminResultsListeners;

import mumble.nooko3.sdk.NKAdmin.NKAdminAsyncTasks.NKAdminAsyncTask_deleteSection;
import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Interface to use with {@link NKAdminAsyncTask_deleteSection (Context)}, and similar, methods,
 * returns the id of the deleted section
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKAdminApiDeleteSectionListener {
    void onSectionDeleteSuccess(long section_id);
    void onSectionDeleteError(String error);
}