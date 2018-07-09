package mumble.nooko3.sdk.NKPay.NKPayData;

import java.io.Serializable;

public class NKStripeCard implements Serializable{

    private String stripe_id;
    private String last4;
    private String brand;
    private long expiration_date;

    public NKStripeCard(String stripe_id, String last4, String brand, long expiration_date) {
        this.stripe_id = stripe_id;
        this.last4 = last4;
        this.brand = brand;
        this.expiration_date = expiration_date;
    }

    public String getStripe_id() {
        return stripe_id;
    }

    public void setStripe_id(String stripe_id) {
        this.stripe_id = stripe_id;
    }

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public long getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(long expiration_date) {
        this.expiration_date = expiration_date;
    }
}
