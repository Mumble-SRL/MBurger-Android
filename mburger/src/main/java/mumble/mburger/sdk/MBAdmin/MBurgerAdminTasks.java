package mumble.mburger.sdk.MBAdmin;

import android.content.Context;

import java.util.ArrayList;

import mumble.mburger.R;
import mumble.mburger.sdk.Common.MBExceptions.MBSDKInitializeException;
import mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks.MBAdminAsyncTask_addSection;
import mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks.MBAdminAsyncTask_deleteMedia;
import mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks.MBAdminAsyncTask_deleteSection;
import mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks.MBAdminAsyncTask_updateSection;
import mumble.mburger.sdk.MBAdmin.MBAdminData.MBAdminParameter;
import mumble.mburger.sdk.MBAdmin.MBAdminData.MBAdminParameterFile;
import mumble.mburger.sdk.Common.MBConstants.MBUserConstants;
import mumble.mburger.sdk.MBAdmin.MBAdminResultsListeners.MBAdminApiAddSectionListener;
import mumble.mburger.sdk.MBAdmin.MBAdminResultsListeners.MBAdminApiDeleteMediaListener;
import mumble.mburger.sdk.MBAdmin.MBAdminResultsListeners.MBAdminApiDeleteSectionListener;
import mumble.mburger.sdk.MBAdmin.MBAdminResultsListeners.MBAdminApiUpdateSectionListener;

public class MBurgerAdminTasks {

    /**
     * Delete a section
     */
    public static void deleteSection(Context context, long section_id) {
        if (MBUserConstants.apiKey != null) {
            new MBAdminAsyncTask_deleteSection(context, section_id).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Delete a section with custom action return
     */
    public static void deleteSection(Context context, String custom_action, long section_id) {
        if (MBUserConstants.apiKey != null) {
            new MBAdminAsyncTask_deleteSection(context, custom_action, section_id).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Delete a section with listener
     */
    public static void deleteSection(Context context, MBAdminApiDeleteSectionListener listener, long section_id) {
        if (MBUserConstants.apiKey != null) {
            new MBAdminAsyncTask_deleteSection(context, listener, section_id).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Add a section
     */
    public static void addSection(Context context, long block_id, ArrayList<MBAdminParameter> params, ArrayList<MBAdminParameterFile> parameters_files, String locale) {
        if (MBUserConstants.apiKey != null) {
            new MBAdminAsyncTask_addSection(context, block_id, params, parameters_files, locale).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Add a section with custom action return
     */
    public static void addSection(Context context, long block_id, String custom_action, ArrayList<MBAdminParameter> params, ArrayList<MBAdminParameterFile> parameters_files, String locale) {
        if (MBUserConstants.apiKey != null) {
            new MBAdminAsyncTask_addSection(context, custom_action, block_id, params, parameters_files, locale).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Add a section with listener
     */
    public static void addSection(Context context, long block_id, MBAdminApiAddSectionListener listener, ArrayList<MBAdminParameter> params, ArrayList<MBAdminParameterFile> parameters_files, String locale) {
        if (MBUserConstants.apiKey != null) {
            new MBAdminAsyncTask_addSection(context, listener, block_id, params, parameters_files, locale).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Add a section
     */
    public static void updateSection(Context context, long section_id, ArrayList<MBAdminParameter> params,
                                     ArrayList<MBAdminParameterFile> parameters_files, String locale, boolean show_in_app) {
        if (MBUserConstants.apiKey != null) {
            new MBAdminAsyncTask_updateSection(context, section_id, params, parameters_files, locale, show_in_app).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Add a section with custom action return
     */
    public static void updateSection(Context context, long section_id, String custom_action, ArrayList<MBAdminParameter> params,
                                     ArrayList<MBAdminParameterFile> parameters_files, String locale, boolean show_in_app) {
        if (MBUserConstants.apiKey != null) {
            new MBAdminAsyncTask_updateSection(context, custom_action, section_id, params, parameters_files, locale, show_in_app).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Add a section with listener
     */
    public static void updateSection(Context context, long section_id, MBAdminApiUpdateSectionListener listener, ArrayList<MBAdminParameter> params,
                                     ArrayList<MBAdminParameterFile> parameters_files, String locale, boolean show_in_app) {
        if (MBUserConstants.apiKey != null) {
            new MBAdminAsyncTask_updateSection(context, listener, section_id, params, parameters_files, locale, show_in_app).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Delete a media from a section
     */
    public static void deleteMedia(Context context, long media_id) {
        if (MBUserConstants.apiKey != null) {
            new MBAdminAsyncTask_deleteMedia(context, media_id).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Delete a media from a section with custom action return
     */
    public static void deleteMedia(Context context, String custom_action, long media_id) {
        if (MBUserConstants.apiKey != null) {
            new MBAdminAsyncTask_deleteMedia(context, custom_action, media_id).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Delete a media from a section with listener
     */
    public static void deleteMedia(Context context, MBAdminApiDeleteMediaListener listener, long media_id) {
        if (MBUserConstants.apiKey != null) {
            new MBAdminAsyncTask_deleteMedia(context, listener, media_id).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }
}
