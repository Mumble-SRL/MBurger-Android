package mumble.mburger.sdk.MBClient;

import android.content.Context;

import java.util.ArrayList;

import mumble.mburger.R;
import mumble.mburger.sdk.Common.MBExceptions.MBSDKInitializeException;
import mumble.mburger.sdk.Common.MBConstants.MBUserConstants;
import mumble.mburger.sdk.MBClient.MBApiResultsLIsteners.MBApiBlocksResultListener;
import mumble.mburger.sdk.MBClient.MBApiResultsLIsteners.MBApiElementsResultListener;
import mumble.mburger.sdk.MBClient.MBApiResultsLIsteners.MBApiSectionResultListener;
import mumble.mburger.sdk.MBClient.MBApiResultsLIsteners.MBApiSectionsResultListener;
import mumble.mburger.sdk.MBClient.MBApiResultsLIsteners.MBApiVotePollListener;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_VotePoll;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getBlock;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getBlocks;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getElements;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getProject;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_sendLiveMessage;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getSection;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getSections;
import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBApiResultsLIsteners.MBApiBlockResultListener;
import mumble.mburger.sdk.MBClient.MBApiResultsLIsteners.MBApiProjectResultListener;
import mumble.mburger.sdk.MBClient.MBApiResultsLIsteners.MBApiSendLiveMessageListener;
import mumble.mburger.sdk.MBClient.MBData.MBElements.MBSubElements.MBAnswer;

/**
 * Commodity list of tasks for obtaining MBurger data from MBurger API
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBurgerTasks {

    /**
     * Asks project data from API
     */
    public static void askForProject(Context context, boolean include_contracts) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getProject(context, include_contracts).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks project data from API with custom action return
     */
    public static void askForProject(Context context, String custom_action, boolean include_contracts) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getProject(context, custom_action, include_contracts).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks project data from API with listener
     */
    public static void askForProject(Context context, MBApiProjectResultListener listener, boolean include_contracts) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getProject(context, listener, include_contracts).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections
     */
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getBlocks(context, filters, getSections).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections and elements
     */
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, boolean getElements) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getBlocks(context, filters, getSections, getElements).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections with a custom action return
     */
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, String custom_action) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getBlocks(context, filters, custom_action, getSections).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections with listener
     */
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, MBApiBlocksResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getBlocks(context, filters, listener, getSections).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections and elements with a custom action return
     */
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, boolean getElements, String custom_action) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getBlocks(context, filters, custom_action, getSections, getElements).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections and elements with listener
     */
    public static void askForBlocks(Context context, ArrayList<Object> filters, boolean getSections, boolean getElements, MBApiBlocksResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getBlocks(context, filters, listener, getSections, getElements).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks sections data from API with or without elements
     */
    public static void askForSections(Context context, long block_id, ArrayList<Object> filters, boolean getElements) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getSections(context, block_id, filters, getElements).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks sections data from API with or without elements with a custom action return
     */
    public static void askForSections(Context context, long block_id, ArrayList<Object> filters, boolean getElements, String custom_action) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getSections(context, block_id, filters, custom_action, getElements).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks sections data from API with or without elements with listener
     */
    public static void askForSections(Context context, long block_id, ArrayList<Object> filters, boolean getElements, MBApiSectionsResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getSections(context, block_id, filters, listener, getElements).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks section data from API with or without elements with listener
     */
    public static void askForSection(Context context, long section_id, boolean getElements) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getSection(context, section_id, getElements).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks section data from API with or without elements with custom action
     */
    public static void askForSection(Context context, long section_id, boolean getElements, String custom_action) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getSection(context, section_id, custom_action, getElements).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks section data from API with or without elements with listener
     */
    public static void askForSection(Context context, long section_id, boolean getElements, MBApiSectionResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getSection(context, section_id, listener, getElements).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections
     */
    public static void askForBlock(Context context, long block_id, boolean getSections) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getBlock(context, block_id, getSections).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections and elements
     */
    public static void askForBlock(Context context, long block_id, boolean getSections, boolean getElements) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getBlock(context, block_id, getSections, getElements).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections with a custom action return
     */
    public static void askForBlock(Context context, long block_id, boolean getSections, String custom_action) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getBlock(context, block_id, custom_action, getSections).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections with listener
     */
    public static void askForBlock(Context context, long block_id, boolean getSections, MBApiBlockResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getBlock(context, block_id, listener, getSections).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections and elements with a custom action return
     */
    public static void askForBlock(Context context, long block_id, boolean getSections, boolean getElements, String custom_action) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getBlock(context, block_id, custom_action, getSections, getElements).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks blocks data from API with or without sections and elements with listener
     */
    public static void askForBlock(Context context, long block_id, boolean getSections, boolean getElements, MBApiBlockResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getBlock(context, block_id, listener, getSections, getElements).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks elements for a particular section
     */
    public static void askForElements(Context context, long section_id){
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getElements(context, section_id).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks elements for a particular section using a custom action
     */
    public static void askForElements(Context context, long section_id, String custom_action){
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getElements(context, section_id, custom_action).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Asks elements for a particular section retrieving data through a listener
     */
    public static void askForElements(Context context, long section_id, MBApiElementsResultListener listener){
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_getElements(context, section_id, listener).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Vote for a poll and retrieve new answers with new indexes through action
     */
    public static void voteForPoll(Context context, long poll_id, int answer_index, ArrayList<MBAnswer> oldAnswers){
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_VotePoll(context, poll_id, answer_index, oldAnswers).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Vote of a poll and retrieve new answers with new indexes through listener
     */
    public static void voteForPoll(Context context, long poll_id, int answer_index,
                                   ArrayList<MBAnswer> oldAnswers, MBApiVotePollListener listener){
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_VotePoll(context, poll_id, answer_index, oldAnswers, listener).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Send a live message and retrieve if everything went ok through actions
     */
    public static void sendLiveMessage(Context context, String name, String text){
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_sendLiveMessage(context, name, text).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Send a live message and retrieve if everything went ok through listener
     */
    public static void sendLiveMessage(Context context, String name, String text, MBApiSendLiveMessageListener listener){
        if (MBUserConstants.apiKey != null) {
            new MBAsyncTask_sendLiveMessage(context, name, text, listener).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }
}
