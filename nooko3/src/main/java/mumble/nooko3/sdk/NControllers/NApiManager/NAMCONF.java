package mumble.nooko3.sdk.NControllers.NApiManager;

import mumble.nooko3.sdk.NConstants.NConst;

/**
 * Static fields for API configuration
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class NAMCONF {

    public static String endpoint = "http://nooko3.mumbleserver.it";
    public static String SERVER_HOSTNAME = "nooko3.mumbleserver.it";
    public static final String OS = "android";
    public static final String API_VERSION = "1";

    public static final int MODE_POST = -1;
    public static final int MODE_GET = -2;
    public static final int MODE_DELETE = -3;
    public static final int MODE_PUT = -4;

    /**
     * API
     */
    public static final String API_PROJECT = "project";
    public static final String API_BLOCK = "block";
    public static final String API_SECTION = "section";

    /**
     * ERRORS
     */
    public static final int STATUS_CODE_OK = 0;
    public static final int STATUS_CODE_LOGOUT = 55;
    public static final int RESULT_OK = 200;
    public static final int RESULT_REFRESH = 401;
    public static final int COMMON_IOERROR = -1000;
    public static final int COMMON_TIMEOUT = -1001;
    public static final int COMMON_INTERNAL_ERROR = -1003;
    public static final int COMMON_NOINTERNET = -1004;

    /**
     * APIMANAGER CONSTANTS
     */
    public static final String AM_RESULT = "result";
    public static final String AM_PAYLOAD = "payload";
    public static final String AM_ERROR = "error";

    public static final String ACTION_GET_PROJECT = "mumble.nooko3.ACTION_GET_PROJECT";
    public static final String ACTION_GET_BLOCKS = "mumble.nooko3.ACTION_GET_BLOCKS";
    public static final String ACTION_GET_BLOCK = "mumble.nooko3.ACTION_GET_BLOCK";
    public static final String ACTION_GET_SECTION = "mumble.nooko3.ACTION_GET_SECTION";
    public static final String ACTION_GET_SECTIONS = "mumble.nooko3.ACTION_GET_SECTIONS";
}

