package mumble.nooko3.sdk.Common.NKConstants;

import mumble.nooko3.sdk.NKAdmin.NKAdminAsyncTasks.NKAdminAsyncTask_deleteSection;
import mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks.NKAuthAsyncTask_Authenticate;
import mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks.NKAuthAsyncTask_Register;

public class NKAPIConstants {

    /**Used with the API {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_getProject NKAsyncTask_getProject}*/
    public static final String ACTION_GET_PROJECT = "mumble.nooko3.ACTION_GET_PROJECT";

    /**Used with the API {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_getBlocks NKAsyncTask_getBlocks}*/
    public static final String ACTION_GET_BLOCKS = "mumble.nooko3.ACTION_GET_BLOCKS";

    /**Used with the API {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_getBlock NKAsyncTask_getBlock}*/
    public static final String ACTION_GET_BLOCK = "mumble.nooko3.ACTION_GET_BLOCK";

    /**Used with the API {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_getSection NKAsyncTask_getSection}*/
    public static final String ACTION_GET_SECTION = "mumble.nooko3.ACTION_GET_SECTION";

    /**Used with the API {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_getSections NKAsyncTask_getSections}*/
    public static final String ACTION_GET_SECTIONS = "mumble.nooko3.ACTION_GET_SECTIONS";

    /**Used with the API {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_getElements NKAsyncTask_getElements}*/
    public static final String ACTION_GET_ELEMENTS = "mumble.nooko3.ACTION_GET_ELEMENTS";

    /**Used with the API {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_sendLiveMessage NKAsyncTask_sendLiveMessage}*/
    public static final String ACTION_SEND_LIVE_MESSAGE = "mumble.nooko3.ACTION_SEND_LIVE_MESSAGE";

    /**Used with the API {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_VotePoll NKAsyncTask_VotePoll}*/
    public static final String ACTION_VOTE_POLL = "mumble.nooko3.ACTION_VOTE_POLL";

    /**Used with the API {@link NKAdminAsyncTask_deleteSection NKAdminAsyncTask_deleteSection}*/
    public static final String ACTION_DELETE_SECTION = "mumble.nooko3.ACTION_DELETE_SECTION";

    /**Used with the API {@link mumble.nooko3.sdk.NKAdmin.NKAdminAsyncTasks.NKAdminAsyncTask_addSection NKAdminAsyncTask_addSection}*/
    public static final String ACTION_ADD_SECTION = "mumble.nooko3.ACTION_ADD_SECTION";

    /**Used with the API {@link mumble.nooko3.sdk.NKAdmin.NKAdminAsyncTasks.NKAdminAsyncTask_updateSection NKAdminAsyncTask_updateSection}*/
    public static final String ACTION_UPDATE_SECTION = "mumble.nooko3.ACTION_UPDATE_SECTION";

    /**Used with the API {@link mumble.nooko3.sdk.NKAdmin.NKAdminAsyncTasks.NKAdminAsyncTask_deleteMedia NKAdminAsyncTask_deleteMedia}*/
    public static final String ACTION_DELETE_MEDIA = "mumble.nooko3.ACTION_DELETE_MEDIA";

    /**Used with the API {@link NKAuthAsyncTask_Authenticate NKAuthAsyncTask_Authenticate}*/
    public static final String ACTION_AUTHENTICATE = "mumble.nooko3.ACTION_AUTHENTICATE";

    /**Used with the API {@link NKAuthAsyncTask_Register NKAuthAsyncTask_Register}*/
    public static final String ACTION_REGISTER = "mumble.nooko3.ACTION_REGISTER";

    /**Used with the API {@link mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks.NKAuthAsyncTask_Profile NKAuthAsyncTask_Profile}*/
    public static final String ACTION_PROFILE = "mumble.nooko3.ACTION_PROFILE";

    /**Used with the API {@link mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks.NKAuthAsyncTask_ForgotPassword NKAuthAsyncTask_ForgotPassword}*/
    public static final String ACTION_FORGOT_PASSWORD = "mumble.nooko3.ACTION_FORGOT_PASSWORD";

    /**Used with the API {@link mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks.NKAuthAsyncTask_ChangePassword NKAuthAsyncTask_ChangePassword}*/
    public static final String ACTION_CHANGE_PASSWORD = "mumble.nooko3.ACTION_CHANGE_PASSWORD";

    /**Used with the API {@link mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks.NKAuthAsyncTask_UpdateProfile NKAuthAsyncTask_UpdateProfile}*/
    public static final String ACTION_UPDATE_PROFILE = "mumble.nooko3.ACTION_UPDATE_PROFILE";

    /**Used with the API {@link mumble.nooko3.sdk.NKPay.NKPayAsyncTasks.NKPayAsyncTask_Subscribe NKPayAsyncTask_Subscribe}*/
    public static final String ACTION_SUBSCRIBE = "mumble.nooko3.ACTION_SUBSCRIBE";

    /**Used with the API {@link mumble.nooko3.sdk.NKPay.NKPayAsyncTasks.NKPayAsyncTask_CancelSubscription NKPayAsyncTask_CancelSubscription}*/
    public static final String ACTION_CANCEL_SUBSCRIPTION = "mumble.nooko3.ACTION_CANCEL_SUBSCRIPTION";

    /**Used with the API {@link mumble.nooko3.sdk.NKPay.NKPayAsyncTasks.NKPayAsyncTask_ResumeSubscription NKPayAsyncTask_ResumeSubscription}*/
    public static final String ACTION_RESUME_SUBSCRIPTION = "mumble.nooko3.ACTION_RESUME_SUBSCRIPTION";

    /**Used with the API {@link mumble.nooko3.sdk.NKPay.NKPayAsyncTasks.NKPayAsyncTask_AddCard NKPayAsyncTask_AddCard}*/
    public static final String ACTION_ADD_CARD = "mumble.nooko3.ACTION_ADD_CARD";

    /**Used with the API {@link mumble.nooko3.sdk.NKPay.NKPayAsyncTasks.NKPayAsyncTask_ChangeDefaultCard NKPayAsyncTask_ChangeDefaultCard}*/
    public static final String ACTION_CHANGE_DEFAULT_CARD = "mumble.nooko3.ACTION_CHANGE_DEFAULT_CARD";

    /**Used with the API {@link mumble.nooko3.sdk.NKPay.NKPayAsyncTasks.NKPayAsyncTask_DeleteCard NKPayAsyncTask_DeleteCard}*/
    public static final String ACTION_DELETE_CARD = "mumble.nooko3.ACTION_DELETE_CARD";

    /**Used with the API {@link mumble.nooko3.sdk.NKPay.NKPayAsyncTasks.NKPayAsyncTask_CreateCustomer NKPayAsyncTask_CreateCustomer}*/
    public static final String ACTION_CREATE_CUSTOMER = "mumble.nooko3.ACTION_CREATE_CUSTOMER";

    /**Used with the API {@link mumble.nooko3.sdk.NKPay.NKPayAsyncTasks.NKPayAsyncTask_GetCards NKPayAsyncTask_GetCards}*/
    public static final String ACTION_GET_CARDS = "mumble.nooko3.ACTION_GET_CARDS";

}
