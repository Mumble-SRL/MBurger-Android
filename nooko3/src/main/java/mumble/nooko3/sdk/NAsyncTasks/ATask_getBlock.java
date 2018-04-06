package mumble.nooko3.sdk.NAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMCONF;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAPIManager3;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiBlockResultListener;
import mumble.nooko3.sdk.NControllers.NCommonMethods;
import mumble.nooko3.sdk.NControllers.NParser;
import mumble.nooko3.sdk.NData.NBlocks.NBlock;

/**
 * Task which returns a single block from the project
 * Bundle will return object "block" which is a {@link NBlock NBlock}
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class ATask_getBlock extends AsyncTask<Void, Void, Void> {

    /**Context reference used to send data to Activity/Fragment*/
    private WeakReference<Context> weakContext;

    /**ID of the block asking for from API*/
    private long block_id = -1;

    /**If you wish to obtain the sections for each block*/
    private boolean getSections = false;

    /**If you wish to obtain the elements for each section in block, should be true only if getSections is true*/
    private boolean getElements = false;

    /**If you wish to change the action that accompanies the API result*/
    private String action = NAMCONF.ACTION_GET_BLOCK;

    /**If you wish to use a listener to retrieve the data*/
    private NApiBlockResultListener listener;

    private int result = NAMCONF.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private NBlock block;

    public ATask_getBlock(Context context, long block_id, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.block_id = block_id;
        this.getSections = getSections;
    }

    public ATask_getBlock(Context context, long block_id, boolean getSections, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.getSections = getSections;
        this.block_id = block_id;
        this.getElements = getElements;
    }

    public ATask_getBlock(Context context, long block_id, String custom_action, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.block_id = block_id;
        this.getSections = getSections;
    }

    public ATask_getBlock(Context context, long block_id, String custom_action, boolean getSections, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.block_id = block_id;
        this.getSections = getSections;
        this.getElements = getElements;
    }

    public ATask_getBlock(Context context, long block_id, NApiBlockResultListener listener, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.block_id = block_id;
        this.getSections = getSections;
    }

    public ATask_getBlock(Context context, long block_id, NApiBlockResultListener listener, boolean getSections, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.block_id = block_id;
        this.getSections = getSections;
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
                error = NCommonMethods.getErrorMessageFromResult(weakContext.get(), result);
            }
        }
        return null;
    }

    protected void onPostExecute(Void postResult) {
        if (weakContext != null) {
            if(listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                i.putExtra("block", block);
                NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            }
            else{
                if(error != null){
                    listener.onBlockApiError(error);
                }
                else{
                    listener.onBlockApiResult(block);
                }
            }
        }
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        String api = NAMCONF.API_BLOCK + "/" + Long.toString(block_id);
        if(getSections){
            if(getElements){
                values.put("include", "sections.elements");
            }
            else {
                values.put("include", "sections");
            }
        }

        map = NAPIManager3.callApi(weakContext.get(), api, values, NAMCONF.MODE_GET, true);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jBlock = jPayload.getJSONObject("body");
            block = NParser.parseBlock(jBlock, getSections, getElements);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
