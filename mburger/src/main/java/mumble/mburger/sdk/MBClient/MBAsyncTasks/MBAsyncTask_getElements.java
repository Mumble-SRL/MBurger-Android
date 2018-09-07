package mumble.mburger.sdk.MBClient.MBAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBApiPayloadKeys;
import mumble.mburger.sdk.MBClient.MBApiResultsLIsteners.MBApiElementsResultListener;
import mumble.mburger.sdk.Common.MBParser;
import mumble.mburger.sdk.MBClient.MBData.MBAtomic.MBClass;

/**
 * Task which returns the all elements from a particular section
 * Bundle will return object "elements" which is an HashMap of {@link mumble.mburger.sdk.MBClient.MBData.MBAtomic.MBClass NClasses}
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBAsyncTask_getElements extends AsyncTask<Void, Void, Void> {

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
    private String action = MBAPIConstants.ACTION_GET_ELEMENTS;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private MBApiElementsResultListener listener;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private Map<String, MBClass> elements;

    public MBAsyncTask_getElements(Context context, long section_id) {
        this.weakContext = new WeakReference<>(context);
        this.section_id = section_id;
    }

    public MBAsyncTask_getElements(Context context, long section_id, String custom_action) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.section_id = section_id;
    }

    public MBAsyncTask_getElements(Context context, long section_id, MBApiElementsResultListener listener) {
        this.weakContext = new WeakReference<>(context);
        this.section_id = section_id;
        this.listener = listener;
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
                HashMap<String, String> hashMap =
                        (map instanceof HashMap)
                                ? (HashMap) map
                                : new HashMap<>(elements);

                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                i.putExtra(MBApiPayloadKeys.key_elements, hashMap);
                i.putExtra(MBApiPayloadKeys.key_section_id, section_id);
                MBAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
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
        String api = "/api" + MBApiManagerConfig.API_SECTION + "/" + Long.toString(section_id) + MBApiManagerConfig.API_ELEMENTS;
        map = MBAPIManager3.callApi(weakContext.get(), api, values, MBApiManagerConfig.MODE_GET, true, false);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jBody = jPayload.getJSONObject("body");
            JSONObject jItems = jBody.getJSONObject("items");
            elements = MBParser.parseElements(jItems);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
