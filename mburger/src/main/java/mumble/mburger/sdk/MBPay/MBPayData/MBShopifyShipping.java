package mumble.mburger.sdk.MBPay.MBPayData;

import java.io.Serializable;

public class MBShopifyShipping implements Serializable{

    private long id;
    private String name;
    private String price;
    private long shipping_zone_id;

    public MBShopifyShipping(long id, String name, String price, long shipping_zone_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.shipping_zone_id = shipping_zone_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getShipping_zone_id() {
        return shipping_zone_id;
    }

    public void setShipping_zone_id(long shipping_zone_id) {
        this.shipping_zone_id = shipping_zone_id;
    }
}
