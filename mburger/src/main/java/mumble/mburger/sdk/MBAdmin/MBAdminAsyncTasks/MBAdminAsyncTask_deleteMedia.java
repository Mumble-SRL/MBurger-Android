package mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBApiPayloadKeys;
import mumble.mburger.sdk.MBAdmin.MBAdminResultsListeners.MBAdminApiDeleteMediaListener;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;

/**
 * Task which will delete a media from a section, identified by the id, usable only if your token has "write" permission
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBAdminAsyncTask_deleteMedia extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    private WeakReference<Context> weakContext;

    /**
     * ID of the section to delete
     */
    private long media_id = -1;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = MBAPIConstants.ACTION_DELETE_MEDIA;

    /**
     * If you wish to use a listener to retrieve the data
     */
    private MBAdminApiDeleteMediaListener listener;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public MBAdminAsyncTask_deleteMedia(Context context, long media_id) {
        this.weakContext = new WeakReference<>(context);
        this.media_id = media_id;
    }

    public MBAdminAsyncTask_deleteMedia(Context context, String custom_action, long media_id) {
        this.weakContext = new WeakReference<>(context);
        this.media_id = media_id;
        this.action = custom_action;
    }

    public MBAdminAsyncTask_deleteMedia(Context context, MBAdminApiDeleteMediaListener listener, long media_id) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.media_id = media_id;
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

    protected void onPostExecute(Void postResult) {
        if (weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                i.putExtra(MBApiPayloadKeys.key_media_id, media_id);
                MBAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onMediaDeleteError(error);
                } else {
                    listener.onMediaDeleteSuccess(media_id);
                }
            }
        }
    }

    public void putValuesAndCall() {
        String api = MBApiManagerConfig.API + MBApiManagerConfig.API_MEDIA + "/" + Long.toString(media_id);
        map = MBAPIManager3.callApi(weakContext.get(), api, new ContentValues(), MBApiManagerConfig.MODE_DELETE, false, false);
    }
}
