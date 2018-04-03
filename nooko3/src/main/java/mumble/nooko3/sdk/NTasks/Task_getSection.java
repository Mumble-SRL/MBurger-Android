package mumble.nooko3.sdk.NTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.nooko3.sdk.NControllers.NApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMCONF;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAPIManager3;
import mumble.nooko3.sdk.NControllers.NParser;
import mumble.nooko3.sdk.NData.NSections.NSection;

/**
 * Task which returns a single section from a particular block
 * Bundle will return object "section" which is a {@link NSection NSection}
 *
 * @author Enrico Ori
 * @version {@value mumble.nooko3.sdk.Const#version}
 */
public class Task_getSection extends AsyncTask<Void, Void, Void> {

    private WeakReference<Context> weakContext;
    private String action = NAMCONF.ACTION_GET_SECTION;
    private boolean getElements = false;

    private int result = NAMCONF.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private NSection section;

    public Task_getSection(Context context, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.getElements = getElements;
    }

    public Task_getSection(Context context, String custom_action, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.getElements = getElements;
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
            Intent i = new Intent(action);
            i.putExtra("result", result);
            i.putExtra("error", error);
            i.putExtra("section", section);
            NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
        }
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        map = NAPIManager3.callApi(weakContext.get(), NAMCONF.API_SECTION, values, NAMCONF.MODE_POST, true);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONArray jSections = jPayload.getJSONArray("body");
            section = NParser.parseSection(jSections.getJSONArray(0), getElements);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
