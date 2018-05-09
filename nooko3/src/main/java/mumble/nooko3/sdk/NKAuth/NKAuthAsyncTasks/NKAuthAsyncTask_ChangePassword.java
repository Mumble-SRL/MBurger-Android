package mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.nooko3.sdk.Common.NKConstants.NKAPIConstants;
import mumble.nooko3.sdk.Common.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.Common.NKApiManager.NKAPIManager3;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.NKAuth.NKAuthResultsListener.NKAuthApiChangePasswordListener;
import mumble.nooko3.sdk.Common.NKCommonMethods;

/**
 * Created by Enrico on 29/08/2016.
 */
public class NKAuthAsyncTask_ChangePassword extends AsyncTask<Void, Void, Void> {

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

    private String action = NKAPIConstants.ACTION_CHANGE_PASSWORD;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private NKAuthApiChangePasswordListener listener;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public NKAuthAsyncTask_ChangePassword(Context context, String old_password, String new_password) {
        this.weakContext = new WeakReference<>(context);
        this.old_password = old_password;
        this.new_password = new_password;
    }

    public NKAuthAsyncTask_ChangePassword(Context context, String custom_action, String old_password, String new_password) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.old_password = old_password;
        this.new_password = new_password;
    }

    public NKAuthAsyncTask_ChangePassword(Context context, NKAuthApiChangePasswordListener listener, String old_password, String new_password) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.old_password = old_password;
        this.new_password = new_password;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        putValuesAndCall();
        if (NKApiManagerUtils.hasMapOkResults(map, false)) {
            result = NKApiManagerConfig.RESULT_OK;
        } else {
            if (map.containsKey(NKApiManagerConfig.AM_RESULT)) {
                result = (int) map.get(NKApiManagerConfig.AM_RESULT);
            } else {
                result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
            }

            if (map.containsKey(NKApiManagerConfig.AM_ERROR)) {
                error = (String) map.get(NKApiManagerConfig.AM_ERROR);
            } else {
                error = NKCommonMethods.getErrorMessageFromResult(weakContext.get(), result);
            }
        }
        return null;
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        values.put("old_password", old_password);
        values.put("new_password", new_password);
        values.put("user_id", "1");
        map = NKAPIManager3.callApi(weakContext.get(), NKApiManagerConfig.API_REGISTER, values, NKApiManagerConfig.MODE_POST, false);
    }

    protected void onPostExecute(Void postResult) {
        if (weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
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

