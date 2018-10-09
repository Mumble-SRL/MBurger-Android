package mumble.mburger.sdk.MBAuth;

import android.content.Context;
import android.net.Uri;

import org.json.JSONArray;

import mumble.mburger.R;
import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBUserConstants;
import mumble.mburger.sdk.Common.MBExceptions.MBSDKInitializeException;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_Authenticate;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_AuthenticateSocial;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_ChangePassword;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_DeleteProfile;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_ForgotPassword;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_Profile;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_Register;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_UpdateProfile;
import mumble.mburger.sdk.MBAuth.MBAuthResultsListener.MBAuthApiAuthenticateListener;
import mumble.mburger.sdk.MBAuth.MBAuthResultsListener.MBAuthApiChangePasswordListener;
import mumble.mburger.sdk.MBAuth.MBAuthResultsListener.MBAuthApiDeleteProfileListener;
import mumble.mburger.sdk.MBAuth.MBAuthResultsListener.MBAuthApiForgotPasswordListener;
import mumble.mburger.sdk.MBAuth.MBAuthResultsListener.MBAuthApiProfileListener;
import mumble.mburger.sdk.MBAuth.MBAuthResultsListener.MBAuthApiProfileUpdateListener;
import mumble.mburger.sdk.MBAuth.MBAuthResultsListener.MBAuthApiRegisterListener;

public class MBurgerAuthTasks {

    /**
     * Authenticate an user
     */
    public static void authenticateUser(Context context, String email, String password) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_Authenticate(context, email, password).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Authenticate an user with custom action callback
     */
    public static void authenticateUser(Context context, String custom_action, String email, String password) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_Authenticate(context, custom_action, email, password).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Authenticate an user with listener callback
     */
    public static void authenticateUser(Context context, MBAuthApiAuthenticateListener listener, String email, String password) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_Authenticate(context, listener, email, password).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Authenticate an user
     */
    public static void authenticateUserWithSocial(Context context, String token, int social_type) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_AuthenticateSocial(context, token, social_type).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Authenticate an user with custom action callback
     */
    public static void authenticateUserWithSocial(Context context, String custom_action, String token, int social_type) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_AuthenticateSocial(context, custom_action, token, social_type).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Authenticate an user with listener callback
     */
    public static void authenticateUserWithSocial(Context context, MBAuthApiAuthenticateListener listener, String token, int social_type) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_AuthenticateSocial(context, listener, token, social_type).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Register an user
     */
    public static void registerUser(Context context, String name, String surname, String phone, Uri image,
                                    String email, String password, JSONArray contracts, String data) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_Register(context, name, surname, phone, image, email, password, contracts, data).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Register an user with custom action callback
     */
    public static void registerUser(Context context, String custom_action, String name, String surname, String phone, Uri image,
                                    String email, String password, JSONArray contracts, String data) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_Register(context, custom_action, name, surname, phone, image, email, password, contracts, data).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Register an user with listener callback
     */
    public static void registerUser(Context context, MBAuthApiRegisterListener listener, String name, String surname, String phone, Uri image,
                                    String email, String password, JSONArray contracts, String data) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_Register(context, listener, name, surname, phone, image, email, password, contracts, data).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Obtain user profile
     */
    public static void getLoggedUserProfile(Context context) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_Profile(context).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Obtain user profile with custom action callback
     */
    public static void getLoggedUserProfile(Context context, String custom_action) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_Profile(context, custom_action).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Obtain user profile with listener callback
     */
    public static void getLoggedUserProfile(Context context, MBAuthApiProfileListener listener) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_Profile(context, listener).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Request a password recovery for the given email
     */
    public static void requestPasswordRecovery(Context context, String email) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_ForgotPassword(context, email).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Request a password recovery for the given email with custom action callback
     */
    public static void requestPasswordRecovery(Context context, String custom_action, String email) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_ForgotPassword(context, custom_action, email).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Request a password recovery for the given email with listener callback
     */
    public static void requestPasswordRecovery(Context context, MBAuthApiForgotPasswordListener listener, String email) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_ForgotPassword(context, listener, email).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Change old password with new password
     */
    public static void changePassword(Context context, String old_password, String new_password) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_ChangePassword(context, old_password, new_password).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Change old password with new password with custom action callback
     */
    public static void changePassword(Context context, String custom_action, String old_password, String new_password) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_ChangePassword(context, custom_action, old_password, new_password).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Change old password with new password with listener callback
     */
    public static void changePassword(Context context, MBAuthApiChangePasswordListener listener, String old_password, String new_password) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_ChangePassword(context, listener, old_password, new_password).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Update logged user profile an user
     */
    public static void updateProfile(Context context, String name, String surname, String phone, Uri image,
                                     String email, JSONArray contracts, String data) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_UpdateProfile(context, name, surname, phone, image, email, contracts, data).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Update logged user profile an user with custom action callback
     */
    public static void updateProfile(Context context, String custom_action, String name, String surname, String phone, Uri image,
                                     String email, JSONArray contracts, String data) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_UpdateProfile(context, custom_action, name, surname, phone, image, email, contracts, data).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Register an user with listener callback
     */
    public static void updateProfile(Context context, MBAuthApiProfileUpdateListener listener, String name,
                                     String surname, String phone, Uri image, String email, JSONArray contracts, String data) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_UpdateProfile(context, listener, name, surname, phone, image, email, contracts, data).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Deletes an user profile an user
     */
    public static void deleteProfile(Context context) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_DeleteProfile(context).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Deletes an user profile an user with custom action callback
     */
    public static void deleteProfile(Context context, String custom_action) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_DeleteProfile(context, custom_action).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Deletes an user with listener callback
     */
    public static void deleteProfile(Context context, MBAuthApiDeleteProfileListener listener) {
        if (MBUserConstants.apiKey != null) {
            new MBAuthAsyncTask_DeleteProfile(context, listener).execute();
        } else {
            throw new MBSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Clears a logged in user token
     */
    public static void clearAuthToken(Context context) {
        MBCommonMethods.removeAccessToken(context);
    }

}
