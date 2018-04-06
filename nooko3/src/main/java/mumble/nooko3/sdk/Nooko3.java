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
}