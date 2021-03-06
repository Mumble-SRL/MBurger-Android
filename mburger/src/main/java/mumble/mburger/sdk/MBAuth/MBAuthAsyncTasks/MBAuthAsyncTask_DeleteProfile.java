package mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.MBAuth.MBAuthResultsListener.MBAuthApiDeleteProfileListener;

/**
 * Created by Enrico on 29/08/2016.
 */
public class MBAuthAsyncTask_DeleteProfile extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    @NonNull
    private WeakReference<Context> weakContext;

    private String action = MBAPIConstants.ACTION_DELETE_PROFILE;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private MBAuthApiDeleteProfileListener listener;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public MBAuthAsyncTask_DeleteProfile(Context context) {
        this.weakContext = new WeakReference<>(context);
    }

    public MBAuthAsyncTask_DeleteProfile(Context context, String custom_action) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
    }

    public MBAuthAsyncTask_DeleteProfile(Context context, MBAuthApiDeleteProfileListener listener) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
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
        map = MBAPIManager3.callApi(weakContext.get(), MBApiManagerConfig.API_PROFILE_DELETE,
                values, MBApiManagerConfig.MODE_DELETE, false, false);
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
                    listener.onProfileDeletedError(error);
                } else {
                    listener.onProfileDeleted();
                }
            }
        }
    }

}

