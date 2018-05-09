package mumble.nooko3.sdk;

import android.app.Activity;
import android.content.BroadcastReceiver;

import mumble.nooko3.R;
import mumble.nooko3.sdk.NKConstants.NKAPIConstants;
import mumble.nooko3.sdk.NKConstants.NKUserConstants;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiFilters.NKGeneralParameter;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.NKControllers.NKGenericApiResultListener;
import mumble.nooko3.sdk.NKExceptions.NKSDKInitializeException;

public class Nooko3ApiActionInitializer {

    /**
     * Initialize an activity/fragment to receive project informations from API
     */
    public static BroadcastReceiver initializeNookoReceiverForProject(Activity activity, NKGenericApiResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            String[] receivers = new String[]{NKAPIConstants.ACTION_GET_PROJECT};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new NKSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive blocks from API
     */
    public static BroadcastReceiver initializeNookoReceiverForBlocks(Activity activity, NKGenericApiResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            String[] receivers = new String[]{NKAPIConstants.ACTION_GET_BLOCKS};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new NKSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive a single block from API
     */
    public static BroadcastReceiver initializeNookoReceiverForBlock(Activity activity, NKGenericApiResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            String[] receivers = new String[]{NKAPIConstants.ACTION_GET_BLOCK};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new NKSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive sections from API
     */
    public static BroadcastReceiver initializeNookoReceiverForSections(Activity activity, NKGenericApiResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            String[] receivers = new String[]{NKAPIConstants.ACTION_GET_SECTIONS};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new NKSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive a single section from API
     */
    public static BroadcastReceiver initializeNookoReceiverForSection(Activity activity, NKGenericApiResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            String[] receivers = new String[]{NKAPIConstants.ACTION_GET_SECTION};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new NKSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for deletion of a section from API
     */
    public static BroadcastReceiver initializeNookoReceiverForDeleteSection(Activity activity, NKGenericApiResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            String[] receivers = new String[]{NKAPIConstants.ACTION_DELETE_SECTION};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new NKSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for adding a section from API
     */
    public static BroadcastReceiver initializeNookoReceiverForAddSection(Activity activity, NKGenericApiResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            String[] receivers = new String[]{NKAPIConstants.ACTION_ADD_SECTION};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new NKSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for adding a section from API
     */
    public static BroadcastReceiver initializeNookoReceiverForDeleteMedia(Activity activity, NKGenericApiResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            String[] receivers = new String[]{NKAPIConstants.ACTION_DELETE_MEDIA};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new NKSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for adding a section from API
     */
    public static BroadcastReceiver initializeNookoReceiverForUpdateSection(Activity activity, NKGenericApiResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            String[] receivers = new String[]{NKAPIConstants.ACTION_UPDATE_SECTION};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new NKSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for authentication API
     */
    public static BroadcastReceiver initializeNookoReceiverForAuthentication(Activity activity, NKGenericApiResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            String[] receivers = new String[]{NKAPIConstants.ACTION_AUTHENTICATE};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new NKSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for registration API
     */
    public static BroadcastReceiver initializeNookoReceiverForRegistration(Activity activity, NKGenericApiResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            String[] receivers = new String[]{NKAPIConstants.ACTION_REGISTER};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new NKSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for password recovery API
     */
    public static BroadcastReceiver initializeNookoReceiverForPasswordRecovery(Activity activity, NKGenericApiResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            String[] receivers = new String[]{NKAPIConstants.ACTION_FORGOT_PASSWORD};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new NKSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for password change API
     */
    public static BroadcastReceiver initializeNookoReceiverForPasswordChange(Activity activity, NKGenericApiResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            String[] receivers = new String[]{NKAPIConstants.ACTION_CHANGE_PASSWORD};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new NKSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for profile retrivial API
     */
    public static BroadcastReceiver initializeNookoReceiverForProfile(Activity activity, NKGenericApiResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            String[] receivers = new String[]{NKAPIConstants.ACTION_PROFILE};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new NKSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive custom data with custom actions
     */
    public static BroadcastReceiver initializeNookoReceiverCustom(Activity activity, NKGenericApiResultListener listener, String[] receivers) {
        if (NKUserConstants.apiKey != null) {
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new NKSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Removes the Nooko receiver from the activity/fragment, must pass BroadcastReceiver from initialization
     */
    public static void pauseNookoReceiver(Activity activity, BroadcastReceiver broadcastReceiver) {
        if (NKUserConstants.apiKey != null) {
            NAMActivityUtils.unregisterForApiManager(activity, broadcastReceiver);
        } else {
            throw new NKSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }
}
