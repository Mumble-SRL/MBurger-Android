package mumble.mburger.sdk.MBAuth.MBAuthData;

import java.io.Serializable;

public class MBUserSection implements Serializable {

    private long id, block_id;
    private int order;
    private boolean visible;
    private long available_at, updated_at;

    public MBUserSection(long id, long block_id, int order, boolean visible, long available_at, long updated_at) {
        this.id = id;
        this.block_id = block_id;
        this.order = order;
        this.visible = visible;
        this.available_at = available_at;
        this.updated_at = updated_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBlock_id() {
        return block_id;
    }

    public void setBlock_id(long block_id) {
        this.block_id = block_id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public long getAvailable_at() {
        return available_at;
    }

    public void setAvailable_at(long available_at) {
        this.available_at = available_at;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }
}
