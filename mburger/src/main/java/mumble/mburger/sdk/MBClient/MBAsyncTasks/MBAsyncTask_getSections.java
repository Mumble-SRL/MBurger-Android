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
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.Common.MBApiManager.MBApiPayloadKeys;
import mumble.mburger.sdk.MBClient.MBApiResultsLIsteners.MBApiSectionsResultListener;
import mumble.mburger.sdk.Common.MBParser;
import mumble.mburger.sdk.MBClient.MBData.MBPaginationInfo;
import mumble.mburger.sdk.MBClient.MBData.MBSections.MBSection;

/**
 * Task which returns the all sections from a particular block with or without inside elements
 * Bundle will return object "sections" which is an ArrayList of {@link mumble.mburger.sdk.MBClient.MBData.MBSections.MBSection MBSection}
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBAsyncTask_getSections extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    private WeakReference<Context> weakContext;

    /**
     * An array of filters/sorters which may result in changes to the result of the API
     */
    private ArrayList<Object> filters;

    /**
     * If you wish to obtain the elements for each section
     */
    private boolean getElements = false;

    /**
     * ID of the block which you need sections
     */
    private long block_id = -1;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = MBAPIConstants.ACTION_GET_SECTIONS;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private MBApiSectionsResultListener listener;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private ArrayList<MBSection> sections;
    private MBPaginationInfo paginationInfos;

    public MBAsyncTask_getSections(Context context, long block_id, ArrayList<Object> filters, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.getElements = getElements;
        this.block_id = block_id;
        this.filters = filters;
    }

    public MBAsyncTask_getSections(Context context, long block_id, ArrayList<Object> filters, String custom_action, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.block_id = block_id;
        this.getElements = getElements;
        this.filters = filters;
    }

    public MBAsyncTask_getSections(Context context, long block_id, ArrayList<Object> filters, MBApiSectionsResultListener listener, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.block_id = block_id;
        this.listener = listener;
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
        if (weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                i.putExtra(MBApiPayloadKeys.key_block_id, block_id);
                i.putExtra(MBApiPayloadKeys.key_sections, sections);
                i.putExtra(MBApiPayloadKeys.key_pagination_infos, paginationInfos);
                MBAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onSectionsApiError(error);
                } else {
                    listener.onSectionsApiResult(sections, block_id, paginationInfos);
                }
            }
        }
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        if (getElements) {
            values.put("include", "elements");
        }

        String api = "/api" + MBApiManagerConfig.API_SECTION;
        if (block_id != -1) {
            api = MBApiManagerConfig.API_BLOCK + "/" + Long.toString(block_id) + MBApiManagerConfig.API_SECTION;
        }

        MBCommonMethods.addFilters(values, filters);
        map = MBAPIManager3.callApi(weakContext.get(), api, values, MBApiManagerConfig.MODE_GET, true, false);
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

            JSONArray jSections = jBody.getJSONArray("items");
            sections = new ArrayList<>();
            for (int i = 0; i < jSections.length(); i++) {
                sections.add(MBParser.parseSection(jSections.getJSONObject(i), getElements));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
