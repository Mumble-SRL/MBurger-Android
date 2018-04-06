package mumble.nooko3.sdk.NAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMCONF;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAPIManager3;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiProjectResultListener;
import mumble.nooko3.sdk.NControllers.NParser;
import mumble.nooko3.sdk.NData.NProjects.NProject;

/**
 * Task which returns the basic Nooko project data
 * Bundle will return object "project" which is {@link NProject NProject}
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class ATask_getProject extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    private WeakReference<Context> weakContext;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = NAMCONF.ACTION_GET_PROJECT;

    /**
     * If you wish to use a listener to retrieve the data
     */
    private NApiProjectResultListener listener;

    private int result = NAMCONF.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private NProject project;

    public ATask_getProject(Context context) {
        this.weakContext = new WeakReference<>(context);
    }

    public ATask_getProject(Context context, String custom_action) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
    }

    public ATask_getProject(Context context, NApiProjectResultListener listener) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        putValuesAndCall();
        if (NAMUtils.hasMapOkResults(map, true)) {
            getPayload((String) map.get(NAMCONF.AM_PAYLOAD));
            result = NAMCONF.RESULT_OK;
        } else {
            if (map.containsKey(NAMCONF.AM_RESULT)) {
                result = (int) map.get(NAMCONF.AM_RESULT);
            } else {
                result = NAMCONF.COMMON_INTERNAL_ERROR;
            }

            if (map.containsKey(NAMCONF.AM_ERROR)) {
                error = (String) map.get(NAMCONF.AM_ERROR);
            } else {
                result = NAMCONF.COMMON_INTERNAL_ERROR;
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
                i.putExtra("project", project);
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
        map = NAPIManager3.callApi(weakContext.get(), NAMCONF.API_PROJECT, values, NAMCONF.MODE_GET, true);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jBody = jPayload.getJSONObject("body");
            project = NParser.parseProject(jBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
