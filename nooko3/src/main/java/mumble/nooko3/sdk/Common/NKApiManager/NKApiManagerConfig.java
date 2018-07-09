package mumble.nooko3.sdk.Common.NKApiManager;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Static fields for API configuration
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKApiManagerConfig {

    public static String endpoint = "https://nooko2.mumbleserver.it";
    public static String SERVER_HOSTNAME = "nooko2.mumbleserver.it";
    public static String endpoint_dev = "https://nooko2-dev.mumbleserver.it";
    public static String SERVER_HOSTNAME_DEV = "nooko2-dev.mumbleserver.it";

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
    public static final String API_PROFILE_UPDATE = "/api/profile/update";
    public static final String API_SUBSCRIBE = "/api/subscriptions";
    public static final String API_SUBSCRIBE_CANCEL = "/api/subscriptions/cancel";
    public static final String API_SUBSCRIBE_RESUME = "/api/subscriptions/resume";
    public static final String API_CARDS = "/api/cards";
    public static final String API_DEFAULT_PART = "/default";
    public static final String API_CUSTOMER = "/api/customer";

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

