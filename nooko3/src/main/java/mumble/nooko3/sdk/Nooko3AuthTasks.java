package mumble.nooko3.sdk;

import android.content.Context;
import android.net.Uri;

import mumble.nooko3.R;
import mumble.nooko3.sdk.NKAuthAsyncTasks.NKAuthAsyncTask_Authenticate;
import mumble.nooko3.sdk.NKAuthAsyncTasks.NKAuthAsyncTask_ChangePassword;
import mumble.nooko3.sdk.NKAuthAsyncTasks.NKAuthAsyncTask_ForgotPassword;
import mumble.nooko3.sdk.NKAuthAsyncTasks.NKAuthAsyncTask_Profile;
import mumble.nooko3.sdk.NKAuthAsyncTasks.NKAuthAsyncTask_Register;
import mumble.nooko3.sdk.NKConstants.NKUserConstants;
import mumble.nooko3.sdk.NKControllers.NKAuthResultsListener.NKAuthApiAuthenticateListener;
import mumble.nooko3.sdk.NKControllers.NKAuthResultsListener.NKAuthApiChangePasswordListener;
import mumble.nooko3.sdk.NKControllers.NKAuthResultsListener.NKAuthApiForgotPasswordListener;
import mumble.nooko3.sdk.NKControllers.NKAuthResultsListener.NKAuthApiProfileListener;
import mumble.nooko3.sdk.NKControllers.NKAuthResultsListener.NKAuthApiRegisterListener;
import mumble.nooko3.sdk.NKControllers.NKCommonMethods;
import mumble.nooko3.sdk.NKExceptions.NKSDKInitializeException;

public class Nooko3AuthTasks {

    /**
     * Authenticate an user
     */
    public static void loginUser(Context context, String email, String password) {
        if (NKUserConstants.apiKey != null) {
            new NKAuthAsyncTask_Authenticate(context, email, password).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Authenticate an user with custom action callback
     */
    public static void loginUser(Context context, String custom_action, String email, String password) {
        if (NKUserConstants.apiKey != null) {
            new NKAuthAsyncTask_Authenticate(context, custom_action, email, password).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Authenticate an user with listener callback
     */
    public static void loginUser(Context context, NKAuthApiAuthenticateListener listener, String email, String password) {
        if (NKUserConstants.apiKey != null) {
            new NKAuthAsyncTask_Authenticate(context, listener, email, password).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Register an user
     */
    public static void registerUser(Context context, String name, String surname, String phone, Uri image,
                                    String email, String password, String data) {
        if (NKUserConstants.apiKey != null) {
            new NKAuthAsyncTask_Register(context, name, surname, phone, image, email, password, data).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Register an user with custom action callback
     */
    public static void registerUser(Context context, String custom_action, String name, String surname, String phone, Uri image,
                                    String email, String password, String data) {
        if (NKUserConstants.apiKey != null) {
            new NKAuthAsyncTask_Register(context, custom_action, name, surname, phone, image, email, password, data).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Register an user with listener callback
     */
    public static void registerUser(Context context, NKAuthApiRegisterListener listener, String name, String surname, String phone, Uri image,
                                    String email, String password, String data) {
        if (NKUserConstants.apiKey != null) {
            new NKAuthAsyncTask_Register(context, listener, name, surname, phone, image, email, password, data).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Obtain user profile
     */
    public static void getLoggedUserProfile(Context context) {
        if (NKUserConstants.apiKey != null) {
            new NKAuthAsyncTask_Profile(context).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Obtain user profile with custom action callback
     */
    public static void getLoggedUserProfile(Context context, String custom_action) {
        if (NKUserConstants.apiKey != null) {
            new NKAuthAsyncTask_Profile(context, custom_action).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Obtain user profile with listener callback
     */
    public static void getLoggedUserProfile(Context context, NKAuthApiProfileListener listener) {
        if (NKUserConstants.apiKey != null) {
            new NKAuthAsyncTask_Profile(context, listener).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Request a password recovery for the given email
     */
    public static void requestPasswordRecovery(Context context, String email) {
        if (NKUserConstants.apiKey != null) {
            new NKAuthAsyncTask_ForgotPassword(context, email).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Request a password recovery for the given email with custom action callback
     */
    public static void requestPasswordRecovery(Context context, String custom_action, String email) {
        if (NKUserConstants.apiKey != null) {
            new NKAuthAsyncTask_ForgotPassword(context, custom_action, email).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Request a password recovery for the given email with listener callback
     */
    public static void requestPasswordRecovery(Context context, NKAuthApiForgotPasswordListener listener, String email) {
        if (NKUserConstants.apiKey != null) {
            new NKAuthAsyncTask_ForgotPassword(context, listener, email).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Change old password with new password
     */
    public static void changePassword(Context context, String old_password, String new_password) {
        if (NKUserConstants.apiKey != null) {
            new NKAuthAsyncTask_ChangePassword(context, old_password, new_password).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Change old password with new password with custom action callback
     */
    public static void changePassword(Context context, String custom_action, String old_password, String new_password) {
        if (NKUserConstants.apiKey != null) {
            new NKAuthAsyncTask_ChangePassword(context, custom_action, old_password, new_password).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Change old password with new password with listener callback
     */
    public static void changePassword(Context context, NKAuthApiChangePasswordListener listener, String old_password, String new_password) {
        if (NKUserConstants.apiKey != null) {
            new NKAuthAsyncTask_ChangePassword(context, listener, old_password, new_password).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Clears a logged in user token
     */
    public static void clearAuthToken(Context context){
        NKCommonMethods.removeAccessToken(context);
    }

}
