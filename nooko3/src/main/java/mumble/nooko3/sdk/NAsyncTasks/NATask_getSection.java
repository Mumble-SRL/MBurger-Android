package mumble.nooko3.sdk.NAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMCONF;
import mumble.nooko3.sdk.NControllers.NApiManager.NAMUtils;
import mumble.nooko3.sdk.NControllers.NApiManager.NAPIManager3;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiProjectResultListener;
import mumble.nooko3.sdk.NControllers.NApiResultsLIsteners.NApiSectionResultListener;
import mumble.nooko3.sdk.NControllers.NCommonMethods;
import mumble.nooko3.sdk.NControllers.NParser;
import mumble.nooko3.sdk.NData.NSections.NSection;

/**
 * Task which returns a single section from a particular block
 * Bundle will return object "section" which is a {@link NSection NSection}
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class NATask_getSection extends AsyncTask<Void, Void, Void> {

    /**Context reference used to send data to Activity/Fragment*/
    private WeakReference<Context> weakContext;

    /**ID of the section asking for from API*/
    private long section_id = -1;

    /**If you wish to obtain the elements for each section*/
    private boolean getElements = false;

    /**If you wish to change the action that accompanies the API result*/
    private String action = NAMCONF.ACTION_GET_SECTION;

    /**If you wish to use a listener to retrieve the data*/
    private NApiSectionResultListener listener;

    private int result = NAMCONF.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    private NSection section;

    public NATask_getSection(Context context, long section_id, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.getElements = getElements;
        this.section_id = section_id;
    }

    public NATask_getSection(Context context, long section_id, String custom_action, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.getElements = getElements;
        this.section_id = section_id;
    }

    public NATask_getSection(Context context, long section_id, NApiSectionResultListener listener, boolean getElements) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.getElements = getElements;
        this.section_id = section_id;
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
            if(listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                i.putExtra("section", section);
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
        String api = "/api" + NAMCONF.API_SECTION + "/" + Long.toString(section_id);
        if(getElements){
            values.put("include", "elements");
        }

        map = NAPIManager3.callApi(weakContext.get(), api, values, NAMCONF.MODE_GET, true);
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jSection = jPayload.getJSONObject("body");
            section = NParser.parseSection(jSection, getElements);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
