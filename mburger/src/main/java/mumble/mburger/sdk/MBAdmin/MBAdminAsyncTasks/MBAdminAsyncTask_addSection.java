package mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerOKHTTPPost;
import mumble.mburger.sdk.MBAdmin.MBAdminData.MBAdminParameter;
import mumble.mburger.sdk.MBAdmin.MBAdminData.MBAdminParameterFile;
import mumble.mburger.sdk.MBAdmin.MBAdminData.MBAdminSingleFile;
import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBAdmin.MBAdminResultsListeners.MBAdminApiAddSectionListener;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBApiPayloadKeys;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Task which will add a section, usable only if your token has "write" permission
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBAdminAsyncTask_addSection extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    private WeakReference<Context> weakContext;

    /**
     * Parameters to add
     */
    private ArrayList<MBAdminParameter> parameters;

    /**
     * Files to add
     */
    private ArrayList<MBAdminParameterFile> parameters_files;

    /**
     * Id of the block in which add a new section
     */
    private long block_id;

    /**
     * Locale in which add a new section
     */
    private String locale;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = MBAPIConstants.ACTION_ADD_SECTION;

    /**
     * ID of the section returned
     */
    private long section_id = -1;

    /**
     * If you wish to use a listener to retrieve the data
     */
    private MBAdminApiAddSectionListener listener;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public MBAdminAsyncTask_addSection(Context context, long block_id, ArrayList<MBAdminParameter> parameters,
                                       ArrayList<MBAdminParameterFile> parameters_files, String locale) {
        this.weakContext = new WeakReference<>(context);
        this.block_id = block_id;
        this.parameters = parameters;
        this.parameters_files = parameters_files;
        this.locale = locale;
    }

    public MBAdminAsyncTask_addSection(Context context, String custom_action, long block_id, ArrayList<MBAdminParameter> parameters,
                                       ArrayList<MBAdminParameterFile> parameters_files, String locale) {
        this.weakContext = new WeakReference<>(context);
        this.block_id = block_id;
        this.parameters = parameters;
        this.parameters_files = parameters_files;
        this.action = custom_action;
        this.locale = locale;
    }

    public MBAdminAsyncTask_addSection(Context context, MBAdminApiAddSectionListener listener, long block_id, ArrayList<MBAdminParameter> parameters,
                                       ArrayList<MBAdminParameterFile> parameters_files, String locale) {
        this.weakContext = new WeakReference<>(context);
        this.block_id = block_id;
        this.parameters = parameters;
        this.parameters_files = parameters_files;
        this.listener = listener;
        this.locale = locale;
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
                MBAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
            } else {
                if (error != null) {
                    listener.onSectionAddedError(error);
                } else {
                    listener.onSectionAdded(section_id);
                }
            }
        }
    }

    public void putValuesAndCall() {
        String api = MBApiManagerConfig.API_BLOCK
                + "/"
                + Long.toString(block_id)
                + MBApiManagerConfig.API_SECTION;

        MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                MBAdminParameter param = parameters.get(i);
                requestBodyBuilder.addFormDataPart(createKey(param), param.getValue());
            }
        }

        if (parameters_files != null) {
            for (int i = 0; i < parameters_files.size(); i++) {
                MBAdminParameterFile param = parameters_files.get(i);
                for (int j = 0; j < param.getFiles().size(); j++) {
                    MBAdminSingleFile f = param.getFiles().get(j);
                    requestBodyBuilder.addFormDataPart(createKey(param, j), f.getName(),
                            RequestBody.create(MediaType.parse(f.getMime_type()), new File(f.getFile_path())));
                }
            }
        }

        map = MBApiManagerOKHTTPPost.callApi(weakContext.get(), api, requestBodyBuilder.build(), true);
    }

    public String createKey(MBAdminParameter parameter) {
        StringBuilder builder = new StringBuilder("elements");
        builder.append("[").append(locale).append("]");
        builder.append("[").append(parameter.getKey()).append("]");
        return builder.toString();
    }

    public String createKey(MBAdminParameterFile parameter, int index) {
        StringBuilder builder = new StringBuilder("elements");
        builder.append("[").append(locale).append("]");
        builder.append("[").append(parameter.getKey()).append("]");
        builder.append("[").append(Integer.toString(index)).append("]");
        return builder.toString();
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jBody = jPayload.getJSONObject("body");
            section_id = jBody.getLong("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
