package mumble.nooko3.sdk.NKControllers;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

import java.util.ArrayList;

import mumble.nooko3.R;
import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiFilters.NKFilterParameter;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiFilters.NKGeneralParameter;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiFilters.NKGeofenceParameter;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiFilters.NKPaginationParameter;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiFilters.NKSortParameter;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerConfig;

/**
 * A list of static methods used througout the SDK
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKCommonMethods {

    /**
     * Checks if a JSON has a key and is not null
     */
    public static boolean isJSONOk(JSONObject json, String key) {
        if (json.has(key)) {
            if (!json.isNull(key)) {
                return true;
            }
        }

        return false;
    }

    public static String getErrorMessageFromResult(Context context, int result) {
        switch (result) {
            case NKApiManagerConfig.COMMON_INTERNAL_ERROR:
                return context.getString(R.string.internal_error);

            case NKApiManagerConfig.COMMON_IOERROR:
                return context.getString(R.string.internal_error);

            case NKApiManagerConfig.COMMON_NOINTERNET:
                return context.getString(R.string.no_internet_error);

            case NKApiManagerConfig.COMMON_TIMEOUT:
                return context.getString(R.string.timeout_operation_error);

            default:
                return context.getString(R.string.internal_error);
        }
    }

    /**
     * Adds filters to API calls
     */
    public static void addFilters(ContentValues values, ArrayList<Object> filters) {
        if (filters != null) {
            for (Object object : filters) {
                if (object instanceof NKGeofenceParameter) {
                    NKGeofenceParameter gfParameter = (NKGeofenceParameter) object;
                    values.put("filter[elements.geofence]",
                            Double.toString(gfParameter.getLatitudeNE()) + ","
                                    + Double.toString(gfParameter.getLatitudeSW()) + ","
                                    + Double.toString(gfParameter.getLongitudeNE()) + ","
                                    + Double.toString(gfParameter.getLongitudeSW()));
                }

                if (object instanceof NKFilterParameter) {
                    NKFilterParameter filterParameter = (NKFilterParameter) object;
                    values.put("filter[" + filterParameter.getKey() + "]", filterParameter.getValue());
                }

                if (object instanceof NKSortParameter) {
                    NKSortParameter sortParameter = (NKSortParameter) object;
                    if (sortParameter.isAscendent()) {
                        values.put("sort", "-" + sortParameter.getKey());
                    } else {
                        values.put("sort", sortParameter.getKey());
                    }
                }

                if (object instanceof NKPaginationParameter) {
                    NKPaginationParameter pageParameter = (NKPaginationParameter) object;
                    values.put("skip", Integer.toString(pageParameter.getSkip()));
                    values.put("take", Integer.toString(pageParameter.getTake()));
                }

                if (object instanceof NKGeneralParameter) {
                    NKGeneralParameter generalParameter = (NKGeneralParameter) object;
                    values.put(generalParameter.getKey(), generalParameter.getValue());
                }
            }
        }
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        if (context != null) {
            SharedPreferences prefs = context.getSharedPreferences(NKConstants.PROPERTY_FILE, Context.MODE_PRIVATE);
            return prefs;
        }
        return null;
    }

    public static SharedPreferences.Editor getSharedPreferencesEditor(Context context) {
        if (context != null) {
            SharedPreferences prefs = context.getSharedPreferences(NKConstants.PROPERTY_FILE, Context.MODE_PRIVATE);
            return prefs.edit();
        }
        return null;
    }

    public static String getAccessToken(Context context) {
        String authToken;
        SharedPreferences prefs = context.getSharedPreferences(NKConstants.PROPERTY_FILE, Context.MODE_PRIVATE);
        authToken = prefs.getString(NKConstants.PROPERTY_ACCESS_TOKEN, "dummy");
        return authToken;
    }

    public static void setAccessToken(Context context, String jwt_token) {
        SharedPreferences.Editor editor = context.getSharedPreferences(NKConstants.PROPERTY_FILE, Context.MODE_PRIVATE).edit();
        editor.putString(NKConstants.PROPERTY_ACCESS_TOKEN, jwt_token).apply();
    }

}
