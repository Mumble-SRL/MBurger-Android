package mumble.mburger.sdk.MBAdmin.MBAdminData;

import java.io.Serializable;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Used for admin API
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBAdminParameter implements Serializable{

    private String key, value;

    public MBAdminParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
