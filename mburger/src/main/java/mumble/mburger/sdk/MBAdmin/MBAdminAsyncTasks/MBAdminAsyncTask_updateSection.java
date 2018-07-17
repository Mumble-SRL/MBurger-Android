package mumble.mburger.sdk.MBAdmin.MBAdminAsyncTasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerOKHTTPPost;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.MBAdmin.MBAdminData.MBAdminParameter;
import mumble.mburger.sdk.MBAdmin.MBAdminData.MBAdminParameterFile;
import mumble.mburger.sdk.MBAdmin.MBAdminData.MBAdminSingleFile;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.MBAdmin.MBAdminResultsListeners.MBAdminApiUpdateSectionListener;
import mumble.mburger.sdk.Common.MBApiManager.MBApiPayloadKeys;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Task which will update a section, usable only if your token has "write" permission
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public class MBAdminAsyncTask_updateSection extends AsyncTask<Void, Void, Void> {

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
    private ArrayList<MBAdminParameter> parameters;

    /**
     * Files to add
     */
    private ArrayList<MBAdminParameterFile> parameters_files;

    /**
     * Locale in which update a the existing section
     */
    private String locale;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = MBAPIConstants.ACTION_UPDATE_SECTION;

    /**
     * If you wish to use a listener to retrieve the data
     */
    private MBAdminApiUpdateSectionListener listener;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public MBAdminAsyncTask_updateSection(Context context, long section_id, ArrayList<MBAdminParameter> parameters,
                                          ArrayList<MBAdminParameterFile> parameters_files, String locale) {
        this.weakContext = new WeakReference<>(context);
        this.section_id = section_id;
        this.parameters = parameters;
        this.parameters_files = parameters_files;
        this.locale = locale;
    }

    public MBAdminAsyncTask_updateSection(Context context, String custom_action, long section_id, ArrayList<MBAdminParameter> parameters,
                                          ArrayList<MBAdminParameterFile> parameters_files, String locale) {
        this.weakContext = new WeakReference<>(context);
        this.section_id = section_id;
        this.parameters = parameters;
        this.parameters_files = parameters_files;
        this.action = custom_action;
        this.locale = locale;
    }

    public MBAdminAsyncTask_updateSection(Context context, MBAdminApiUpdateSectionListener listener, long section_id, ArrayList<MBAdminParameter> parameters,
                                          ArrayList<MBAdminParameterFile> parameters_files, String locale) {
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
        if (MBApiManagerUtils.hasMapOkResults(map, false)) {
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
                    listener.onSectionUpdatedError(error);
                } else {
                    listener.onSectionUpdated(section_id);
                }
            }
        }
    }

    public void putValuesAndCall() {
        String api = MBApiManagerConfig.API
                + MBApiManagerConfig.API_SECTION
                + "/"
                + Long.toString(section_id)
                + MBApiManagerConfig.API_UPDATE;

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

}
