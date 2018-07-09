package mumble.nooko3.sdk.Common.NKApiManager;

import mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks.NKAuthAsyncTask_Authenticate;

/**
 * Constants used in AsyncTasks Broadcast Messages for data retrivial
 */
public class NKApiPayloadKeys {

    /**
     * Block key, used with {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_getBlock NKAsyncTask_getBlock} action data retrivial
     */
    public static String key_block = "block";

    /**
     * Blocks key, used with {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_getBlocks NKAsyncTask_getBlocks} action data retrivial
     */
    public static String key_blocks = "blocks";

    /**
     * Elements key, used with {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_getElements NKAsyncTask_getElements} action data retrivial
     */
    public static String key_elements = "elements";

    /**
     * Project key, used with {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_getProject NKAsyncTask_getProject} action data retrivial
     */
    public static String key_project = "project";

    /**
     * Section key, used with {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_getSection NKAsyncTask_getSection} action data retrivial
     */
    public static String key_section = "section";

    /**
     * Sections key, used with {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_getSections NKAsyncTask_getSections} action data retrivial
     */
    public static String key_sections = "sections";

    /**
     * Pagination key, used with all API that return an array of items
     */
    public static String key_pagination_infos = "paginationInfos";

    /**
     * Block id key, used with {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_getSections NKAsyncTask_getSections}
     */
    public static String key_block_id = "block_id";

    /**
     * Section id key, used with {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_getSection NKAsyncTask_getSection},
     * {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_getElements NKAsyncTask_getElements},
     * {@link mumble.nooko3.sdk.NKAdmin.NKAdminAsyncTasks.NKAdminAsyncTask_updateSection NKAdminAsyncTask_updateSection},
     * {@link mumble.nooko3.sdk.NKAdmin.NKAdminAsyncTasks.NKAdminAsyncTask_addSection NKAdminAsyncTask_addSection},
     */
    public static String key_section_id = "section_id";

    /**
     * Section id key, used with {@link mumble.nooko3.sdk.NKAdmin.NKAdminAsyncTasks.NKAdminAsyncTask_deleteMedia NKAdminAsyncTask_deleteMedia}
     */
    public static String key_media_id = "media_id";

    /**
     * My vote key, used with {@link mumble.nooko3.sdk.NKClient.NKAsyncTasks.NKAsyncTask_VotePoll NKAsyncTask_VotePoll}
     */
    public static String key_my_vote = "mine";

    /**
     * My vote key, used with {@link NKAuthAsyncTask_Authenticate NKAuthAsyncTask_Authenticate}
     */
    public static String key_jwt_token = "jwt_token";

    /**
     * My vote key, used with {@link mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks.NKAuthAsyncTask_Profile NKAuthAsyncTask_Profile}
     */
    public static String key_profile = "profile";

}