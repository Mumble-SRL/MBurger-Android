package mumble.nooko3.sdk.NKClient.NKApiFilters;

import java.io.Serializable;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Class to filter data retrievement through API for general purposes
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKGeneralParameter implements Serializable {

    /**Key to filter for*/
    private String key;

    /**Value which should be filtered for*/
    private String value;

    public NKGeneralParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**Get the key*/
    public String getKey() {
        return key;
    }

    /**Set the key*/
    public void setKey(String key) {
        this.key = key;
    }

    /**Get the value*/
    public String getValue() {
        return value;
    }

    /**Set the value*/
    public void setValue(String value) {
        this.value = value;
    }
}
