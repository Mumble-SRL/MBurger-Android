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
import mumble.nooko3.sdk.NKAuth.NKAuthResultsListener.NKAuthApiForgotPasswordListener;
import mumble.nooko3.sdk.Common.NKCommonMethods;

/**
 * Created by Enrico on 29/08/2016.
 */
public class NKAuthAsyncTask_ForgotPassword extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    @NonNull
    private WeakReference<Context> weakContext;

    /**
     * Registration email
     */
    @NonNull
    private String email;

    private String action = NKAPIConstants.ACTION_FORGOT_PASSWORD;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private NKAuthApiForgotPasswordListener listener;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public NKAuthAsyncTask_ForgotPassword(Context context, String email) {
        this.weakContext = new WeakReference<>(context);
        this.email = email;
    }

    public NKAuthAsyncTask_ForgotPassword(Context context, String custom_action, String email) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.email = email;
    }

    public NKAuthAsyncTask_ForgotPassword(Context context, NKAuthApiForgotPasswordListener listener, String email) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.email = email;
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
        values.put("email", email);
        map = NKAPIManager3.callApi(weakContext.get(), NKApiManagerConfig.API_FORGOT_PASSWORD,
                values, NKApiManagerConfig.MODE_POST, false);
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
                    listener.onForgotPasswordRequestedError(error);
                } else {
                    listener.onForgotPasswordRequested();
                }
            }
        }
    }

}

