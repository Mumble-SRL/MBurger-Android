package mumble.nooko3.sdk.NKPay.NKPayData;

import java.io.Serializable;

public class NKStripeSubscription implements Serializable{

    private long id;
    private String name;
    private String stripe_id;
    private String stripe_plan;
    private int quantity;
    private long ends_at;
    private long trial_ends_at;
    private long created_at;
    private long expires_at;
    private boolean valid;
    private boolean trial;
    private boolean grace_period;
    private boolean cancelled;

    public NKStripeSubscription(long id, String name, String stripe_id, String stripe_plan, int quantity, long ends_at,
                                long trial_ends_at, long created_at, long expires_at, boolean valid, boolean trial,
                                boolean grace_period, boolean cancelled) {
        this.id = id;
        this.name = name;
        this.stripe_id = stripe_id;
        this.stripe_plan = stripe_plan;
        this.quantity = quantity;
        this.ends_at = ends_at;
        this.trial_ends_at = trial_ends_at;
        this.created_at = created_at;
        this.expires_at = expires_at;
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

    public long getEnds_at() {
        return ends_at;
    }

    public void setEnds_at(long ends_at) {
        this.ends_at = ends_at;
    }

    public long getTrial_ends_at() {
        return trial_ends_at;
    }

    public void setTrial_ends_at(long trial_ends_at) {
        this.trial_ends_at = trial_ends_at;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(long expires_at) {
        this.expires_at = expires_at;
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
