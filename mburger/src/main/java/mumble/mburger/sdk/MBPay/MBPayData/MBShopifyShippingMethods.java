package mumble.mburger.sdk.MBPay.MBPayData;

import java.util.ArrayList;

public class MBShopifyShippingMethods {

    private ArrayList<MBShopifyShipping> shipping_weights;
    private ArrayList<MBShopifyShipping> shipping_prices;

    public MBShopifyShippingMethods(ArrayList<MBShopifyShipping> shipping_weights, ArrayList<MBShopifyShipping> shipping_prices) {
        this.shipping_weights = shipping_weights;
        this.shipping_prices = shipping_prices;
    }

    public ArrayList<MBShopifyShipping> getShipping_weights() {
        return shipping_weights;
    }

    public void setShipping_weights(ArrayList<MBShopifyShipping> shipping_weights) {
        this.shipping_weights = shipping_weights;
    }

    public ArrayList<MBShopifyShipping> getShipping_prices() {
        return shipping_prices;
    }

    public void setShipping_prices(ArrayList<MBShopifyShipping> shipping_prices) {
        this.shipping_prices = shipping_prices;
    }
}
