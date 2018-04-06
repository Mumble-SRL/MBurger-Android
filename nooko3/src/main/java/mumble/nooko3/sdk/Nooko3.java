package mumble.nooko3.sdk;

import android.app.Activity;
import android.content.BroadcastReceiver;

import mumble.nooko3.R;
import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NConstants.NUserConst;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMCONF;
import mumble.nooko3.sdk.NControllers.NGenericApiResultListener;
import mumble.nooko3.sdk.NExceptions.NSDKInitializeException;

/**
 * Basic init class for Nooko3, which sets an array of constants used runtime.
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class Nooko3 {

    /**
     * Initialize the SDK with the default parameters
     * Caching = false
     * CachingTime = 3 days
     */
    public static void initialize(String api_key) {
        NUserConst.apiKey = api_key;
    }

    /**
     * Initialize the SDK with user parameters about caching on/off with the default caching time (3 days)
     */
    public static void initialize(String api_key, boolean cachingEnabled) {
        NUserConst.apiKey = api_key;
        NUserConst.cachingEnabled = cachingEnabled;
    }

    /**
     * Initialize the SDK with user parameters about caching duration, default caching is OFF but this method turn it ON
     */
    public static void initialize(String api_key, long cachingDuration) {
        NUserConst.apiKey = api_key;
        NUserConst.cachingEnabled = true;
        NUserConst.cachingTime = cachingDuration;
    }

    /**
     * Initialize an activity/fragment to receive project informations from API
     */
    public static BroadcastReceiver initializeNookoReceiverForProject(Activity activity, NGenericApiResultListener listener) {
        if(NUserConst.apiKey != null) {
            String[] receivers = new String[]{NAMCONF.ACTION_GET_PROJECT};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        }
        else{
            throw new NSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive blocks from API
     */
    public static BroadcastReceiver initializeNookoReceiverForBlocks(Activity activity, NGenericApiResultListener listener) {
        if(NUserConst.apiKey != null) {
            String[] receivers = new String[]{NAMCONF.ACTION_GET_BLOCKS};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        }
        else{
            throw new NSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive a single block from API
     */
    public static BroadcastReceiver initializeNookoReceiverForBlock(Activity activity, NGenericApiResultListener listener) {
        if(NUserConst.apiKey != null) {
            String[] receivers = new String[]{NAMCONF.ACTION_GET_BLOCK};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        }
        else{
            throw new NSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive sections from API
     */
    public static BroadcastReceiver initializeNookoReceiverForSections(Activity activity, NGenericApiResultListener listener) {
        if(NUserConst.apiKey != null) {
            String[] receivers = new String[]{NAMCONF.ACTION_GET_SECTIONS};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        }
        else{
            throw new NSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive a single section from API
     */
    public static BroadcastReceiver initializeNookoReceiverForSection(Activity activity, NGenericApiResultListener listener) {
        if(NUserConst.apiKey != null) {
            String[] receivers = new String[]{NAMCONF.ACTION_GET_SECTION};
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        }
        else{
            throw new NSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive custom data with custom actions
     */
    public static BroadcastReceiver initializeNookoReceiverCustom(Activity activity, NGenericApiResultListener listener, String[] receivers) {
        if(NUserConst.apiKey != null) {
            return NAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        }
        else{
            throw new NSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Removes the Nooko receiver from the activity/fragment, must pass BroadcastReceiver from initialization
     */
    public static void pauseNookoReceiver(Activity activity, BroadcastReceiver broadcastReceiver) {
        if(NUserConst.apiKey != null) {
            NAMActivityUtils.unregisterForApiManager(activity, broadcastReceiver);
        }
        else{
            throw new NSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }
}