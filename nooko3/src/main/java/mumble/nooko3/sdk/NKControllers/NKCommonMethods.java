package mumble.nooko3.sdk.NKControllers;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.Settings;
import android.util.Base64;

import com.scottyab.aescrypt.AESCrypt;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
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
        SharedPreferences prefs = context.getSharedPreferences(NKConstants.PROPERTY_FILE, Context.MODE_PRIVATE);
        String encryptedToken = prefs.getString(NKConstants.PROPERTY_ACCESS_TOKEN, "dummy");
        try {
            if(!encryptedToken.equals("dummy")) {
                String decryptedToken = AESCrypt.decrypt(getDeviceId(context) + context.getPackageName(), encryptedToken);
                return decryptedToken;
            }
        } catch (GeneralSecurityException e) {
            //handle error - could be due to incorrect password or tampered encryptedMsg
        }

        return "dummy";
    }

    public static boolean hasLoggedIn(Context context) {
        return (!getAccessToken(context).equals("dummy"));
    }

    public static void setAccessToken(Context context, String jwt_token) {
        SharedPreferences.Editor editor = context.getSharedPreferences(NKConstants.PROPERTY_FILE, Context.MODE_PRIVATE).edit();
        try {
            String encryptedToken = AESCrypt.encrypt(getDeviceId(context) + context.getPackageName(), jwt_token);
            editor.putString(NKConstants.PROPERTY_ACCESS_TOKEN, encryptedToken).apply();
        } catch (GeneralSecurityException e) {
            //handle error
        }
    }

    public static void removeAccessToken(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(NKConstants.PROPERTY_FILE, Context.MODE_PRIVATE).edit();
        editor.remove(NKConstants.PROPERTY_ACCESS_TOKEN).apply();
    }

    public static String fromUriToBase64(Context context, Uri uri) {
        InputStream imageStream = null;
        try {
            imageStream = context.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
        return encodeTobase64(yourSelectedImage);
    }

    private static String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.NO_WRAP);
    }

    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}
