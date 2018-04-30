package mumble.nooko3.sdk.NKAsyncTasks;

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

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKAPIManager3;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiPayloadKeys;
import mumble.nooko3.sdk.NKControllers.NKApiResultsLIsteners.NKApiSectionsResultListener;
import mumble.nooko3.sdk.NKControllers.NKCommonMethods;
import mumble.nooko3.sdk.NKControllers.NKParser;
import mumble.nooko3.sdk.NKData.NKPaginationInfo;
import mumble.nooko3.sdk.NKData.NKSections.NKSection;

/**
 * Task which returns the all sections from a particular block with or without inside elements
 * Bundle will return object "sections" which is an ArrayList of {@link NKSection NKSection}
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKAsyncTask_getSections extends AsyncTask<Void, Void, Void> {

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
    private String action = NKApiManagerConfig.ACTION_GET_SECTIONS;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private NKApiSectionsResultListener listener;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private ArrayList<NKSection> sections;
    private NKPaginationInfo paginationInfos;

    public NKAsyncTask_getSections(Context context, long block_id, ArrayList<Object> filters, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.getElements = getElements;
        this.block_id = block_id;
        this.filters = filters;
    }

    public NKAsyncTask_getSections(Context context, long block_id, ArrayList<Object> filters, String custom_action, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.block_id = block_id;
        this.getElements = getElements;
        this.filters = filters;
    }

    public NKAsyncTask_getSections(Context context, long block_id, ArrayList<Object> filters, NKApiSectionsResultListener listener, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.block_id = block_id;
        this.listener = listener;
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
        if (weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                i.putExtra(NKApiPayloadKeys.key_block_id, block_id);
                i.putExtra(NKApiPayloadKeys.key_sections, sections);
                i.putExtra(NKApiPayloadKeys.key_pagination_infos, paginationInfos);
                NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
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

        String api = "/api" + NKApiManagerConfig.API_SECTION;
        if (block_id != -1) {
            api = NKApiManagerConfig.API_BLOCK + "/" + Long.toString(block_id) + NKApiManagerConfig.API_SECTION;
        }

        NKCommonMethods.addFilters(values, filters);
        map = NKAPIManager3.callApi(weakContext.get(), api, values, NKApiManagerConfig.MODE_GET, true);
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

            JSONArray jSections = jBody.getJSONArray("items");
            sections = new ArrayList<>();
            for (int i = 0; i < jSections.length(); i++) {
                sections.add(NKParser.parseSection(jSections.getJSONObject(i), getElements));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
