package mumble.nooko3.sdk;

import android.content.Context;

import java.util.ArrayList;

import mumble.nooko3.R;
import mumble.nooko3.sdk.NKAdminAsyncTasks.NKAdminAsyncTask_addSection;
import mumble.nooko3.sdk.NKAdminAsyncTasks.NKAdminAsyncTask_deleteMedia;
import mumble.nooko3.sdk.NKAdminAsyncTasks.NKAdminAsyncTask_deleteSection;
import mumble.nooko3.sdk.NKAdminAsyncTasks.NKAdminAsyncTask_updateSection;
import mumble.nooko3.sdk.NKAdminData.NKAdminParameter;
import mumble.nooko3.sdk.NKAdminData.NKAdminParameterFile;
import mumble.nooko3.sdk.NKConstants.NKUserConstants;
import mumble.nooko3.sdk.NKControllers.NKAdminResultsListeners.NKAdminApiAddSectionListener;
import mumble.nooko3.sdk.NKControllers.NKAdminResultsListeners.NKAdminApiDeleteMediaListener;
import mumble.nooko3.sdk.NKControllers.NKAdminResultsListeners.NKAdminApiDeleteSectionListener;
import mumble.nooko3.sdk.NKControllers.NKAdminResultsListeners.NKAdminApiUpdateSectionListener;
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

    /**
     * Add a section
     */
    public static void addSection(Context context, long block_id, ArrayList<NKAdminParameter> params, ArrayList<NKAdminParameterFile> parameters_files, String locale) {
        if (NKUserConstants.apiKey != null) {
            new NKAdminAsyncTask_addSection(context, block_id, params, parameters_files, locale).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Add a section with custom action return
     */
    public static void addSection(Context context, long block_id, String custom_action, ArrayList<NKAdminParameter> params, ArrayList<NKAdminParameterFile> parameters_files, String locale) {
        if (NKUserConstants.apiKey != null) {
            new NKAdminAsyncTask_addSection(context, custom_action, block_id, params, parameters_files, locale).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Add a section with listener
     */
    public static void addSection(Context context, long block_id, NKAdminApiAddSectionListener listener, ArrayList<NKAdminParameter> params, ArrayList<NKAdminParameterFile> parameters_files, String locale) {
        if (NKUserConstants.apiKey != null) {
            new NKAdminAsyncTask_addSection(context, listener, block_id, params, parameters_files, locale).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Add a section
     */
    public static void updateSection(Context context, long section_id, ArrayList<NKAdminParameter> params,
                                     ArrayList<NKAdminParameterFile> parameters_files, String locale) {
        if (NKUserConstants.apiKey != null) {
            new NKAdminAsyncTask_updateSection(context, section_id, params, parameters_files, locale).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Add a section with custom action return
     */
    public static void updateSection(Context context, long section_id, String custom_action, ArrayList<NKAdminParameter> params,
                                     ArrayList<NKAdminParameterFile> parameters_files, String locale) {
        if (NKUserConstants.apiKey != null) {
            new NKAdminAsyncTask_updateSection(context, custom_action, section_id, params, parameters_files, locale).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Add a section with listener
     */
    public static void updateSection(Context context, long section_id, NKAdminApiUpdateSectionListener listener, ArrayList<NKAdminParameter> params,
                                     ArrayList<NKAdminParameterFile> parameters_files, String locale) {
        if (NKUserConstants.apiKey != null) {
            new NKAdminAsyncTask_updateSection(context, listener, section_id, params, parameters_files, locale).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Delete a media from a section
     */
    public static void deleteMedia(Context context, long media_id) {
        if (NKUserConstants.apiKey != null) {
            new NKAdminAsyncTask_deleteMedia(context, media_id).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Delete a media from a section with custom action return
     */
    public static void deleteMedia(Context context, String custom_action, long media_id) {
        if (NKUserConstants.apiKey != null) {
            new NKAdminAsyncTask_deleteMedia(context, custom_action, media_id).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Delete a media from a section with listener
     */
    public static void deleteMedia(Context context, NKAdminApiDeleteMediaListener listener, long media_id) {
        if (NKUserConstants.apiKey != null) {
            new NKAdminAsyncTask_deleteMedia(context, listener, media_id).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }
}
