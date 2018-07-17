package mumble.mburger.sdk.Common;

import android.app.Activity;
import android.content.BroadcastReceiver;

import mumble.mburger.R;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.Common.MBConstants.MBUserConstants;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBExceptions.MBSDKInitializeException;

public class MBurgerApiActionInitializer {

    /**
     * Initialize an activity/fragment to receive project informations from API
     */
    public static BroadcastReceiver initializeNookoReceiverForProject(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_GET_PROJECT};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive blocks from API
     */
    public static BroadcastReceiver initializeNookoReceiverForBlocks(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_GET_BLOCKS};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive a single block from API
     */
    public static BroadcastReceiver initializeNookoReceiverForBlock(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_GET_BLOCK};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive sections from API
     */
    public static BroadcastReceiver initializeNookoReceiverForSections(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_GET_SECTIONS};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive a single section from API
     */
    public static BroadcastReceiver initializeNookoReceiverForSection(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_GET_SECTION};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for deletion of a section from API
     */
    public static BroadcastReceiver initializeNookoReceiverForDeleteSection(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_DELETE_SECTION};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for adding a section from API
     */
    public static BroadcastReceiver initializeNookoReceiverForAddSection(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_ADD_SECTION};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for adding a section from API
     */
    public static BroadcastReceiver initializeNookoReceiverForDeleteMedia(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_DELETE_MEDIA};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for adding a section from API
     */
    public static BroadcastReceiver initializeNookoReceiverForUpdateSection(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_UPDATE_SECTION};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for authentication API
     */
    public static BroadcastReceiver initializeNookoReceiverForAuthentication(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_AUTHENTICATE};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for registration API
     */
    public static BroadcastReceiver initializeNookoReceiverForRegistration(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_REGISTER};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for password recovery API
     */
    public static BroadcastReceiver initializeNookoReceiverForPasswordRecovery(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_FORGOT_PASSWORD};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for password change API
     */
    public static BroadcastReceiver initializeNookoReceiverForPasswordChange(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_CHANGE_PASSWORD};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for profile retrivial API
     */
    public static BroadcastReceiver initializeNookoReceiverForProfile(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_PROFILE};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive custom data with custom actions
     */
    public static BroadcastReceiver initializeNookoReceiverCustom(Activity activity, MBGenericApiResultListener listener, String[] receivers) {
        if (MBUserConstants.apiKey != null) {
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Removes the Nooko receiver from the activity/fragment, must pass BroadcastReceiver from initialization
     */
    public static void pauseNookoReceiver(Activity activity, BroadcastReceiver broadcastReceiver) {
        if (MBUserConstants.apiKey != null) {
            MBAMActivityUtils.unregisterForApiManager(activity, broadcastReceiver);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }
}
