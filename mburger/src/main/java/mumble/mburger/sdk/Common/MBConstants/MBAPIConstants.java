package mumble.mburger.sdk.Common.MBConstants;

import mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks.MBAdminAsyncTask_addSection;
import mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks.MBAdminAsyncTask_deleteMedia;
import mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks.MBAdminAsyncTask_deleteSection;
import mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks.MBAdminAsyncTask_updateSection;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_Authenticate;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_ChangePassword;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_ForgotPassword;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_Profile;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_Register;
import mumble.mburger.sdk.MBPush.MBPushAsyncTasks.MBPushAsyncTask_RegisterTopics;
import mumble.mburger.sdk.MBPush.MBPushAsyncTasks.MBPushAsyncTask_SendToken;
import mumble.mburger.sdk.MBPush.MBPushAsyncTasks.MBPushAsyncTask_UnregisterAllTopics;
import mumble.mburger.sdk.MBPush.MBPushAsyncTasks.MBPushAsyncTask_UnregisterTopics;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_AddCard;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_CancelSubscription;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_ChangeDefaultCard;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_CreateCustomer;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_DeleteCard;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_GetCards;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_ResumeSubscription;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_Subscribe;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_VotePoll;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getBlock;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getBlocks;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getElements;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getProject;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getSection;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getSections;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_sendLiveMessage;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_UpdateProfile;

public class MBAPIConstants {

    /**Used with the API {@link MBAsyncTask_getProject MBAsyncTask_getProject}*/
    public static final String ACTION_GET_PROJECT = "mumble.mburger.ACTION_GET_PROJECT";

    /**Used with the API {@link MBAsyncTask_getBlocks MBAsyncTask_getBlocks}*/
    public static final String ACTION_GET_BLOCKS = "mumble.mburger.ACTION_GET_BLOCKS";

    /**Used with the API {@link MBAsyncTask_getBlock MBAsyncTask_getBlock}*/
    public static final String ACTION_GET_BLOCK = "mumble.mburger.ACTION_GET_BLOCK";

    /**Used with the API {@link MBAsyncTask_getSection MBAsyncTask_getSection}*/
    public static final String ACTION_GET_SECTION = "mumble.mburger.ACTION_GET_SECTION";

    /**Used with the API {@link MBAsyncTask_getSections MBAsyncTask_getSections}*/
    public static final String ACTION_GET_SECTIONS = "mumble.mburger.ACTION_GET_SECTIONS";

    /**Used with the API {@link MBAsyncTask_getElements MBAsyncTask_getElements}*/
    public static final String ACTION_GET_ELEMENTS = "mumble.mburger.ACTION_GET_ELEMENTS";

    /**Used with the API {@link MBAsyncTask_sendLiveMessage MBAsyncTask_sendLiveMessage}*/
    public static final String ACTION_SEND_LIVE_MESSAGE = "mumble.mburger.ACTION_SEND_LIVE_MESSAGE";

    /**Used with the API {@link MBAsyncTask_VotePoll MBAsyncTask_VotePoll}*/
    public static final String ACTION_VOTE_POLL = "mumble.mburger.ACTION_VOTE_POLL";

    /**Used with the API {@link MBAdminAsyncTask_deleteSection MBAdminAsyncTask_deleteSection}*/
    public static final String ACTION_DELETE_SECTION = "mumble.mburger.ACTION_DELETE_SECTION";

    /**Used with the API {@link MBAdminAsyncTask_addSection MBAdminAsyncTask_addSection}*/
    public static final String ACTION_ADD_SECTION = "mumble.mburger.ACTION_ADD_SECTION";

    /**Used with the API {@link MBAdminAsyncTask_updateSection MBAdminAsyncTask_updateSection}*/
    public static final String ACTION_UPDATE_SECTION = "mumble.mburger.ACTION_UPDATE_SECTION";

    /**Used with the API {@link MBAdminAsyncTask_deleteMedia MBAdminAsyncTask_deleteMedia}*/
    public static final String ACTION_DELETE_MEDIA = "mumble.mburger.ACTION_DELETE_MEDIA";

    /**Used with the API {@link MBAuthAsyncTask_Authenticate MBAuthAsyncTask_Authenticate}*/
    public static final String ACTION_AUTHENTICATE = "mumble.mburger.ACTION_AUTHENTICATE";

    /**Used with the API {@link MBAuthAsyncTask_Register MBAuthAsyncTask_Register}*/
    public static final String ACTION_REGISTER = "mumble.mburger.ACTION_REGISTER";

    /**Used with the API {@link MBAuthAsyncTask_Profile MBAuthAsyncTask_Profile}*/
    public static final String ACTION_PROFILE = "mumble.mburger.ACTION_PROFILE";

    /**Used with the API {@link MBAuthAsyncTask_ForgotPassword MBAuthAsyncTask_ForgotPassword}*/
    public static final String ACTION_FORGOT_PASSWORD = "mumble.mburger.ACTION_FORGOT_PASSWORD";

    /**Used with the API {@link MBAuthAsyncTask_ChangePassword MBAuthAsyncTask_ChangePassword}*/
    public static final String ACTION_CHANGE_PASSWORD = "mumble.mburger.ACTION_CHANGE_PASSWORD";

    /**Used with the API {@link mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_DeleteProfile MBAuthAsyncTask_DeleteProfile}*/
    public static final String ACTION_DELETE_PROFILE = "mumble.mburger.ACTION_DELETE_PROFILE";

    /**Used with the API {@link MBAuthAsyncTask_UpdateProfile MBAuthAsyncTask_UpdateProfile}*/
    public static final String ACTION_UPDATE_PROFILE = "mumble.mburger.ACTION_UPDATE_PROFILE";

    /**Used with the API {@link mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_Logout MBAuthAsyncTask_Logout}*/
    public static final String ACTION_LOGOUT = "mumble.mburger.ACTION_LOGOUT";

    /**Used with the API {@link MBPayAsyncTask_Subscribe MBPayAsyncTask_Subscribe}*/
    public static final String ACTION_SUBSCRIBE = "mumble.mburger.ACTION_SUBSCRIBE";

    /**Used with the API {@link MBPayAsyncTask_CancelSubscription MBPayAsyncTask_CancelSubscription}*/
    public static final String ACTION_CANCEL_SUBSCRIPTION = "mumble.mburger.ACTION_CANCEL_SUBSCRIPTION";

    /**Used with the API {@link MBPayAsyncTask_ResumeSubscription MBPayAsyncTask_ResumeSubscription}*/
    public static final String ACTION_RESUME_SUBSCRIPTION = "mumble.mburger.ACTION_RESUME_SUBSCRIPTION";

    /**Used with the API {@link MBPayAsyncTask_AddCard MBPayAsyncTask_AddCard}*/
    public static final String ACTION_ADD_CARD = "mumble.mburger.ACTION_ADD_CARD";

    /**Used with the API {@link MBPayAsyncTask_ChangeDefaultCard MBPayAsyncTask_ChangeDefaultCard}*/
    public static final String ACTION_CHANGE_DEFAULT_CARD = "mumble.mburger.ACTION_CHANGE_DEFAULT_CARD";

    /**Used with the API {@link MBPayAsyncTask_DeleteCard MBPayAsyncTask_DeleteCard}*/
    public static final String ACTION_DELETE_CARD = "mumble.mburger.ACTION_DELETE_CARD";

    /**Used with the API {@link MBPayAsyncTask_CreateCustomer MBPayAsyncTask_CreateCustomer}*/
    public static final String ACTION_CREATE_CUSTOMER = "mumble.mburger.ACTION_CREATE_CUSTOMER";

    /**Used with the API {@link MBPayAsyncTask_GetCards MBPayAsyncTask_GetCards}*/
    public static final String ACTION_GET_CARDS = "mumble.mburger.ACTION_GET_CARDS";

    /**Used with the API {@link MBPushAsyncTask_SendToken MBPayAsyncTask_GetCards}*/
    public static final String ACTION_SEND_TOKEN = "mumble.mburger.ACTION_SEND_TOKEN";

    /**Used with the API {@link MBPushAsyncTask_RegisterTopics MBPushAsyncTask_RegisterTopics}*/
    public static final String ACTION_REGISTER_TOPICS = "mumble.mburger.ACTION_REGISTER_TOPICS";

    /**Used with the API {@link MBPushAsyncTask_UnregisterTopics MBPushAsyncTask_UnregisterTopics}*/
    public static final String ACTION_UNREGISTER_TOPICS = "mumble.mburger.ACTION_UNREGISTER_TOPICS";

    /**Used with the API {@link MBPushAsyncTask_UnregisterAllTopics MBPushAsyncTask_UnregisterAllTopics}*/
    public static final String ACTION_UNREGISTER_ALL_TOPICS = "mumble.mburger.ACTION_UNREGISTER_ALL_TOPICS";

}
