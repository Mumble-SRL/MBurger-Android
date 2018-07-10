package mumble.nooko3.sdk.NKPush;

import android.content.Context;

import org.json.JSONArray;

import mumble.nooko3.R;
import mumble.nooko3.sdk.Common.NKConstants.NKUserConstants;
import mumble.nooko3.sdk.Common.NKExceptions.NKSDKInitializeException;
import mumble.nooko3.sdk.NKPush.NKPushAsyncTasks.NKPushAsyncTask_RegisterTopics;
import mumble.nooko3.sdk.NKPush.NKPushAsyncTasks.NKPushAsyncTask_SendToken;
import mumble.nooko3.sdk.NKPush.NKPushResultsListener.NKPushRegisterTopicsListener;
import mumble.nooko3.sdk.NKPush.NKPushResultsListener.NKPushSendTokenListener;

public class Nooko3PushTasks {

    /**
     * Register device to receive push notifications
     */
    public static void sendToken(Context context, String device_id, String token) {
        if (NKUserConstants.apiKey != null) {
            new NKPushAsyncTask_SendToken(context, device_id, token).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Register device to receive push notifications with custom action callback
     */
    public static void sendToken(Context context, String custom_action, String device_id, String token) {
        if (NKUserConstants.apiKey != null) {
            new NKPushAsyncTask_SendToken(context, custom_action, device_id, token).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Register device to receive push notifications with listener callback
     */
    public static void sendToken(Context context, NKPushSendTokenListener listener, String device_id, String token) {
        if (NKUserConstants.apiKey != null) {
            new NKPushAsyncTask_SendToken(context, listener, device_id, token).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Register push channels
     */
    public static void registerTopics(Context context, String device_id, JSONArray topics) {
        if (NKUserConstants.apiKey != null) {
            new NKPushAsyncTask_RegisterTopics(context, device_id, topics).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Register push channels with custom action callback
     */
    public static void registerTopics(Context context, String custom_action, String device_id, JSONArray topics) {
        if (NKUserConstants.apiKey != null) {
            new NKPushAsyncTask_RegisterTopics(context, custom_action, device_id, topics).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }

    /**
     * Register push channels with listener callback
     */
    public static void registerTopics(Context context, NKPushRegisterTopicsListener listener, String device_id, JSONArray topics) {
        if (NKUserConstants.apiKey != null) {
            new NKPushAsyncTask_RegisterTopics(context, listener, device_id, topics).execute();
        } else {
            throw new NKSDKInitializeException(context.getString(R.string.exception_sdk_not_initialized));
        }
    }
}
