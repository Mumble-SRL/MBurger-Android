package mumble.nooko3.sdk.NKData;

import java.io.Serializable;

import mumble.nooko3.sdk.NKConstants.NKConstants;

/**
 * Gives information about pagination when calling APIs which will return arrays (blocks, sections, elements).
 *
 * @author  Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKPaginationInfo implements Serializable{

    /**Starting index for the pagination*/
    private int from;

    /**Max index for the pagination*/
    private int to;

    /**Number of elements gathered by the API*/
    private int total;

    public NKPaginationInfo(int from, int to, int total) {
        this.from = from;
        this.to = to;
        this.total = total;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
