package mumble.nooko3.sdk.NKPay.NKPayData;

import java.io.Serializable;

public class NKStripeSubscription implements Serializable{

    private long id;
    private String name;
    private String stripe_id;
    private String stripe_plan;
    private int quantity;
    private boolean ends_at;
    private boolean trial_ends_at;
    private long created_at;
    private boolean valid;
    private boolean trial;
    private boolean grace_period;
    private boolean cancelled;

    public NKStripeSubscription(long id, String name, String stripe_id, String stripe_plan, int quantity, boolean ends_at,
                                boolean trial_ends_at, long created_at, boolean valid, boolean trial,
                                boolean grace_period, boolean cancelled) {
        this.id = id;
        this.name = name;
        this.stripe_id = stripe_id;
        this.stripe_plan = stripe_plan;
        this.quantity = quantity;
        this.ends_at = ends_at;
        this.trial_ends_at = trial_ends_at;
        this.created_at = created_at;
        this.valid = valid;
        this.trial = trial;
        this.grace_period = grace_period;
        this.cancelled = cancelled;
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

    public String getStripe_id() {
        return stripe_id;
    }

    public void setStripe_id(String stripe_id) {
        this.stripe_id = stripe_id;
    }

    public String getStripe_plan() {
        return stripe_plan;
    }

    public void setStripe_plan(String stripe_plan) {
        this.stripe_plan = stripe_plan;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isEnds_at() {
        return ends_at;
    }

    public void setEnds_at(boolean ends_at) {
        this.ends_at = ends_at;
    }

    public boolean isTrial_ends_at() {
        return trial_ends_at;
    }

    public void setTrial_ends_at(boolean trial_ends_at) {
        this.trial_ends_at = trial_ends_at;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isTrial() {
        return trial;
    }

    public void setTrial(boolean trial) {
        this.trial = trial;
    }

    public boolean isGrace_period() {
        return grace_period;
    }

    public void setGrace_period(boolean grace_period) {
        this.grace_period = grace_period;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
