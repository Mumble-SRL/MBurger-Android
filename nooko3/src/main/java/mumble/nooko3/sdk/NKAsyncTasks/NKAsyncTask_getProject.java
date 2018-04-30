package mumble.nooko3.sdk.NKAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.nooko3.sdk.NKConstants.NKAPIConstants;
import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKAPIManager3;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiPayloadKeys;
import mumble.nooko3.sdk.NKControllers.NKApiResultsLIsteners.NKApiProjectResultListener;
import mumble.nooko3.sdk.NKControllers.NKCommonMethods;
import mumble.nooko3.sdk.NKControllers.NKParser;
import mumble.nooko3.sdk.NKData.NKProjects.NKProject;

/**
 * Task which returns the basic Nooko project data
 * Bundle will return object "project" which is {@link NKProject NKProject}
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKAsyncTask_getProject extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    private WeakReference<Context> weakContext;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = NKAPIConstants.ACTION_GET_PROJECT;

    /**
     * If you wish to use a listener to retrieve the data
     */
    private NKApiProjectResultListener listener;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private NKProject project;

    public NKAsyncTask_getProject(Context context) {
        this.weakContext = new WeakReference<>(context);
    }

    public NKAsyncTask_getProject(Context context, String custom_action) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
    }

    public NKAsyncTask_getProject(Context context, NKApiProjectResultListener listener) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        putValuesAndCall();
        if (NKApiManagerUtils.hasMapOkResults(map, true)) {
            getPayload((String) map.get(NKApiManagerConfig.AM_PAYLOAD));
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
                i.putExtra(NKApiPayloadKeys.key_project, project);
                NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onProjectApiError(error);
                } else {
                    listener.onProjectApiResult(project);
                }
            }
        }
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        map = NKAPIManager3.callApi(weakContext.get(), NKApiManagerConfig.API_PROJECT, values, NKApiManagerConfig.MODE_GET, true);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jBody = jPayload.getJSONObject("body");
            project = NKParser.parseProject(jBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
