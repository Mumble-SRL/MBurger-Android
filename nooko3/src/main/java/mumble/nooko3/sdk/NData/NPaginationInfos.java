package mumble.nooko3.sdk.NData;

import java.io.Serializable;

public class NPaginationInfos implements Serializable{

    private int from;
    private int to;
    private int total;

    public NPaginationInfos(int from, int to, int total) {
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
