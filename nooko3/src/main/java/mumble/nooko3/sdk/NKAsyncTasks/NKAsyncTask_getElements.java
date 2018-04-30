package mumble.nooko3.sdk.NKAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import mumble.nooko3.sdk.NKConstants.NKAPIConstants;
import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKAPIManager3;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiPayloadKeys;
import mumble.nooko3.sdk.NKControllers.NKApiResultsLIsteners.NKApiElementsResultListener;
import mumble.nooko3.sdk.NKControllers.NKCommonMethods;
import mumble.nooko3.sdk.NKControllers.NKParser;
import mumble.nooko3.sdk.NKData.NKAtomic.NKClass;

/**
 * Task which returns the all elements from a particular section
 * Bundle will return object "elements" which is an HashMap of {@link NKClass NClasses}
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKAsyncTask_getElements extends AsyncTask<Void, Void, Void> {

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
    private String action = NKAPIConstants.ACTION_GET_ELEMENTS;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private NKApiElementsResultListener listener;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private Map<String, NKClass> elements;

    public NKAsyncTask_getElements(Context context, long section_id) {
        this.weakContext = new WeakReference<>(context);
        this.section_id = section_id;
    }

    public NKAsyncTask_getElements(Context context, long section_id, String custom_action) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.section_id = section_id;
    }

    public NKAsyncTask_getElements(Context context, long section_id, NKApiElementsResultListener listener) {
        this.weakContext = new WeakReference<>(context);
        this.section_id = section_id;
        this.listener = listener;
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
                HashMap<String, String> hashMap =
                        (map instanceof HashMap)
                                ? (HashMap) map
                                : new HashMap<>(elements);

                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                i.putExtra(NKApiPayloadKeys.key_elements, hashMap);
                i.putExtra(NKApiPayloadKeys.key_section_id, section_id);
                NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onElementsApiError(error);
                } else {
                    listener.onElementsApiResult(elements, section_id);
                }
            }
        }
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        String api = "/api" + NKApiManagerConfig.API_SECTION + "/" + Long.toString(section_id) + NKApiManagerConfig.API_ELEMENTS;
        map = NKAPIManager3.callApi(weakContext.get(), api, values, NKApiManagerConfig.MODE_GET, true);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jBody = jPayload.getJSONObject("body");
            JSONObject jItems = jBody.getJSONObject("items");
            elements = NKParser.parseElements(jItems);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
