package mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiFilters;

import java.io.Serializable;

import mumble.nooko3.sdk.NKConstants.NKConstants;

/**
 * Class to paginate API calls
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKPaginationParameter implements Serializable {

    /**Starting point for the API list*/
    private int skip;

    /**How many items are needed*/
    private int take;

    public NKPaginationParameter(int skip, int take) {
        this.skip = skip;
        this.take = take;
    }

    /**Get the starting list point API*/
    public int getSkip() {
        return skip;
    }

    /**Set the starting list point API*/
    public void setSkip(int skip) {
        this.skip = skip;
    }

    /**Get how many items to obtain from API*/
    public int getTake() {
        return take;
    }

    /**Set how many items to obtain from API*/
    public void setTake(int take) {
        this.take = take;
    }
}
