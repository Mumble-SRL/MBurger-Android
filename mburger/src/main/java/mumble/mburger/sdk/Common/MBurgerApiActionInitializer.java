package mumble.mburger.sdk.Common;

import android.app.Activity;
import android.content.BroadcastReceiver;

import mumble.mburger.R;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.Common.MBConstants.MBUserConstants;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBExceptions.MBSDKInitializeException;

public class MBurgerApiActionInitializer {

    /**
     * Removes the MBurger receiver from the activity/fragment, must pass BroadcastReceiver from initialization
     */
    public static void pauseMBurgerReceiver(Activity activity, BroadcastReceiver broadcastReceiver) {
        if (MBUserConstants.apiKey != null) {
            MBAMActivityUtils.unregisterForApiManager(activity, broadcastReceiver);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive project informations from API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForProject(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_GET_PROJECT};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive blocks from API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForBlocks(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_GET_BLOCKS};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive a single block from API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForBlock(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_GET_BLOCK};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive sections from API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForSections(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_GET_SECTIONS};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive a single section from API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForSection(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_GET_SECTION};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for deletion of a section from API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForDeleteSection(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_DELETE_SECTION};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for adding a section from API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForAddSection(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_ADD_SECTION};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for adding a section from API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForDeleteMedia(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_DELETE_MEDIA};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for adding a section from API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForUpdateSection(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_UPDATE_SECTION};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for authentication API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForAuthentication(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_AUTHENTICATE};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for registration API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForRegistration(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_REGISTER};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for password recovery API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForPasswordRecovery(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_FORGOT_PASSWORD};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for password change API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForPasswordChange(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_CHANGE_PASSWORD};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for profile retrivial API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForProfile(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_PROFILE};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for profile editing API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForEditProfile(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_UPDATE_PROFILE};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for adding credit card API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForAddCard(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_ADD_CARD};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for canceling subscription API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForCancelSubscription(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_CANCEL_SUBSCRIPTION};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for resume subscription API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForResumeSubscription(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_RESUME_SUBSCRIPTION};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for change default credit card API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForChangeDefaultCard(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_CHANGE_DEFAULT_CARD};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for create a Stripe customer API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForCreateCustomer(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_CREATE_CUSTOMER};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for delete credit card API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForDeleteCard(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_DELETE_CARD};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for getting credit card API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForGettingCard(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_GET_CARDS};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for subscribe to Stripe plan API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForSubscribe(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_SUBSCRIBE};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for sending InstanceID token API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForSendToken(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_SEND_TOKEN};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for register topics API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForRegisterTopics(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_REGISTER_TOPICS};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for unregister topics API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForUnregisterTopics(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_UNREGISTER_TOPICS};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive callback for unregister all topics API
     */
    public static BroadcastReceiver initializeMBurgerReceiverForUnregisterAllTopics(Activity activity, MBGenericApiResultListener listener) {
        if (MBUserConstants.apiKey != null) {
            String[] receivers = new String[]{MBAPIConstants.ACTION_UNREGISTER_ALL_TOPICS};
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Initialize an activity/fragment to receive custom data with custom actions
     */
    public static BroadcastReceiver initializeMBurgerReceiverCustom(Activity activity, MBGenericApiResultListener listener, String[] receivers) {
        if (MBUserConstants.apiKey != null) {
            return MBAMActivityUtils.initializeReceiverForApiManager(activity, listener, receivers);
        } else {
            throw new MBSDKInitializeException(activity.getString(R.string.exception_sdk_not_initialized));
        }
    }
}
