package mumble.nooko3.sdk.Common.NKConstants;

import java.util.concurrent.TimeUnit;

import mumble.nooko3.sdk.Common.NKCachingHelper;
import mumble.nooko3.sdk.Nooko3;

/**
 * Constants used with the SDK, editable by the developer initializing the SDK via class {@link Nooko3 Nooko3}.
 *
 * @author  Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKUserConstants {

    /**API key for using the API's, initialized with {@link Nooko3#initialize(String, boolean)} method*/
    public static String apiKey = null;

    /**Boolean that indicates if API are used in development mode, initialized with {@link Nooko3#initialize(String, boolean)} method*/
    public static boolean devMode = false;

    /**Push API key obtained from Nooko Push project, initialized with {@link Nooko3#initPush(String)} (String)} method*/
    public static String pushKey = null;

    /**Default caching time for the data taken down by API (3 days)*/
    private static long defaultCaching = TimeUnit.DAYS.toMillis(3);

    /**Public field for enabling offline caching inside the database {@link NKCachingHelper NKCachingHelper}, used with the APIManager*/
    public static boolean cachingEnabled = false;

    /**Public field for offline caching time, if data are older than this time, they are deleted from the database
     * {@link NKCachingHelper NKCachingHelper}*/
    public static long cachingTime = defaultCaching;


}
