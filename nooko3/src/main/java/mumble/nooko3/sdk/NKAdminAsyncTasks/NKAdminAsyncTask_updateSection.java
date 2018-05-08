package mumble.nooko3.sdk.NKAdminAsyncTasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

import mumble.nooko3.sdk.NKAdminData.NKAdminParameter;
import mumble.nooko3.sdk.NKAdminData.NKAdminParameterFile;
import mumble.nooko3.sdk.NKAdminData.NKAdminSingleFile;
import mumble.nooko3.sdk.NKConstants.NKAPIConstants;
import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKControllers.NKAdminResultsListeners.NKAdminApiAddSectionListener;
import mumble.nooko3.sdk.NKControllers.NKAdminResultsListeners.NKAdminApiUpdateSectionListener;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerOKHTTPPost;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiPayloadKeys;
import mumble.nooko3.sdk.NKControllers.NKCommonMethods;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Task which will update a section, usable only if your token has "write" permission
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKAdminAsyncTask_updateSection extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    private WeakReference<Context> weakContext;

    /**
     * ID of the section to update
     */
    private long section_id;

    /**
     * Parameters to add
     */
    private ArrayList<NKAdminParameter> parameters;

    /**
     * Files to add
     */
    private ArrayList<NKAdminParameterFile> parameters_files;

    /**
     * Locale in which update a the existing section
     */
    private String locale;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = NKAPIConstants.ACTION_UPDATE_SECTION;

    /**
     * If you wish to use a listener to retrieve the data
     */
    private NKAdminApiUpdateSectionListener listener;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public NKAdminAsyncTask_updateSection(Context context, long section_id, ArrayList<NKAdminParameter> parameters,
                                          ArrayList<NKAdminParameterFile> parameters_files, String locale) {
        this.weakContext = new WeakReference<>(context);
        this.section_id = section_id;
        this.parameters = parameters;
        this.parameters_files = parameters_files;
        this.locale = locale;
    }

    public NKAdminAsyncTask_updateSection(Context context, String custom_action, long section_id, ArrayList<NKAdminParameter> parameters,
                                          ArrayList<NKAdminParameterFile> parameters_files, String locale) {
        this.weakContext = new WeakReference<>(context);
        this.section_id = section_id;
        this.parameters = parameters;
        this.parameters_files = parameters_files;
        this.action = custom_action;
        this.locale = locale;
    }

    public NKAdminAsyncTask_updateSection(Context context, NKAdminApiUpdateSectionListener listener, long section_id, ArrayList<NKAdminParameter> parameters,
                                          ArrayList<NKAdminParameterFile> parameters_files, String locale) {
        this.weakContext = new WeakReference<>(context);
        this.section_id = section_id;
        this.parameters = parameters;
        this.parameters_files = parameters_files;
        this.listener = listener;
        this.locale = locale;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        putValuesAndCall();
        if (NKApiManagerUtils.hasMapOkResults(map, false)) {
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
                    listener.onSectionUpdatedError(error);
                } else {
                    listener.onSectionUpdated(section_id);
                }
            }
        }
    }

    public void putValuesAndCall() {
        String api = NKApiManagerConfig.API
                + NKApiManagerConfig.API_SECTION
                + "/"
                + Long.toString(section_id)
                + NKApiManagerConfig.API_UPDATE;

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

}
