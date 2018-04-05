package mumble.nooko3.sdk.NControllers.NApiManager.NApiFilters;

import java.io.Serializable;

import mumble.nooko3.sdk.NConstants.NConst;

/**
 * Class to filter data retrievement through API
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class NFilterParam implements Serializable {

    /**Key to filter for*/
    private String key;

    /**Value which should be filtered for*/
    private String value;

    public NFilterParam(String key, String value) {
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
