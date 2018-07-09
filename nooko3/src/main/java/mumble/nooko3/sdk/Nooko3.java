package mumble.nooko3.sdk;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;
import mumble.nooko3.sdk.Common.NKConstants.NKUserConstants;

/**
 * Basic init class for Nooko3, which sets an array of constants used runtime.
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class Nooko3 {

    /**
     * Initialize the SDK with the default parameters
     * Caching = false
     * CachingTime = 3 days
     * Development mode is an required option to choose
     */
    public static void initialize(String api_key, boolean devMode) {
        NKUserConstants.apiKey = api_key;
        NKUserConstants.devMode = devMode;
    }

    /**
     * Initialize the SDK with user parameters about caching on/off with the default caching time (3 days)
     */
    public static void initialize(String api_key, boolean devMode, boolean cachingEnabled) {
        NKUserConstants.apiKey = api_key;
        NKUserConstants.devMode = devMode;
        NKUserConstants.cachingEnabled = cachingEnabled;
    }

    /**
     * Initialize the SDK with user parameters about caching duration, default caching is OFF but this method turn it ON
     */
    public static void initialize(String api_key, boolean devMode, long cachingDuration) {
        NKUserConstants.apiKey = api_key;
        NKUserConstants.devMode = devMode;
        NKUserConstants.cachingEnabled = true;
        NKUserConstants.cachingTime = cachingDuration;
    }
}