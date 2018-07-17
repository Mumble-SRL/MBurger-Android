package mumble.mburger.sdk.MBClient.MBApiFilters;

import java.io.Serializable;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Class to obatin sorted data by a key in a ascentent or descendent order
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBSortParameter implements Serializable {

    /**Key to filter for*/
    private String key;

    /**If the order is ascendent or not*/
    private boolean ascendent;

    public MBSortParameter(String key, boolean ascendent) {
        this.key = key;
        this.ascendent = ascendent;
    }

    /**Get the key to sort for*/
    public String getKey() {
        return key;
    }

    /**Set the key to sort for*/
    public void setKey(String key) {
        this.key = key;
    }

    /**Get if ascendent sorting or descendent*/
    public boolean isAscendent() {
        return ascendent;
    }

    /**Set if ascendent sorting or descendent*/
    public void setAscendent(boolean ascendent) {
        this.ascendent = ascendent;
    }
}