package mumble.nooko3.sdk.NKAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKAPIManager3;
import mumble.nooko3.sdk.NKControllers.NKApiResultsLIsteners.NKApiBlockResultListener;
import mumble.nooko3.sdk.NKControllers.NKCommonMethods;
import mumble.nooko3.sdk.NKControllers.NKParser;
import mumble.nooko3.sdk.NKData.NKBlocks.NKBlock;

/**
 * Task which returns a single block from the project
 * Bundle will return object "block" which is a {@link NKBlock NKBlock}
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKAsyncTask_getBlock extends AsyncTask<Void, Void, Void> {

    /**Context reference used to send data to Activity/Fragment*/
    private WeakReference<Context> weakContext;

    /**ID of the block asking for from API*/
    private long block_id = -1;

    /**If you wish to obtain the sections for each block*/
    private boolean getSections = false;

    /**If you wish to obtain the elements for each section in block, should be true only if getSections is true*/
    private boolean getElements = false;

    /**If you wish to change the action that accompanies the API result*/
    private String action = NKApiManagerConfig.ACTION_GET_BLOCK;

    /**If you wish to use a listener to retrieve the data*/
    private NKApiBlockResultListener listener;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private NKBlock block;

    public NKAsyncTask_getBlock(Context context, long block_id, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.block_id = block_id;
        this.getSections = getSections;
    }

    public NKAsyncTask_getBlock(Context context, long block_id, boolean getSections, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.getSections = getSections;
        this.block_id = block_id;
        this.getElements = getElements;
    }

    public NKAsyncTask_getBlock(Context context, long block_id, String custom_action, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.block_id = block_id;
        this.getSections = getSections;
    }

    public NKAsyncTask_getBlock(Context context, long block_id, String custom_action, boolean getSections, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.block_id = block_id;
        this.getSections = getSections;
        this.getElements = getElements;
    }

    public NKAsyncTask_getBlock(Context context, long block_id, NKApiBlockResultListener listener, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.block_id = block_id;
        this.getSections = getSections;
    }

    public NKAsyncTask_getBlock(Context context, long block_id, NKApiBlockResultListener listener, boolean getSections, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.block_id = block_id;
        this.getSections = getSections;
        this.getElements = getElements;
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
        String api = NKApiManagerConfig.API_BLOCK + "/" + Long.toString(block_id);
        if(getSections){
            if(getElements){
                values.put("include", "sections.elements");
            }
            else {
                values.put("include", "sections");
            }
        }

        map = NKAPIManager3.callApi(weakContext.get(), api, values, NKApiManagerConfig.MODE_GET, true);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jBlock = jPayload.getJSONObject("body");
            block = NKParser.parseBlock(jBlock, getSections, getElements);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
