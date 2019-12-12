package mumble.mburger.sdk.MBClient.MBApiFilters;

import java.io.Serializable;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Class to filter data retrievement through API
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBFilterEmail implements Serializable {

    /**Value which should be filtered for*/
    private String value;

    public MBFilterEmail(String value) {
        this.value = value;
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
