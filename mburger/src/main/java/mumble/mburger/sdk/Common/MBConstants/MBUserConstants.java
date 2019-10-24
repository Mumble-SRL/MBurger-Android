package mumble.mburger.sdk.Common.MBConstants;

import java.util.concurrent.TimeUnit;

import mumble.mburger.sdk.Common.MBCachingHelper;
import mumble.mburger.sdk.MBurger;

/**
 * Constants used with the SDK, editable by the developer initializing the SDK via class {@link MBurger MBurger}.
 *
 * @author  Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBUserConstants {

    /**API key for using the API's, initialized with {@link MBurger#initialize(String, boolean)} method*/
    public static String apiKey = null;

    /**Boolean that indicates if API are used in development mode, initialized with {@link MBurger#initialize(String, boolean)} method*/
    public static boolean devMode = false;

    /**Push API key obtained from MBurger Push project, initialized with {@link MBurger#initPush(String)} (String)} method*/
    public static String pushKey = null;

    /**Default caching time for the data taken down by API (3 days)*/
    private static long defaultCaching = TimeUnit.DAYS.toMillis(3);

    /**Public field for enabling offline caching inside the database {@link MBCachingHelper MBCachingHelper}, used with the APIManager*/
    public static boolean cachingEnabled = false;

    /**Public field for offline caching time, if data are older than this time, they are deleted from the database
     * {@link MBCachingHelper MBCachingHelper}*/
    public static long cachingTime = defaultCaching;


}
