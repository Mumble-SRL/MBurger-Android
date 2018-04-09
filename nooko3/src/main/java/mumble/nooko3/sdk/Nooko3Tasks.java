package mumble.nooko3.sdk;

import android.content.Context;

import java.util.ArrayList;

import mumble.nooko3.R;
import mumble.nooko3.sdk.NKAsyncTasks.NKAsyncTask_getBlock;
import mumble.nooko3.sdk.NKAsyncTasks.NKAsyncTask_getBlocks;
import mumble.nooko3.sdk.NKAsyncTasks.NKAsyncTask_getElements;
import mumble.nooko3.sdk.NKAsyncTasks.NKAsyncTask_getProject;
import mumble.nooko3.sdk.NKAsyncTasks.NKAsyncTask_getSection;
import mumble.nooko3.sdk.NKAsyncTasks.NKAsyncTask_getSections;
import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKConstants.NKUserConstants;
import mumble.nooko3.sdk.NKControllers.NKApiResultsLIsteners.NKApiBlockResultListener;
import mumble.nooko3.sdk.NKControllers.NKApiResultsLIsteners.NKApiBlocksResultListener;
import mumble.nooko3.sdk.NKControllers.NKApiResultsLIsteners.NKApiElementsResultListener;
import mumble.nooko3.sdk.NKControllers.NKApiResultsLIsteners.NKApiProjectResultListener;
import mumble.nooko3.sdk.NKControllers.NKApiResultsLIsteners.NKApiSectionResultListener;
import mumble.nooko3.sdk.NKControllers.NKApiResultsLIsteners.NKApiSectionsResultListener;
import mumble.nooko3.sdk.NKExceptions.NKSDKInitializeException;

/**
 * Commodity list of tasks for obtaining Nooko data from Nooko3 API
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class Nooko3Tasks {

    /**
     * Asks project data from API
     */
    public static void askForProject(Context context) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getProject(context).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks project data from API with custom action return
     */
    public static void askForProject(Context context, String custom_action) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getProject(context, custom_action).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks project data from API with listener
     */
    public static void askForProject(Context context, NKApiProjectResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getProject(context, listener).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections
     */
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getBlocks(context, filters, getSections).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections and elements
     */
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, boolean getElements) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getBlocks(context, filters, getSections, getElements).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections with a custom action return
     */
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, String custom_action) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getBlocks(context, filters, custom_action, getSections).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections with listener
     */
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, NKApiBlocksResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getBlocks(context, filters, listener, getSections).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections and elements with a custom action return
     */
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, boolean getElements, String custom_action) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getBlocks(context, filters, custom_action, getSections, getElements).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections and elements with listener
     */
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, boolean getElements, NKApiBlocksResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getBlocks(context, filters, listener, getSections, getElements).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks sections data from API with or without elements
     */
    public static void askForSections(Context context, long block_id, ArrayList<Object> filters, boolean getElements) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getSections(context, block_id, filters, getElements).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks sections data from API with or without elements with a custom action return
     */
    public static void askForSections(Context context, long block_id, ArrayList<Object> filters, boolean getElements, String custom_action) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getSections(context, block_id, filters, custom_action, getElements).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks sections data from API with or without elements with listener
     */
    public static void askForSections(Context context, long block_id, ArrayList<Object> filters, boolean getElements, NKApiSectionsResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getSections(context, block_id, filters, listener, getElements).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks section data from API with or without elements with listener
     */
    public static void askForSection(Context context, long section_id, boolean getElements) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getSection(context, section_id, getElements).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks section data from API with or without elements with custom action
     */
    public static void askForSection(Context context, long section_id, boolean getElements, String custom_action) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getSection(context, section_id, custom_action, getElements).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks section data from API with or without elements with listener
     */
    public static void askForSection(Context context, long section_id, boolean getElements, NKApiSectionResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getSection(context, section_id, listener, getElements).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections
     */
    public static void askForBlock(Context context, long block_id, boolean getSections) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getBlock(context, block_id, getSections).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections and elements
     */
    public static void askForBlock(Context context, long block_id, boolean getSections, boolean getElements) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getBlock(context, block_id, getSections, getElements).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections with a custom action return
     */
    public static void askForBlock(Context context, long block_id, boolean getSections, String custom_action) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getBlock(context, block_id, custom_action, getSections).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections with listener
     */
    public static void askForBlock(Context context, long block_id, boolean getSections, NKApiBlockResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getBlock(context, block_id, listener, getSections).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections and elements with a custom action return
     */
    public static void askForBlock(Context context, long block_id, boolean getSections, boolean getElements, String custom_action) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getBlock(context, block_id, custom_action, getSections, getElements).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections and elements with listener
     */
    public static void askForBlock(Context context, long block_id, boolean getSections, boolean getElements, NKApiBlockResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getBlock(context, block_id, listener, getSections, getElements).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks elements for a particular section
     */
    public static void askForElements(Context context, long section_id){
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getElements(context, section_id).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks elements for a particular section using a custom action
     */
    public static void askForElements(Context context, long section_id, String custom_action){
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getElements(context, section_id, custom_action).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks elements for a particular section retrieving data through a listener
     */
    public static void askForElements(Context context, long section_id, NKApiElementsResultListener listener){
        if (NKUserConstants.apiKey != null) {
            new NKAsyncTask_getElements(context, section_id, listener).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }


}
