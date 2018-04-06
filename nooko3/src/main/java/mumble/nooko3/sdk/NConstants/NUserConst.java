package mumble.nooko3.sdk.NConstants;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import mumble.nooko3.sdk.NControllers.NCachingHelper;
import mumble.nooko3.sdk.Nooko3;

/**
 * Constants used with the SDK, editable by the developer initializing the SDK via class {@link Nooko3 Nooko3}.
 *
 * @author  Enrico Ori
 * @version {@value NConst#version}
 */
public class NUserConst {

    /**API key for using the API's, initialized with {@link Nooko3#initialize(String)} method*/
    public static String apiKey = null;

    /**Default caching time for the data taken down by API (3 days)*/
    private static long defaultCaching = TimeUnit.DAYS.toMillis(3);

    /**Public field for enabling offline caching inside the database {@link NCachingHelper NCachingHelper}, used with the APIManager*/
    public static boolean cachingEnabled = false;

    /**Public field for offline caching time, if data are older than this time, they are deleted from the database
     * {@link NCachingHelper NCachingHelper}*/
    public static long cachingTime = defaultCaching;


}
