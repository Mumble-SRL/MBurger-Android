package mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.MBAuth.MBAuthResultsListener.MBAuthApiChangePasswordListener;
import mumble.mburger.sdk.Common.MBCommonMethods;

/**
 * Created by Enrico on 29/08/2016.
 */
public class MBAuthAsyncTask_ChangePassword extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    @NonNull
    private WeakReference<Context> weakContext;

    /**
     * User old password
     */
    @NonNull
    private String old_password;

    /**
     * User new password
     */
    @NonNull
    private String new_password;

    private String action = MBAPIConstants.ACTION_CHANGE_PASSWORD;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private MBAuthApiChangePasswordListener listener;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public MBAuthAsyncTask_ChangePassword(Context context, String old_password, String new_password) {
        this.weakContext = new WeakReference<>(context);
        this.old_password = old_password;
        this.new_password = new_password;
    }

    public MBAuthAsyncTask_ChangePassword(Context context, String custom_action, String old_password, String new_password) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.old_password = old_password;
        this.new_password = new_password;
    }

    public MBAuthAsyncTask_ChangePassword(Context context, MBAuthApiChangePasswordListener listener, String old_password, String new_password) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.old_password = old_password;
        this.new_password = new_password;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        putValuesAndCall();
        if (MBApiManagerUtils.hasMapOkResults(map, false)) {
            result = MBApiManagerConfig.RESULT_OK;
        } else {
            if (map.containsKey(MBApiManagerConfig.AM_RESULT)) {
                result = (int) map.get(MBApiManagerConfig.AM_RESULT);
            } else {
                result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
            }

            if (map.containsKey(MBApiManagerConfig.AM_ERROR)) {
                error = (String) map.get(MBApiManagerConfig.AM_ERROR);
            } else {
                error = MBCommonMethods.getErrorMessageFromResult(weakContext.get(), result);
            }
        }
        return null;
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        values.put("old_password", old_password);
        values.put("new_password", new_password);
        map = MBAPIManager3.callApi(weakContext.get(), MBApiManagerConfig.API_CHANGE_PASSWORD,
                values, MBApiManagerConfig.MODE_POST, false, false);
    }

    protected void onPostExecute(Void postResult) {
        if (weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                MBAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onPasswordChangedError(error);
                } else {
                    listener.onPasswordChanged();
                }
            }
        }
    }

}

