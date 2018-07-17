package mumble.mburger.sdk;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.Common.MBConstants.MBUserConstants;

/**
 * Basic init class for MBurger, which sets an array of constants used runtime.
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBurger {

    /**
     * Initialize the SDK with the default parameters
     * Caching = false
     * CachingTime = 3 days
     * Development mode is an required option to choose
     */
    public static void initialize(String api_key, boolean devMode) {
        MBUserConstants.apiKey = api_key;
        MBUserConstants.devMode = devMode;
    }

    /**
     * Initialize the SDK with user parameters about caching on/off with the default caching time (3 days)
     */
    public static void initialize(String api_key, boolean devMode, boolean cachingEnabled) {
        MBUserConstants.apiKey = api_key;
        MBUserConstants.devMode = devMode;
        MBUserConstants.cachingEnabled = cachingEnabled;
    }

    /**
     * Initialize the SDK with user parameters about caching duration, default caching is OFF but this method turn it ON
     */
    public static void initialize(String api_key, boolean devMode, long cachingDuration) {
        MBUserConstants.apiKey = api_key;
        MBUserConstants.devMode = devMode;
        MBUserConstants.cachingEnabled = true;
        MBUserConstants.cachingTime = cachingDuration;
    }

    public static void initPush(String token){
        MBUserConstants.pushKey = token;
    }
}