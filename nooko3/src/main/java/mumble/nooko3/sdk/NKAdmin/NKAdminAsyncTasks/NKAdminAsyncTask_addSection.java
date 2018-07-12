package mumble.nooko3.sdk.NKAdmin.NKAdminAsyncTasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

import mumble.nooko3.sdk.Common.NKParser;
import mumble.nooko3.sdk.NKAdmin.NKAdminData.NKAdminParameter;
import mumble.nooko3.sdk.NKAdmin.NKAdminData.NKAdminParameterFile;
import mumble.nooko3.sdk.NKAdmin.NKAdminData.NKAdminSingleFile;
import mumble.nooko3.sdk.Common.NKConstants.NKAPIConstants;
import mumble.nooko3.sdk.Common.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKAdmin.NKAdminResultsListeners.NKAdminApiAddSectionListener;
import mumble.nooko3.sdk.Common.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerOKHTTPPost;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiPayloadKeys;
import mumble.nooko3.sdk.Common.NKCommonMethods;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Task which will add a section, usable only if your token has "write" permission
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKAdminAsyncTask_addSection extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    private WeakReference<Context> weakContext;

    /**
     * Parameters to add
     */
    private ArrayList<NKAdminParameter> parameters;

    /**
     * Files to add
     */
    private ArrayList<NKAdminParameterFile> parameters_files;

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
    private String action = NKAPIConstants.ACTION_ADD_SECTION;

    /**
     * ID of the section returned
     */
    private long section_id = -1;

    /**
     * If you wish to use a listener to retrieve the data
     */
    private NKAdminApiAddSectionListener listener;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public NKAdminAsyncTask_addSection(Context context, long block_id, ArrayList<NKAdminParameter> parameters,
                                       ArrayList<NKAdminParameterFile> parameters_files, String locale) {
        this.weakContext = new WeakReference<>(context);
        this.block_id = block_id;
        this.parameters = parameters;
        this.parameters_files = parameters_files;
        this.locale = locale;
    }

    public NKAdminAsyncTask_addSection(Context context, String custom_action, long block_id, ArrayList<NKAdminParameter> parameters,
                                       ArrayList<NKAdminParameterFile> parameters_files, String locale) {
        this.weakContext = new WeakReference<>(context);
        this.block_id = block_id;
        this.parameters = parameters;
        this.parameters_files = parameters_files;
        this.action = custom_action;
        this.locale = locale;
    }

    public NKAdminAsyncTask_addSection(Context context, NKAdminApiAddSectionListener listener, long block_id, ArrayList<NKAdminParameter> parameters,
                                       ArrayList<NKAdminParameterFile> parameters_files, String locale) {
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
                i.putExtra(NKApiPayloadKeys.key_section_id, section_id);
                NAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
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
        String api = NKApiManagerConfig.API_BLOCK
                + "/"
                + Long.toString(block_id)
                + NKApiManagerConfig.API_SECTION;

        MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                NKAdminParameter param = parameters.get(i);
                requestBodyBuilder.addFormDataPart(createKey(param), param.getValue());
            }
        }

        if (parameters_files != null) {
            for (int i = 0; i < parameters_files.size(); i++) {
                NKAdminParameterFile param = parameters_files.get(i);
                for (int j = 0; j < param.getFiles().size(); j++) {
                    NKAdminSingleFile f = param.getFiles().get(j);
                    requestBodyBuilder.addFormDataPart(createKey(param, j), f.getName(),
                            RequestBody.create(MediaType.parse(f.getMime_type()), new File(f.getFile_path())));
                }
            }
        }

        map = NKApiManagerOKHTTPPost.callApi(weakContext.get(), api, requestBodyBuilder.build(), true);
    }

    public String createKey(NKAdminParameter parameter) {
        StringBuilder builder = new StringBuilder("elements");
        builder.append("[").append(locale).append("]");
        builder.append("[").append(parameter.getKey()).append("]");
        return builder.toString();
    }

    public String createKey(NKAdminParameterFile parameter, int index) {
        StringBuilder builder = new StringBuilder("elements");
        builder.append("[").append(locale).append("]");
        builder.append("[").append(parameter.getKey()).append("]");
        builder.append("[").append(Integer.toString(index)).append("]");
        return builder.toString();
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
