package mumble.nooko3.sdk.NControllers;

import android.content.Context;

import org.json.JSONObject;

import mumble.nooko3.R;
import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMCONF;

/**
 * A list of static methods used througout the SDK
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class NCommonMethods {

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
            case NAMCONF.COMMON_INTERNAL_ERROR:
                return context.getString(R.string.internal_error);

            case NAMCONF.COMMON_IOERROR:
                return context.getString(R.string.internal_error);

            case NAMCONF.COMMON_NOINTERNET:
                return context.getString(R.string.no_internet_error);

            case NAMCONF.COMMON_TIMEOUT:
                return context.getString(R.string.timeout_operation_error);

            default:
                return context.getString(R.string.internal_error);
        }
    }
}
