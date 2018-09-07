package mumble.mburger.sdk.MBClient.MBAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
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
import mumble.mburger.sdk.MBClient.MBApiResultsLIsteners.MBApiSectionResultListener;
import mumble.mburger.sdk.MBClient.MBData.MBSections.MBSection;

/**
 * Task which returns a single section from a particular block
 * Bundle will return object "section" which is a {@link MBSection MBSection}
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBAsyncTask_getSection extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    private WeakReference<Context> weakContext;

    /**
     * ID of the section asking for from API
     */
    private long section_id = -1;

    /**
     * If you wish to obtain the elements for each section
     */
    private boolean getElements = false;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = MBAPIConstants.ACTION_GET_SECTION;

    /**
     * If you wish to use a listener to retrieve the data
     */
    private MBApiSectionResultListener listener;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private MBSection section;

    public MBAsyncTask_getSection(Context context, long section_id, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.getElements = getElements;
        this.section_id = section_id;
    }

    public MBAsyncTask_getSection(Context context, long section_id, String custom_action, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.getElements = getElements;
        this.section_id = section_id;
    }

    public MBAsyncTask_getSection(Context context, long section_id, MBApiSectionResultListener listener, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.getElements = getElements;
        this.section_id = section_id;
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
                i.putExtra(MBApiPayloadKeys.key_section_id, section_id);
                i.putExtra(MBApiPayloadKeys.key_section, section);
                MBAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onSectionApiError(error);
                } else {
                    listener.onSectionApiResult(section, section_id);
                }
            }
        }
    }

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        String api = "/api" + MBApiManagerConfig.API_SECTION + "/" + Long.toString(section_id);
        if (getElements) {
            values.put("include", "elements");
        }

        map = MBAPIManager3.callApi(weakContext.get(), api, values, MBApiManagerConfig.MODE_GET, true, false);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jSection = jPayload.getJSONObject("body");
            section = MBParser.parseSection(jSection, getElements);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
