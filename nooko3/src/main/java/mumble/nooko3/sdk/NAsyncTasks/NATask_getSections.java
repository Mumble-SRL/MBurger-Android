package mumble.nooko3.sdk.NAsyncTasks;

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

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMCONF;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAPIManager3;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiSectionsResultListener;
import mumble.nooko3.sdk.NControllers.NCommonMethods;
import mumble.nooko3.sdk.NControllers.NParser;
import mumble.nooko3.sdk.NData.NPaginationInfos;
import mumble.nooko3.sdk.NData.NSections.NSection;

/**
 * Task which returns the all sections from a particular block with or without inside elements
 * Bundle will return object "sections" which is an ArrayList of {@link NSection NSection}
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class NATask_getSections extends AsyncTask<Void, Void, Void> {

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
    private String action = NAMCONF.ACTION_GET_SECTIONS;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private NApiSectionsResultListener listener;

    private int result = NAMCONF.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private ArrayList<NSection> sections;
    private NPaginationInfos paginationInfos;

    public NATask_getSections(Context context, long block_id, ArrayList<Object> filters, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.getElements = getElements;
        this.block_id = block_id;
        this.filters = filters;
    }

    public NATask_getSections(Context context, long block_id, ArrayList<Object> filters, String custom_action, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.block_id = block_id;
        this.getElements = getElements;
        this.filters = filters;
    }

    public NATask_getSections(Context context, long block_id, ArrayList<Object> filters, NApiSectionsResultListener listener, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.block_id = block_id;
        this.listener = listener;
        this.getElements = getElements;
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
                error = NCommonMethods.getErrorMessageFromResult(weakContext.get(), result);
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
                i.putExtra("sections", sections);
                i.putExtra("paginationInfos", paginationInfos);
                NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onSectionsApiError(error);
                } else {
                    listener.onSectionsApiResult(sections, paginationInfos);
                }
            }
        }
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        if (getElements) {
            values.put("include", "elements");
        }

        String api = "/api" + NAMCONF.API_SECTION;
        if (block_id != -1) {
            api = NAMCONF.API_BLOCK + "/" + Long.toString(block_id) + NAMCONF.API_SECTION;
        }

        map = NAPIManager3.callApi(weakContext.get(), api, values, NAMCONF.MODE_GET, true);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jBody = jPayload.getJSONObject("body");
            JSONObject jMeta = jBody.getJSONObject("meta");
            int from = 0;
            int to = 0;
            int total = 0;

            if (NCommonMethods.isJSONOk(jMeta, "from")) {
                from = jMeta.getInt("from");
            }

            if (NCommonMethods.isJSONOk(jMeta, "to")) {
                to = jMeta.getInt("to");
            }

            if (NCommonMethods.isJSONOk(jMeta, "total")) {
                total = jMeta.getInt("total");
            }

            paginationInfos = new NPaginationInfos(from, to, total);

            JSONArray jSections = jBody.getJSONArray("items");
            sections = new ArrayList<>();
            for (int i = 0; i < jSections.length(); i++) {
                sections.add(NParser.parseSection(jSections.getJSONObject(i), getElements));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
