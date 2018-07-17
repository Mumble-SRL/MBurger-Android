package mumble.mburger.sdk.MBPay;

import android.content.Context;

import mumble.mburger.R;
import mumble.mburger.sdk.Common.MBConstants.MBUserConstants;
import mumble.mburger.sdk.Common.MBExceptions.MBSDKInitializeException;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_AddCard;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_CancelSubscription;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_ChangeDefaultCard;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_CreateCustomer;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_GetCards;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_ResumeSubscription;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_Subscribe;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_DeleteCard;
import mumble.mburger.sdk.MBPay.MBPayResultsListener.MBPayApiAddCardListener;
import mumble.mburger.sdk.MBPay.MBPayResultsListener.MBPayApiCancelSubscriptionListener;
import mumble.mburger.sdk.MBPay.MBPayResultsListener.MBPayApiCardsListener;
import mumble.mburger.sdk.MBPay.MBPayResultsListener.MBPayApiChangeDefaultCardListener;
import mumble.mburger.sdk.MBPay.MBPayResultsListener.MBPayApiCreateCustomerListener;
import mumble.mburger.sdk.MBPay.MBPayResultsListener.MBPayApiDeleteCardListener;
import mumble.mburger.sdk.MBPay.MBPayResultsListener.MBPayApiResumeSubscriptionListener;
import mumble.mburger.sdk.MBPay.MBPayResultsListener.MBPayApiSubscribeListener;

public class MBurgerPayTasks {

    /**
     * Subscribe to a Stripe™ plan
     */
    public static void subscribeToPlan(Context context, String subscription, String token, String discount_code, String meta,
                                       int quantity, int trial_days) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_Subscribe(context, subscription, token, discount_code, meta, quantity, trial_days).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Subscribe to a Stripe™ plan with custom action callback
     */
    public static void subscribeToPlan(Context context, String custom_action, String subscription, String token,
                                        String discount_code, String meta, int quantity, int trial_days) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_Subscribe(context, custom_action, subscription, token, discount_code, meta, quantity,
                    trial_days).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Subscribe to a Stripe™ plan with listener callback
     */
    public static void subscribeToPlan(Context context, MBPayApiSubscribeListener listener, String subscription,
                                       String token, String discount_code, String meta, int quantity, int trial_days) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_Subscribe(context, listener, subscription, token, discount_code, meta, quantity,
                    trial_days).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Resume subscription to a Stripe™ plan
     */
    public static void resumePlan(Context context, String subscription) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_ResumeSubscription(context, subscription).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Resume subscription to a Stripe™ plan with custom action callback
     */
    public static void resumePlan(Context context, String custom_action, String subscription) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_ResumeSubscription(context, custom_action, subscription).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Resume subscription to a Stripe™ plan with listener callback
     */
    public static void resumePlan(Context context, MBPayApiResumeSubscriptionListener listener, String subscription) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_ResumeSubscription(context, listener, subscription).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Cancel subscription to a Stripe™ plan
     */
    public static void cancelPlan(Context context, String subscription) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_CancelSubscription(context, subscription).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Cancel subscription to a Stripe™ plan with custom action callback
     */
    public static void cancelPlan(Context context, String custom_action, String subscription) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_CancelSubscription(context, custom_action, subscription).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Cancel subscription to a Stripe™ plan with listener callback
     */
    public static void cancelPlan(Context context, MBPayApiCancelSubscriptionListener listener, String subscription) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_CancelSubscription(context, listener, subscription).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Change default customer Stripe™ card
     */
    public static void changeDefaultCard(Context context, String card_id) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_ChangeDefaultCard(context, card_id).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Change default customer Stripe™ card with custom action callback
     */
    public static void changeDefaultCard(Context context, String custom_action, String card_id) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_ChangeDefaultCard(context, custom_action, card_id).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Change default customer Stripe™ card with listener callback
     */
    public static void changeDefaultCard(Context context, MBPayApiChangeDefaultCardListener listener, String card_id) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_ChangeDefaultCard(context, listener, card_id).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Create a new Stripe™ customer
     */
    public static void createCustomer(Context context) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_CreateCustomer(context).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Create a new Stripe™ customer with custom action callback
     */
    public static void createCustomer(Context context, String custom_action) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_CreateCustomer(context, custom_action).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Create a new Stripe™ customer with listener callback
     */
    public static void createCustomer(Context context, MBPayApiCreateCustomerListener listener) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_CreateCustomer(context, listener).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Get all Stripe™ cards associated to the customer
     */
    public static void getCards(Context context) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_GetCards(context).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Get all Stripe™ cards associated to the customer with custom action callback
     */
    public static void getCards(Context context, String custom_action) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_GetCards(context, custom_action).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Get all Stripe™ cards associated to the customer with listener callback
     */
    public static void getCards(Context context, MBPayApiCardsListener listener) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_GetCards(context, listener).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Add a new Stripe™ card associated to the customer
     */
    public static void addCard(Context context, String token) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_AddCard(context, token).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Add a new Stripe™ card associated to the customer with custom action callback
     */
    public static void addCard(Context context, String custom_action, String token) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_AddCard(context, custom_action, token).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Add a new Stripe™ card associated to the customer with listener callback
     */
    public static void addCard(Context context, MBPayApiAddCardListener listener, String token) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_AddCard(context, listener, token).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Delete a card associated to the customer
     */
    public static void deleteCard(Context context, String card_id) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_DeleteCard(context, card_id).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Delete a card associated to the customer with custom action callback
     */
    public static void deleteCard(Context context, String custom_action, String card_id) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_DeleteCard(context, custom_action, card_id).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Delete a card associated to the customer with listener callback
     */
    public static void deleteCard(Context context, MBPayApiDeleteCardListener listener, String card_id) {
        if (MBUserConstants.apiKey != null) {
            new MBPayAsyncTask_DeleteCard(context, listener, card_id).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }
}
