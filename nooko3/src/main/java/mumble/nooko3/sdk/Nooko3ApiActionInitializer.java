package mumble.nooko3.sdk;

import android.app.Activity;
import android.content.BroadcastReceiver;

import mumble.nooko3.R;
import mumble.nooko3.sdk.NKConstants.NKUserConstants;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.NKControllers.NKGenericApiResultListener;
import mumble.nooko3.sdk.NKExceptions.NKSDKInitializeException;

public class Nooko3ApiActionInitializer {

    /**
     * Initialize an activity/fragment to receive project informations from API
     */
    public static BroadcastReceiver initializeNookoReceiverForProject(Activity activity, NKGenericApiResultListener listener) {
        if (NKUserConstants.apiKey != null) {
            String[] receivers = new String[]{NKApiManagerConfig.ACTION_GET_PROJECT};
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
            String[] receivers = new String[]{NKApiManagerConfig.ACTION_GET_BLOCKS};
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
            String[] receivers = new String[]{NKApiManagerConfig.ACTION_GET_BLOCK};
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
            String[] receivers = new String[]{NKApiManagerConfig.ACTION_GET_SECTIONS};
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
            String[] receivers = new String[]{NKApiManagerConfig.ACTION_GET_SECTION};
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
