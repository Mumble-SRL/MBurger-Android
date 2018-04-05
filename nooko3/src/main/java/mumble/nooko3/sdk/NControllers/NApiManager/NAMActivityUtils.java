package mumble.nooko3.sdk.NControllers.NApiManager;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NControllers.NApiResultListener;
import mumble.nooko3.sdk.NData.APIResponse;

/**
 * Activity and Fragment utilities for retrieving data from Nooko3 APIs
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class NAMActivityUtils {

    /**
     * Initialize the listener for obtaining the API result when called, needs to be implemented in Activities or Fragments which retrieve data from API,
     * must be called in onResume and obtain the BroadcastReceiver
     */
    public static BroadcastReceiver initializeReceiverForApiManager(Activity activity, final NApiResultListener apiResultListener, String[] actions) {
        IntentFilter intentFilter = new IntentFilter();
        for (int i = 0; i < actions.length; i++) {
            intentFilter.addAction(actions[i]);
        }

        BroadcastReceiver bRec = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int result = intent.getExtras().getInt("result");
                String error = intent.getExtras().getString("error");
                boolean boolResult = result == NAMCONF.RESULT_OK;
                if (boolResult) {
                    if (error != null) {
                        boolResult = false;
                    }
                }

                apiResultListener.onApiResult(new APIResponse(boolResult, error, intent.getExtras()));
            }
        };
        try {
            LocalBroadcastManager.getInstance(activity).registerReceiver(bRec, intentFilter);
        } catch (IllegalArgumentException e) {
        }

        return bRec;
    }

    /**
     * Removes the listener for API from Activity/Fragment, must be called in onPause
     */
    public static void unregisterForApiManager(Activity activity, BroadcastReceiver broadcastReceiver) {
        try {
            LocalBroadcastManager.getInstance(activity).unregisterReceiver(broadcastReceiver);
        } catch (Exception e) {

        }
    }

    /**
     * Commodity class to send LocalBroadcastMessages, which is the method used for sending data from async API to
     * the user
     */
    public static void sendBroadcastMessage(Context context, Intent in) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(in);
    }

}
