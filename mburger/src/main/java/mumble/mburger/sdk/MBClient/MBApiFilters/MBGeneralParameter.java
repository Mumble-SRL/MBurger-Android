package mumble.mburger.sdk.MBClient.MBApiFilters;

import java.io.Serializable;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Class to filter data retrievement through API for general purposes
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBGeneralParameter implements Serializable {

    /**Key to filter for*/
    private String key;

    /**Value which should be filtered for*/
    private String value;

    public MBGeneralParameter(String key, String value) {
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
