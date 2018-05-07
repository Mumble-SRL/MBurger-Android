package mumble.nooko3.sdk.NKAdminAsyncTasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

import mumble.nooko3.sdk.NKAdminData.NKAdminParameter;
import mumble.nooko3.sdk.NKConstants.NKAPIConstants;
import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKControllers.NKAdminResultsListeners.NKAdminApiAddSectionListener;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerOKHTTPPost;
import mumble.nooko3.sdk.NKControllers.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.NKControllers.NKCommonMethods;
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

    public NKAdminAsyncTask_addSection(Context context, ArrayList<NKAdminParameter> parameters) {
        this.weakContext = new WeakReference<>(context);
        this.parameters = parameters;
    }

    public NKAdminAsyncTask_addSection(Context context, String custom_action, ArrayList<NKAdminParameter> parameters) {
        this.weakContext = new WeakReference<>(context);
        this.parameters = parameters;
        this.action = custom_action;
    }

    public NKAdminAsyncTask_addSection(Context context, NKAdminApiAddSectionListener listener, ArrayList<NKAdminParameter> parameters) {
        this.weakContext = new WeakReference<>(context);
        this.parameters = parameters;
        this.listener = listener;
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
        String api = NKApiManagerConfig.API + NKApiManagerConfig.API_SECTION;
        MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        for (int i = 0; i < parameters.size(); i++) {
            NKAdminParameter param = parameters.get(i);
            if (param.isFile()) {
                requestBodyBuilder.addFormDataPart(param.getKey(), param.getValue(),
                        RequestBody.create(MediaType.parse(param.getMime_type()), new File(param.getFilepath())));
            } else {
                requestBodyBuilder.addFormDataPart(param.getKey(), param.getValue());
            }
        }

        map = NKApiManagerOKHTTPPost.callApi(weakContext.get(), api, requestBodyBuilder.build(), true);
    }
}
