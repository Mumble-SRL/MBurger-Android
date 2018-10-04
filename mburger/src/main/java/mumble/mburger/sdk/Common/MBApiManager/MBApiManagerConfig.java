package mumble.mburger.sdk.Common.MBApiManager;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Static fields for API configuration
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBApiManagerConfig {

    public static String endpoint = "https://mburger.cloud";
    public static String SERVER_HOSTNAME = "mburger.cloud";
    public static String endpoint_dev = "https://dev.mburger.cloud";
    public static String SERVER_HOSTNAME_DEV = "dev.mburger.cloud";
    public static final String endpoint_push = "https://push.mumbleserver.it";
    public static final String SERVER_HOSTNAME_PUSH = "push.mumbleserver.it";

    public static final int MODE_POST = -1;
    public static final int MODE_GET = -2;
    public static final int MODE_DELETE = -3;
    public static final int MODE_PUT = -4;

    /**
     * API
     */
    public static final String API = "/api";
    public static final String API_PROJECT = "/api/project";
    public static final String API_BLOCK = "/api/blocks";
    public static final String API_SECTION = "/sections";
    public static final String API_ELEMENTS = "/elements";
    public static final String API_SEND_LIVE_MESSAGE = "/api/send-live-message";
    public static final String API_VOTE_POLL = "/api/vote-poll";

    /**
     * Admin API
     */
    public static final String API_UPDATE = "/update";
    public static final String API_MEDIA = "/media";

    /**
     * Authenticate API
     */
    public static final String API_AUTHENTICATE = "/api/authenticate";
    public static final String API_REGISTER = "/api/register";
    public static final String API_CHANGE_PASSWORD = "/api/change-password";
    public static final String API_FORGOT_PASSWORD = "/api/forgot-password";
    public static final String API_PROFILE = "/api/profile";
    public static final String API_PROFILE_DELETE = "/api/profile/delete";
    public static final String API_PROFILE_UPDATE = "/api/profile/update";
    public static final String API_SUBSCRIBE = "/api/subscriptions";
    public static final String API_SUBSCRIBE_CANCEL = "/api/subscriptions/cancel";
    public static final String API_SUBSCRIBE_RESUME = "/api/subscriptions/resume";
    public static final String API_CARDS = "/api/cards";
    public static final String API_DEFAULT_PART = "/default";
    public static final String API_CUSTOMER = "/api/customer";

    /**
     * PUSH API
     */
    public static final String API_TOKENS_PUSH = "/api/tokens";
    public static final String API_REGISTER_TOPICS = "/api/register";
    public static final String API_UNREGISTER_TOPICS = "/api/unregister";
    public static final String API_UNREGISTER_ALL_TOPICS = "/api/unregister-all";

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
    public static final String AM_RESPONSE = "response";
    public static final String AM_ERROR = "error";
}

