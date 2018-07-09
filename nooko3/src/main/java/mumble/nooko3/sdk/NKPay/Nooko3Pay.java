package mumble.nooko3.sdk.NKPay;

import android.content.Context;

import mumble.nooko3.R;
import mumble.nooko3.sdk.Common.NKConstants.NKUserConstants;
import mumble.nooko3.sdk.Common.NKExceptions.NKSDKInitializeException;
import mumble.nooko3.sdk.NKPay.NKPayAsyncTasks.NKPayAsyncTask_CancelSubscription;
import mumble.nooko3.sdk.NKPay.NKPayAsyncTasks.NKPayAsyncTask_ChangeDefaultCard;
import mumble.nooko3.sdk.NKPay.NKPayAsyncTasks.NKPayAsyncTask_CreateCustomer;
import mumble.nooko3.sdk.NKPay.NKPayAsyncTasks.NKPayAsyncTask_GetCards;
import mumble.nooko3.sdk.NKPay.NKPayAsyncTasks.NKPayAsyncTask_ResumeSubscription;
import mumble.nooko3.sdk.NKPay.NKPayAsyncTasks.NKPayAsyncTask_Subscribe;
import mumble.nooko3.sdk.NKPay.NKPayResultsListener.NKPayApiCancelSubscriptionListener;
import mumble.nooko3.sdk.NKPay.NKPayResultsListener.NKPayApiCardsListener;
import mumble.nooko3.sdk.NKPay.NKPayResultsListener.NKPayApiChangeDefaultCardListener;
import mumble.nooko3.sdk.NKPay.NKPayResultsListener.NKPayApiCreateCustomerListener;
import mumble.nooko3.sdk.NKPay.NKPayResultsListener.NKPayApiResumeSubscriptionListener;
import mumble.nooko3.sdk.NKPay.NKPayResultsListener.NKPayApiSubscribeListener;

public class Nooko3Pay {

    /**
     * Subscribe to a Stripe™ plan
     */
    public static void subscribeToPlan(Context context, String subscription, String token, String discount_code, String meta,
                                       int quantity, int trial_days) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_Subscribe(context, subscription, token, discount_code, meta, quantity, trial_days).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Subscribe to a Stripe™ plan with custom action callback
     */
    public static void subscribeToPlan(Context context, String custom_action, String subscription, String token,
                                        String discount_code, String meta, int quantity, int trial_days) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_Subscribe(context, custom_action, subscription, token, discount_code, meta, quantity,
                    trial_days).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Subscribe to a Stripe™ plan with listener callback
     */
    public static void subscribeToPlan(Context context, NKPayApiSubscribeListener listener, String subscription,
                                        String token, String discount_code, String meta, int quantity, int trial_days) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_Subscribe(context, listener, subscription, token, discount_code, meta, quantity,
                    trial_days).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Resume subscription to a Stripe™ plan
     */
    public static void resumePlan(Context context, String subscription) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_ResumeSubscription(context, subscription).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Resume subscription to a Stripe™ plan with custom action callback
     */
    public static void resumePlan(Context context, String custom_action, String subscription) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_ResumeSubscription(context, custom_action, subscription).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Resume subscription to a Stripe™ plan with listener callback
     */
    public static void resumePlan(Context context, NKPayApiResumeSubscriptionListener listener, String subscription) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_ResumeSubscription(context, listener, subscription).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Cancel subscription to a Stripe™ plan
     */
    public static void cancelPlan(Context context, String subscription) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_CancelSubscription(context, subscription).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Cancel subscription to a Stripe™ plan with custom action callback
     */
    public static void cancelPlan(Context context, String custom_action, String subscription) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_CancelSubscription(context, custom_action, subscription).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Cancel subscription to a Stripe™ plan with listener callback
     */
    public static void cancelPlan(Context context, NKPayApiCancelSubscriptionListener listener, String subscription) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_CancelSubscription(context, listener, subscription).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Change default customer Stripe™ card
     */
    public static void changeDefaultCard(Context context, String card_id) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_ChangeDefaultCard(context, card_id).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Change default customer Stripe™ card with custom action callback
     */
    public static void changeDefaultCard(Context context, String custom_action, String card_id) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_ChangeDefaultCard(context, custom_action, card_id).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Change default customer Stripe™ card with listener callback
     */
    public static void changeDefaultCard(Context context, NKPayApiChangeDefaultCardListener listener, String card_id) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_ChangeDefaultCard(context, listener, card_id).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Create a new Stripe™ customer
     */
    public static void createCustomer(Context context) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_CreateCustomer(context).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Create a new Stripe™ customer with custom action callback
     */
    public static void createCustomer(Context context, String custom_action) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_CreateCustomer(context, custom_action).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Create a new Stripe™ customer with listener callback
     */
    public static void createCustomer(Context context, NKPayApiCreateCustomerListener listener) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_CreateCustomer(context, listener).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Get all Stripe™ cards associated to the customer
     */
    public static void getCards(Context context) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_GetCards(context).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Get all Stripe™ cards associated to the customer with custom action callback
     */
    public static void getCards(Context context, String custom_action) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_GetCards(context, custom_action).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Get all Stripe™ cards associated to the customer with listener callback
     */
    public static void getCards(Context context, NKPayApiCardsListener listener) {
        if (NKUserConstants.apiKey != null) {
            new NKPayAsyncTask_GetCards(context, listener).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }
}
