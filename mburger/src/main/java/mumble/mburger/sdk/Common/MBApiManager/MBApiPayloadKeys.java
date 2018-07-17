package mumble.mburger.sdk.Common.MBApiManager;

import mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks.MBAdminAsyncTask_addSection;
import mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks.MBAdminAsyncTask_deleteMedia;
import mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks.MBAdminAsyncTask_updateSection;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_Authenticate;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_VotePoll;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getBlock;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getBlocks;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getElements;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getProject;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getSection;
import mumble.mburger.sdk.MBClient.MBAsyncTasks.MBAsyncTask_getSections;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_Profile;

/**
 * Constants used in AsyncTasks Broadcast Messages for data retrivial
 */
public class MBApiPayloadKeys {

    /**
     * Block key, used with {@link MBAsyncTask_getBlock MBAsyncTask_getBlock} action data retrivial
     */
    public static String key_block = "block";

    /**
     * Blocks key, used with {@link MBAsyncTask_getBlocks MBAsyncTask_getBlocks} action data retrivial
     */
    public static String key_blocks = "blocks";

    /**
     * Elements key, used with {@link MBAsyncTask_getElements MBAsyncTask_getElements} action data retrivial
     */
    public static String key_elements = "elements";

    /**
     * Project key, used with {@link MBAsyncTask_getProject MBAsyncTask_getProject} action data retrivial
     */
    public static String key_project = "project";

    /**
     * Section key, used with {@link MBAsyncTask_getSection MBAsyncTask_getSection} action data retrivial
     */
    public static String key_section = "section";

    /**
     * Sections key, used with {@link MBAsyncTask_getSections MBAsyncTask_getSections} action data retrivial
     */
    public static String key_sections = "sections";

    /**
     * Pagination key, used with all API that return an array of items
     */
    public static String key_pagination_infos = "paginationInfos";

    /**
     * Block id key, used with {@link MBAsyncTask_getSections MBAsyncTask_getSections}
     */
    public static String key_block_id = "block_id";

    /**
     * Section id key, used with {@link MBAsyncTask_getSection MBAsyncTask_getSection},
     * {@link MBAsyncTask_getElements MBAsyncTask_getElements},
     * {@link MBAdminAsyncTask_updateSection MBAdminAsyncTask_updateSection},
     * {@link MBAdminAsyncTask_addSection MBAdminAsyncTask_addSection},
     */
    public static String key_section_id = "section_id";

    /**
     * Section id key, used with {@link MBAdminAsyncTask_deleteMedia MBAdminAsyncTask_deleteMedia}
     */
    public static String key_media_id = "media_id";

    /**
     * My vote key, used with {@link MBAsyncTask_VotePoll MBAsyncTask_VotePoll}
     */
    public static String key_my_vote = "mine";

    /**
     * My vote key, used with {@link MBAuthAsyncTask_Authenticate MBAuthAsyncTask_Authenticate}
     */
    public static String key_jwt_token = "jwt_token";

    /**
     * My vote key, used with {@link MBAuthAsyncTask_Profile MBAuthAsyncTask_Profile}
     */
    public static String key_profile = "profile";

}
