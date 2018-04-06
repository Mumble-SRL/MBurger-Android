package mumble.nooko3.sdk.NControllers;

import org.json.JSONObject;

import mumble.nooko3.sdk.NConstants.NConst;

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
}
