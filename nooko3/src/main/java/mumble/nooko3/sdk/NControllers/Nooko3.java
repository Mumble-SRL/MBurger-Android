package mumble.nooko3.sdk.NControllers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;

import mumble.nooko3.sdk.NControllers.NApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMCONF;
import mumble.nooko3.sdk.UserConst;

/**
 * Basic init class for Nooko3, which sets an array of constants used runtime.
 *
 * @author Enrico Ori
 * @version {@value mumble.nooko3.sdk.Const#version}
 */
public class Nooko3 {

    /**
     * Initialize the SDK with the default parameters
     * Caching = false
     * CachingTime = 3 days
     */
    public static void initialize(Context context) {

    }

    /**
     * Initialize the SDK with user parameters about caching duration, default caching is ON
     */
    public static void initialize(Context context, long cachingDuration) {
        UserConst.cachingTime = cachingDuration;
    }

    /**
     * Initialize the SDK with user parameters about caching on/off
     */
    public static void initialize(Context context, boolean cachingEnabled) {
        UserConst.cachingEnabled = cachingEnabled;
    }

    /**
     * Initialize an activity/fragment to receive blocks from API
     */
    public static BroadcastReceiver initializeNookoReceiverForBlocks(Activity activity, ApiResultListener listener) {
        String[] receivers = new String[]{NAMCONF.ACTION_GET_BLOCK};
        return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
    }

    /**
     * Initialize an activity/fragment to receive sections from API
     */
    public static BroadcastReceiver initializeNookoReceiverForSections(Activity activity, ApiResultListener listener) {
        String[] receivers = new String[]{NAMCONF.ACTION_GET_SECTION};
        return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
    }

    /**
     * Initialize an activity/fragment to receive custom data with custom actions
     */
    public static BroadcastReceiver initializeNookoReceiverCustom(Activity activity, ApiResultListener listener, String[] receivers) {
        return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
    }

    /**
     * Removes the Nooko receiver from the activity/fragment, must pass BroadcastReceiver from initialization
     */
    public static void pauseNookoReceiver(Activity activity, BroadcastReceiver broadcastReceiver) {
        NAMActivityUtils.unregisterForApiManager(activity, broadcastReceiver);
    }
}
