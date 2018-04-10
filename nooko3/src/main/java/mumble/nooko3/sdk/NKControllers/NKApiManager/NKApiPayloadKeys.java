package mumble.nooko3.sdk.NKControllers.NKApiManager;

/**
 * Constants used in AsyncTasks Broadcast Messages for data retrivial
 */
public class NKApiPayloadKeys {

    /**
     * Block key, used with {@link mumble.nooko3.sdk.NKAsyncTasks.NKAsyncTask_getBlock NKAsyncTask_getBlock} action data retrivial
     */
    public static String key_block = "block";

    /**
     * Blocks key, used with {@link mumble.nooko3.sdk.NKAsyncTasks.NKAsyncTask_getBlocks NKAsyncTask_getBlocks} action data retrivial
     */
    public static String key_blocks = "blocks";

    /**
     * Elements key, used with {@link mumble.nooko3.sdk.NKAsyncTasks.NKAsyncTask_getElements NKAsyncTask_getElements} action data retrivial
     */
    public static String key_elements = "elements";

    /**
     * Project key, used with {@link mumble.nooko3.sdk.NKAsyncTasks.NKAsyncTask_getProject NKAsyncTask_getProject} action data retrivial
     */
    public static String key_project = "project";

    /**
     * Section key, used with {@link mumble.nooko3.sdk.NKAsyncTasks.NKAsyncTask_getSection NKAsyncTask_getSection} action data retrivial
     */
    public static String key_section = "section";

    /**
     * Sections key, used with {@link mumble.nooko3.sdk.NKAsyncTasks.NKAsyncTask_getSections NKAsyncTask_getSections} action data retrivial
     */
    public static String key_sections = "sections";

    /**
     * Pagination key, used with all API that return an array of items
     */
    public static String key_pagination_infos = "paginationInfos";

}
