package mumble.nooko3.sdk.Common.NKApiManager;

import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import fslogger.lizsoft.lv.fslogger.FSLogger;
import mumble.nooko3.BuildConfig;
import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Utilities with the {@link NKAPIManager3 NKAPIManager3 class}
 *
 * @author  Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKApiManagerUtils {

    /** Transforms ContentValues in a "GET" query */
    public static String getQuery(ContentValues values) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        Set<Map.Entry<String, Object>> s = values.valueSet();
        Iterator itr = s.iterator();

        while (itr.hasNext()) {
            Map.Entry me = (Map.Entry) itr.next();
            String key = me.getKey().toString();
            Object value = me.getValue();

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode((String) value, "UTF-8"));

            if (itr.hasNext()) {
                result.append("&");
            }
        }

        return result.toString();
    }

    /** Transforms ContentValues in a "GET" query with the ? symbol*/
    public static String getGETQuery(ContentValues values) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder("?");

        Set<Map.Entry<String, Object>> s = values.valueSet();
        Iterator itr = s.iterator();

        while (itr.hasNext()) {
            Map.Entry me = (Map.Entry) itr.next();
            String key = me.getKey().toString();
            Object value = me.getValue();

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode((String) value, "UTF-8"));

            if (itr.hasNext()) {
                result.append("&");
            }
        }

        return result.toString();
    }

    /** Creates API string for Caching helper*/
    public static String createApiValuesString(String api, ContentValues values) {
        StringBuilder builder = new StringBuilder(api);
        builder.append(createValuesString(values));
        return builder.toString();
    }

    private static String createValuesString(ContentValues values) {
        StringBuilder builder = new StringBuilder();

        Set<Map.Entry<String, Object>> s = values.valueSet();
        Iterator itr = s.iterator();

        while (itr.hasNext()) {
            Map.Entry me = (Map.Entry) itr.next();
            String key = me.getKey().toString();
            String value = (String) me.getValue();
            builder.append(key);
            builder.append(value);
        }

        return builder.toString();
    }

    /** Checks if API has gone well or not*/
    public static boolean hasMapOkResults(Map<String, Object> map, boolean payload) {
        if (map != null) {
            if (map.containsKey(NKApiManagerConfig.AM_RESULT)) {
                if ((int) map.get(NKApiManagerConfig.AM_RESULT) == NKApiManagerConfig.RESULT_OK) {
                    if (!map.containsKey(NKApiManagerConfig.AM_ERROR)) {
                        if (payload) {
                            if (map.containsKey(NKApiManagerConfig.AM_PAYLOAD)) {
                                return true;
                            }
                        } else {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /** Logs a string if not on release*/
    public static void doLogging(String log) {
        if (BuildConfig.DEBUG) {
            FSLogger.logout(log);
        }
    }

    /** Installs auxiliar certificates not included in Andorid versions pre 19*/
    public static void installCertificates(Context mContext) {
        try {
            ProviderInstaller.installIfNeeded(mContext.getApplicationContext());
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    /** Checks if Internet connection is available*/
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    /** Checks HTTP response to be ok or not*/
    public static boolean checkResponse(int response) {
        if ((response < 400) && (response >= 200)) {
            return true;
        } else {
            return false;
        }
    }
}
