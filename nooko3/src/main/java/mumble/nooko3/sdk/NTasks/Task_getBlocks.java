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
import java.util.ArrayList;
import java.util.Map;

import mumble.nooko3.sdk.NControllers.NApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMCONF;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAPIManager3;
import mumble.nooko3.sdk.NControllers.NParser;
import mumble.nooko3.sdk.NData.NBlocks.NBlock;

/**
 * Task which returns all blocks from the project
 * Bundle will return object "blocks" which is an ArrayList of {@link NBlock NBlocks}
 *
 * @author Enrico Ori
 * @version {@value mumble.nooko3.sdk.Const#version}
 */
public class Task_getBlocks extends AsyncTask<Void, Void, Void> {

    private WeakReference<Context> weakContext;
    private ArrayList<Object> filters;
    private boolean getSections = false, getElements = false;
    private String[] objectNames;
    private String action = NAMCONF.ACTION_GET_BLOCKS;

    private int result = NAMCONF.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private ArrayList<NBlock> blocks;

    public Task_getBlocks(Context context, ArrayList<Object> filters, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.getSections = getSections;
        this.filters = filters;
    }

    public Task_getBlocks(Context context, ArrayList<Object> filters, boolean getSections, boolean getElements, String[] objectNames) {
        this.weakContext = new WeakReference<>(context);
        this.getSections = getSections;
        this.getElements = getElements;
        this.objectNames = objectNames;
        this.filters = filters;
    }

    public Task_getBlocks(Context context, ArrayList<Object> filters, String custom_action, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.getSections = getSections;
        this.filters = filters;
    }

    public Task_getBlocks(Context context, ArrayList<Object> filters, String custom_action, boolean getSections, boolean getElements, String[] objectNames) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.getSections = getSections;
        this.getElements = getElements;
        this.objectNames = objectNames;
        this.filters = filters;
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
            i.putExtra("blocks", blocks);
            NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
        }
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        map = NAPIManager3.callApi(weakContext.get(), NAMCONF.API_BLOCK, values, NAMCONF.MODE_POST, true);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONArray jBlocks = jPayload.getJSONArray("items");
            blocks = new ArrayList<>();
            for (int i = 0; i < jBlocks.length(); i++) {
                blocks.add(NParser.parseBlock(jBlocks.getJSONObject(i), getSections, getElements, objectNames));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
