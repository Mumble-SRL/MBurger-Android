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
import java.util.HashMap;
import java.util.Map;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMCONF;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAPIManager3;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiElementsResultListener;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiSectionsResultListener;
import mumble.nooko3.sdk.NControllers.NCommonMethods;
import mumble.nooko3.sdk.NControllers.NParser;
import mumble.nooko3.sdk.NData.NAtomic.NClass;
import mumble.nooko3.sdk.NData.NPaginationInfos;
import mumble.nooko3.sdk.NData.NSections.NSection;

/**
 * Task which returns the all elements from a particular section
 * Bundle will return object "elements" which is an HashMap of {@link mumble.nooko3.sdk.NData.NAtomic.NClass NClasses}
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class ATask_getElements extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    private WeakReference<Context> weakContext;

    /**
     * If you wish to obtain the elements for a section
     */
    private long section_id = -1;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = NAMCONF.ACTION_GET_ELEMENTS;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private NApiElementsResultListener listener;

    private int result = NAMCONF.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private Map<String, NClass> elements;

    public ATask_getElements(Context context, long section_id) {
        this.weakContext = new WeakReference<>(context);
        this.section_id = section_id;
    }

    public ATask_getElements(Context context, long section_id, String custom_action) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.section_id = section_id;
    }

    public ATask_getElements(Context context, long section_id, NApiElementsResultListener listener) {
        this.weakContext = new WeakReference<>(context);
        this.section_id = section_id;
        this.listener = listener;
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
                HashMap<String, String> hashMap =
                        (map instanceof HashMap)
                                ? (HashMap) map
                                : new HashMap<>(elements);

                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                i.putExtra("sections", hashMap);
                NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onElementsApiError(error);
                } else {
                    listener.onElementsApiResult(elements);
                }
            }
        }
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        String api = "/api" + NAMCONF.API_SECTION + "/" + Long.toString(section_id) + NAMCONF.API_ELEMENTS;
        map = NAPIManager3.callApi(weakContext.get(), api, values, NAMCONF.MODE_GET, true);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jBody = jPayload.getJSONObject("body");
            JSONObject jItems = jBody.getJSONObject("items");
            elements = NParser.parseElements(jItems);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
