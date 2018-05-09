package mumble.nooko3.sdk.NKAdmin.NKAdminData;

import java.io.Serializable;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Used for admin API
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKAdminParameter implements Serializable{

    private String key, value;

    public NKAdminParameter(String key, String value) {
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
