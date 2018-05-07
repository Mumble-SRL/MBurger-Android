package mumble.nooko3.sdk;

import android.content.Context;

import mumble.nooko3.R;
import mumble.nooko3.sdk.NKAdminAsyncTasks.NKAdminAsyncTask_deleteSection;
import mumble.nooko3.sdk.NKConstants.NKUserConstants;
import mumble.nooko3.sdk.NKControllers.NKAdminResultsListeners.NKAdminApiDeleteSectionListener;
import mumble.nooko3.sdk.NKExceptions.NKSDKInitializeException;

public class Nooko3AdminTasks {

    /**
     * Delete a section
     */
    public static void deleteSection(Context context, long section_id) {
        if (NKUserConstants.apiKey != null) {
            new NKAdminAsyncTask_deleteSection(context, section_id).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Delete a section with custom action return
     */
    public static void deleteSection(Context context, String custom_action, long section_id) {
        if (NKUserConstants.apiKey != null) {
            new NKAdminAsyncTask_deleteSection(context, custom_action, section_id).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Delete a section with listener
     */
    public static void deleteSection(Context context, NKAdminApiDeleteSectionListener listener, long section_id) {
        if (NKUserConstants.apiKey != null) {
            new NKAdminAsyncTask_deleteSection(context, listener, section_id).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

}
