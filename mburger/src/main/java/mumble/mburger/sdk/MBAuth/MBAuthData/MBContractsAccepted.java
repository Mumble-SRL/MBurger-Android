package mumble.mburger.sdk.MBAuth.MBAuthData;

import java.io.Serializable;

public class MBContractsAccepted implements Serializable{

    private long id;
    private boolean accepted;

    public MBContractsAccepted(long id, boolean accepted) {
        this.id = id;
        this.accepted = accepted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
