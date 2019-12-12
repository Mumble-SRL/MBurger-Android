package mumble.mburger.sdk.MBClient.MBApiFilters;

import java.io.Serializable;
import java.util.ArrayList;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Class to filter data retrievement through API
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBFilterIds implements Serializable {

    /**Value which should be filtered for*/
    private ArrayList<Long> values;

    public MBFilterIds(ArrayList<Long> values) {
        this.values = values;
    }

    /**Get the value*/
    public ArrayList<Long> getValue() {
        return values;
    }

    /**Set the value*/
    public void setValue(ArrayList<Long> value) {
        this.values = value;
    }
}
