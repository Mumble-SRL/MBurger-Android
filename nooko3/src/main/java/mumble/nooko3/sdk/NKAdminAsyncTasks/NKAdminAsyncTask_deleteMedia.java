package mumble.nooko3.sdk.NKAdminAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.nooko3.sdk.NKConstants.NKAPIConstants;
import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKControllers.NKAdminResultsListeners.NKAdminApiDeleteMediaListener;
import mumble.nooko3.sdk.NKControllers.NKAdminResultsListeners.NKAdminApiDeleteSectionListener;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKAPIManager3;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiPayloadKeys;
import mumble.nooko3.sdk.NKControllers.NKCommonMethods;

/**
 * Task which will delete a media from a section, identified by the id, usable only if your token has "write" permission
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKAdminAsyncTask_deleteMedia extends AsyncTask<Void, Void, Void> {

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
    private String action = NKAPIConstants.ACTION_DELETE_MEDIA;

    /**
     * If you wish to use a listener to retrieve the data
     */
    private NKAdminApiDeleteMediaListener listener;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public NKAdminAsyncTask_deleteMedia(Context context, long media_id) {
        this.weakContext = new WeakReference<>(context);
        this.media_id = media_id;
    }

    public NKAdminAsyncTask_deleteMedia(Context context, String custom_action, long media_id) {
        this.weakContext = new WeakReference<>(context);
        this.media_id = media_id;
        this.action = custom_action;
    }

    public NKAdminAsyncTask_deleteMedia(Context context, NKAdminApiDeleteMediaListener listener, long media_id) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.media_id = media_id;
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

    protected void onPostExecute(Void postResult) {
        if (weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                i.putExtra(NKApiPayloadKeys.key_media_id, media_id);
                NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
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
        String api = NKApiManagerConfig.API + NKApiManagerConfig.API_MEDIA + "/" + Long.toString(media_id);
        map = NKAPIManager3.callApi(weakContext.get(), api, new ContentValues(), NKApiManagerConfig.MODE_DELETE, false);
    }
}
