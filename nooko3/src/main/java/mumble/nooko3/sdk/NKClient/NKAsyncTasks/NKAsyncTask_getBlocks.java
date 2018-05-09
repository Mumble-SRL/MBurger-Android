package mumble.nooko3.sdk.NKClient.NKAsyncTasks;

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

import mumble.nooko3.sdk.Common.NKConstants.NKAPIConstants;
import mumble.nooko3.sdk.Common.NKConstants.NKConstants;
import mumble.nooko3.sdk.Common.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.Common.NKApiManager.NKAPIManager3;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiPayloadKeys;
import mumble.nooko3.sdk.NKClient.NKApiResultsLIsteners.NKApiBlocksResultListener;
import mumble.nooko3.sdk.Common.NKCommonMethods;
import mumble.nooko3.sdk.Common.NKParser;
import mumble.nooko3.sdk.NKData.NKBlocks.NKBlock;
import mumble.nooko3.sdk.NKData.NKPaginationInfo;

/**
 * Task which returns all blocks from the project
 * Bundle will return object "blocks" which is an ArrayList of {@link NKBlock NBlocks}
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKAsyncTask_getBlocks extends AsyncTask<Void, Void, Void> {

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
    private String action = NKAPIConstants.ACTION_GET_BLOCKS;

    /**If you wish to use a listener to retrieve the data instead of the ApiListener*/
    private NKApiBlocksResultListener listener;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private ArrayList<NKBlock> blocks;
    private NKPaginationInfo paginationInfos;

    public NKAsyncTask_getBlocks(Context context, ArrayList<Object> filters, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.getSections = getSections;
        this.filters = filters;
    }

    public NKAsyncTask_getBlocks(Context context, ArrayList<Object> filters, boolean getSections, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.getSections = getSections;
        this.getElements = getElements;
        this.filters = filters;
    }

    public NKAsyncTask_getBlocks(Context context, ArrayList<Object> filters, String custom_action, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.getSections = getSections;
        this.filters = filters;
    }

    public NKAsyncTask_getBlocks(Context context, ArrayList<Object> filters, String custom_action, boolean getSections, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.getSections = getSections;
        this.getElements = getElements;
        this.filters = filters;
    }

    public NKAsyncTask_getBlocks(Context context, ArrayList<Object> filters, NKApiBlocksResultListener listener, boolean getSections) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.getSections = getSections;
        this.filters = filters;
    }

    public NKAsyncTask_getBlocks(Context context, ArrayList<Object> filters, NKApiBlocksResultListener listener, boolean getSections, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.getSections = getSections;
        this.getElements = getElements;
        this.filters = filters;
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
        if(weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                i.putExtra(NKApiPayloadKeys.key_blocks, blocks);
                i.putExtra(NKApiPayloadKeys.key_pagination_infos, paginationInfos);
                NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
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
            NKCommonMethods.addFilters(values, filters);
        }

        map = NKAPIManager3.callApi(weakContext.get(), NKApiManagerConfig.API_BLOCK, values, NKApiManagerConfig.MODE_GET, true);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jBody = jPayload.getJSONObject("body");
            JSONObject jMeta = jBody.getJSONObject("meta");
            int from = 0;
            int to = 0;
            int total = 0;

            if (NKCommonMethods.isJSONOk(jMeta, "from")) {
                from = jMeta.getInt("from");
            }

            if (NKCommonMethods.isJSONOk(jMeta, "to")) {
                to = jMeta.getInt("to");
            }

            if (NKCommonMethods.isJSONOk(jMeta, "total")) {
                total = jMeta.getInt("total");
            }

            paginationInfos = new NKPaginationInfo(from, to, total);

            JSONArray jBlocks = jBody.getJSONArray("items");
            blocks = new ArrayList<>();
            for (int i = 0; i < jBlocks.length(); i++) {
                blocks.add(NKParser.parseBlock(jBlocks.getJSONObject(i), getSections, getElements));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
