package mumble.nooko3.sdk.NConstants;

import java.util.concurrent.TimeUnit;

import mumble.nooko3.sdk.NControllers.CachingHelper;
import mumble.nooko3.sdk.Nooko3;

/**
 * Constants used with the SDK, editable by the developer initializing the SDK via class {@link Nooko3 Nooko3}.
 *
 * @author  Enrico Ori
 * @version {@value Const#version}
 */
public class UserConst {

    /**Default caching time for the data taken down by API (3 days)*/
    private static final long defaultCaching = TimeUnit.DAYS.toMillis(3);

    /**Public field for enabling offline caching inside the database {@link CachingHelper CachingHelper}, used with the APIManager*/
    public static boolean cachingEnabled = false;

    /**Public field for offline caching time, if data are older than this time, they are deleted from the database
     * {@link CachingHelper CachingHelper}*/
    public static long cachingTime = defaultCaching;


}
