package mumble.mburger.sdk.MBPay.MBPayData;

import java.io.Serializable;

public class MBStripeCard implements Serializable{

    private String stripe_id;
    private String last4;
    private String brand;
    private int exp_month, exp_year;

    public MBStripeCard(String stripe_id, String last4, String brand, int exp_month, int exp_year) {
        this.stripe_id = stripe_id;
        this.last4 = last4;
        this.brand = brand;
        this.exp_month = exp_month;
        this.exp_year = exp_year;
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

    public int getExp_month() {
        return exp_month;
    }

    public void setExp_month(int exp_month) {
        this.exp_month = exp_month;
    }

    public int getExp_year() {
        return exp_year;
    }

    public void setExp_year(int exp_year) {
        this.exp_year = exp_year;
    }
}
