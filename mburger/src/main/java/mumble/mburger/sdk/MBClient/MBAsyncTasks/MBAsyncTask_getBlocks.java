package mumble.mburger.sdk.MBClient.MBAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBApiPayloadKeys;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.MBClient.MBApiResultsLIsteners.MBApiBlocksResultListener;
import mumble.mburger.sdk.Common.MBParser;
import mumble.mburger.sdk.MBClient.MBData.MBBlocks.MBBlock;
import mumble.mburger.sdk.MBClient.MBData.MBPaginationInfo;

/**
 * Task which returns all blocks from the project
 * Bundle will return object "blocks" which is an ArrayList of {@link mumble.mburger.sdk.MBClient.MBData.MBBlocks.MBBlock NBlocks}
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBAsyncTask_getBlocks extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    private WeakReference<Context> weakContext;

    /**
     * An array of filters/sorters which may result in changes to the result of the API
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
    private String action = MBAPIConstants.ACTION_GET_BLOCKS;

    /**If you wish to use a listener to retrieve the data instead of the ApiListener*/
    private MBApiBlocksResultListener listener;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private ArrayList<MBBlock> blocks;
    private MBPaginationInfo paginationInfos;

    public MBAsyncTask_getBlocks(Context context, ArrayList<Object> filters, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.getSections = getSections;
        this.filters = filters;
    }

    public MBAsyncTask_getBlocks(Context context, ArrayList<Object> filters, boolean getSections, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.getSections = getSections;
        this.getElements = getElements;
        this.filters = filters;
    }

    public MBAsyncTask_getBlocks(Context context, ArrayList<Object> filters, String custom_action, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.getSections = getSections;
        this.filters = filters;
    }

    public MBAsyncTask_getBlocks(Context context, ArrayList<Object> filters, String custom_action, boolean getSections, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.getSections = getSections;
        this.getElements = getElements;
        this.filters = filters;
    }

    public MBAsyncTask_getBlocks(Context context, ArrayList<Object> filters, MBApiBlocksResultListener listener, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.getSections = getSections;
        this.filters = filters;
    }

    public MBAsyncTask_getBlocks(Context context, ArrayList<Object> filters, MBApiBlocksResultListener listener, boolean getSections, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.getSections = getSections;
        this.getElements = getElements;
        this.filters = filters;
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
        if(weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                i.putExtra(MBApiPayloadKeys.key_blocks, blocks);
                i.putExtra(MBApiPayloadKeys.key_pagination_infos, paginationInfos);
                MBAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onBlocksApiError(error);
                } else {
                    listener.onBlocksApiResult(blocks, paginationInfos);
                }
            }
        }
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        if(getSections){
            if(getElements){
                values.put("include", "sections.elements");
            }
            else {
                values.put("include", "sections");
            }
        }

        if(getSections) {
            MBCommonMethods.addFilters(values, filters);
        }

        map = MBAPIManager3.callApi(weakContext.get(), MBApiManagerConfig.API_BLOCK, values,
                MBApiManagerConfig.MODE_GET, true, false);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jBody = jPayload.getJSONObject("body");
            JSONObject jMeta = jBody.getJSONObject("meta");
            int from = 0;
            int to = 0;
            int total = 0;

            if (MBCommonMethods.isJSONOk(jMeta, "from")) {
                from = jMeta.getInt("from");
            }

            if (MBCommonMethods.isJSONOk(jMeta, "to")) {
                to = jMeta.getInt("to");
            }

            if (MBCommonMethods.isJSONOk(jMeta, "total")) {
                total = jMeta.getInt("total");
            }

            paginationInfos = new MBPaginationInfo(from, to, total);

            JSONArray jBlocks = jBody.getJSONArray("items");
            blocks = new ArrayList<>();
            for (int i = 0; i < jBlocks.length(); i++) {
                blocks.add(MBParser.parseBlock(jBlocks.getJSONObject(i), getSections, getElements));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
