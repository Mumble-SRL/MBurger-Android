package mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.nooko3.sdk.Common.NKApiManager.NAMActivityUtils;
import mumble.nooko3.sdk.Common.NKApiManager.NKAPIManager3;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerConfig;
import mumble.nooko3.sdk.Common.NKApiManager.NKApiManagerUtils;
import mumble.nooko3.sdk.Common.NKCommonMethods;
import mumble.nooko3.sdk.Common.NKConstants.NKAPIConstants;
import mumble.nooko3.sdk.Common.NKParser;
import mumble.nooko3.sdk.NKAuth.NKAuthData.NKAuthUser;
import mumble.nooko3.sdk.NKAuth.NKAuthResultsListener.NKAuthApiProfileListener;
import mumble.nooko3.sdk.NKAuth.NKAuthResultsListener.NKAuthApiProfileUpdateListener;
import mumble.nooko3.sdk.NKAuth.NKAuthResultsListener.NKAuthApiRegisterListener;

/**
 * Created by Enrico on 29/08/2016.
 */
public class NKAuthAsyncTask_UpdateProfile extends AsyncTask<Void, Void, Void> {

    /**
     * Context reference used to send data to Activity/Fragment
     */
    @NonNull
    private WeakReference<Context> weakContext;

    /**
     * Registration email
     */
    @NonNull
    private String email;

    /**
     * Registration name
     */
    @NonNull
    private String name;

    /**
     * Registration surname
     */
    @Nullable
    private String surname;

    /**
     * Registration phone
     */
    @Nullable
    private String phone;

    /**
     * Auxiliar registration data
     */
    @Nullable
    private String data;

    /**
     * Registration user image
     */
    @Nullable
    private Uri image;

    /**
     * If you wish to change the action that accompanies the API result
     */
    private String action = NKAPIConstants.ACTION_UPDATE_PROFILE;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private NKAuthApiProfileUpdateListener listener;

    private int result = NKApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    /**
     * User obtained from API
     */
    private NKAuthUser user;

    public NKAuthAsyncTask_UpdateProfile(Context context, String name, String surname, String phone, Uri image,
                                         String email, String data) {
        this.weakContext = new WeakReference<>(context);
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.image = image;
        this.data = data;
    }

    public NKAuthAsyncTask_UpdateProfile(Context context, String custom_action, String name, String surname,
                                         String phone, Uri image, String email, String data) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.image = image;
        this.data = data;
    }

    public NKAuthAsyncTask_UpdateProfile(Context context, NKAuthApiProfileUpdateListener listener, String name, String surname,
                                         String phone, Uri image, String email, String data) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.image = image;
        this.data = data;
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

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        values.put("name", name);

        if (surname != null) {
            values.put("surname", surname);
        }

        values.put("email", email);
        if (phone != null) {
            values.put("phone", phone);
        }

        if (image != null) {
            String b64Img = NKCommonMethods.fromUriToBase64(weakContext.get(), image);
            if (b64Img != null) {
                values.put("image", b64Img);
            }
        }

        if (data != null) {
            values.put("data", data);
        }

        map = NKAPIManager3.callApi(weakContext.get(), NKApiManagerConfig.API_PROFILE_UPDATE,
                values, NKApiManagerConfig.MODE_POST, true);
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
                    listener.onProfileObtainedError(error);
                } else {
                    listener.onProfileObtained(user);
                }
            }
        }
    }

    public void getPayload(String sPayload) {
        try {
            JSONObject jPayload = new JSONObject(sPayload);
            JSONObject jObj = jPayload.getJSONObject("body");
            user = NKParser.parseUser(jObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

