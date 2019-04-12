package mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.mburger.sdk.Common.MBParser;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.MBAuth.MBAuthData.MBAuthUser;
import mumble.mburger.sdk.MBAuth.MBAuthResultsListener.MBAuthApiProfileUpdateListener;

/**
 * Created by Enrico on 29/08/2016.
 */
public class MBAuthAsyncTask_UpdateProfile extends AsyncTask<Void, Void, Void> {

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
     * Contracts data
     */
    @Nullable
    private JSONArray contracts;

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
    private String action = MBAPIConstants.ACTION_UPDATE_PROFILE;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private MBAuthApiProfileUpdateListener listener;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    /**
     * User obtained from API
     */
    private MBAuthUser user;

    public MBAuthAsyncTask_UpdateProfile(Context context, String name, String surname, String phone, Uri image,
                                         String email, JSONArray contracts, String data) {
        this.weakContext = new WeakReference<>(context);
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.image = image;
        this.data = data;
        this.contracts = contracts;
    }

    public MBAuthAsyncTask_UpdateProfile(Context context, String custom_action, String name, String surname,
                                         String phone, Uri image, String email, JSONArray contracts, String data) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.image = image;
        this.data = data;
        this.contracts = contracts;
    }

    public MBAuthAsyncTask_UpdateProfile(Context context, MBAuthApiProfileUpdateListener listener, String name, String surname,
                                         String phone, Uri image, String email, JSONArray contracts, String data) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.image = image;
        this.data = data;
        this.contracts = contracts;
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
            String b64Img = MBCommonMethods.fromUriToBase64(weakContext.get(), image);
            if (b64Img != null) {
                values.put("image", b64Img);
            }
        }

        if (data != null) {
            values.put("data", data);
        }

        if(contracts != null){
            if(contracts.length() != 0) {
                values.put("contracts", contracts.toString());
            }
        }

        map = MBAPIManager3.callApi(weakContext.get(), MBApiManagerConfig.API_PROFILE_UPDATE,
                values, MBApiManagerConfig.MODE_POST, true, false);
    }

    protected void onPostExecute(Void postResult) {
        if (weakContext != null) {
            if (listener == null) {
                Intent i = new Intent(action);
                i.putExtra("result", result);
                i.putExtra("error", error);
                MBAMActivityUtils.sendBroadcastMessage(weakContext.get(), i);
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
            user = MBParser.parseUser(jObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

