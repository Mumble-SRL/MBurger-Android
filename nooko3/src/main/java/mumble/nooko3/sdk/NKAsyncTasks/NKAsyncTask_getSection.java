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
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiPayloadKeys;
import mumble.nooko3.sdk.NKControllers.NKApiResultsLIsteners.NKApiSectionResultListener;
import mumble.nooko3.sdk.NKControllers.NKCommonMethods;
import mumble.nooko3.sdk.NKControllers.NKParser;
import mumble.nooko3.sdk.NKData.NKSections.NKSection;

/**
 * Task which returns a single section from a particular block
 * Bundle will return object "section" which is a {@link NKSection NKSection}
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKAsyncTask_getSection extends AsyncTask<Void, Void, Void> {

    /**Context reference used to send data to Activity/Fragment*/
    private WeakReference<Context> weakContext;

    /**ID of the section asking for from API*/
    private long section_id = -1;

    /**If you wish to obtain the elements for each section*/
    private boolean getElements = false;

    /**If you wish to change the action that accompanies the API result*/
    private String action = NKApiManagerConfig.ACTION_GET_SECTION;

    /**If you wish to use a listener to retrieve the data*/
    private NKApiSectionResultListener listener;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private NKSection section;

    public NKAsyncTask_getSection(Context context, long section_id, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.getElements = getElements;
        this.section_id = section_id;
    }

    public NKAsyncTask_getSection(Context context, long section_id, String custom_action, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.getElements = getElements;
        this.section_id = section_id;
    }

    public NKAsyncTask_getSection(Context context, long section_id, NKApiSectionResultListener listener, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.getElements = getElements;
        this.section_id = section_id;
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
                i.putExtra(NKApiPayloadKeys.key_section, section);
                NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            }
            else{
                if(error != null){
                    listener.onSectionApiError(error);
                }
                else{
                    listener.onSectionApiResult(section);
                }
            }
        }
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        String api = "/api" + NKApiManagerConfig.API_SECTION + "/" + Long.toString(section_id);
        if(getElements){
            values.put("include", "elements");
        }

        map = NKAPIManager3.callApi(weakContext.get(), api, values, NKApiManagerConfig.MODE_GET, true);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jSection = jPayload.getJSONObject("body");
            section = NKParser.parseSection(jSection, getElements);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
