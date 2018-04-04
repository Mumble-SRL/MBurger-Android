package mumble.nooko3.sdk.NControllers.NApiManager.NApiFilters;

import java.io.Serializable;

/**
 * Class to obatin sorted data by a key in a ascentent or descendent order
 *
 * @author Enrico Ori
 * @version {@value mumble.nooko3.sdk.Const#version}
 */
public class NSortParam implements Serializable {

    /**Key to filter for*/
    private String key;

    /**If the order is ascendent or not*/
    private boolean ascendent;

    public NSortParam(String key, boolean ascendent) {
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