package mumble.mburger.sdk.MBClient.MBAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBApiPayloadKeys;
import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.Common.MBParser;
import mumble.mburger.sdk.MBClient.MBApiResultsLIsteners.MBApiBlockResultListener;
import mumble.mburger.sdk.MBClient.MBData.MBBlocks.MBBlock;

/**
 * Task which returns a single block from the project
 * Bundle will return object "block" which is a {@link mumble.mburger.sdk.MBClient.MBData.MBBlocks.MBBlock MBBlock}
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBAsyncTask_getBlock extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    private WeakReference<Context> weakContext;

    /**
     * ID of the block asking for from API
     */
    private long block_id = -1;

    /**
     * An array of filters/sorters which may result in changes to the result of the API, used when getSections = true
     */
    private ArrayList<Object> filters;

    /**
     * If you wish to obtain the sections for each block
     */
    private boolean getSections = false;

    /**
     * If you wish to obtain the elements for each section in block, should be true only if getSections is true
     */
    private boolean getElements = false;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = MBAPIConstants.ACTION_GET_BLOCK;

    /**
     * If you wish to use a listener to retrieve the data
     */
    private MBApiBlockResultListener listener;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private MBBlock block;

    public MBAsyncTask_getBlock(Context context, long block_id, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.block_id = block_id;
        this.getSections = getSections;
    }

    public MBAsyncTask_getBlock(Context context, long block_id, boolean getSections, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.getSections = getSections;
        this.block_id = block_id;
        this.getElements = getElements;
    }

    public MBAsyncTask_getBlock(Context context, long block_id, String custom_action, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.block_id = block_id;
        this.getSections = getSections;
    }

    public MBAsyncTask_getBlock(Context context, long block_id, String custom_action, boolean getSections, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.block_id = block_id;
        this.getSections = getSections;
        this.getElements = getElements;
    }

    public MBAsyncTask_getBlock(Context context, long block_id, MBApiBlockResultListener listener, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.block_id = block_id;
        this.getSections = getSections;
    }

    public MBAsyncTask_getBlock(Context context, long block_id, MBApiBlockResultListener listener, boolean getSections, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.block_id = block_id;
        this.getSections = getSections;
        this.getElements = getElements;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        putValuesAndCall();
        if (MBApiManagerUtils.hasMapOkResults(map, true)) {
            getPayload((String) map.get(MBApiManagerConfig.AM_PAYLOAD));
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
                i.putExtra(MBApiPayloadKeys.key_block, block);
                MBAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onBlockApiError(error);
                } else {
                    listener.onBlockApiResult(block);
                }
            }
        }
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        String api = MBApiManagerConfig.API_BLOCK + "/" + Long.toString(block_id);
        if (getSections) {
            if (getElements) {
                values.put("include", "sections.elements");
            } else {
                values.put("include", "sections");
            }
        }

        if (getSections) {
            MBCommonMethods.addFilters(values, filters);
        }

        map = MBAPIManager3.callApi(weakContext.get(), api, values, MBApiManagerConfig.MODE_GET, true, false);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jBlock = jPayload.getJSONObject("body");
            block = MBParser.parseBlock(jBlock, getSections, getElements);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
