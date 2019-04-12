package mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;

import java.lang.ref.WeakReference;
import java.util.Map;

import mumble.mburger.sdk.Common.MBCommonMethods;
import mumble.mburger.sdk.Common.MBApiManager.MBAMActivityUtils;
import mumble.mburger.sdk.Common.MBApiManager.MBAPIManager3;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerConfig;
import mumble.mburger.sdk.Common.MBApiManager.MBApiManagerUtils;
import mumble.mburger.sdk.Common.MBConstants.MBAPIConstants;
import mumble.mburger.sdk.MBAuth.MBAuthResultsListener.MBAuthApiRegisterListener;

/**
 * Created by Enrico on 29/08/2016.
 */
public class MBAuthAsyncTask_Register extends AsyncTask<Void, Void, Void> {

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
     * Registration password
     */
    @NonNull
    private String password;

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
    private String action = MBAPIConstants.ACTION_REGISTER;

    /**
     * If you wish to use a listener to retrieve the data instead of the ApiListener
     */
    private MBAuthApiRegisterListener listener;

    private int result = MBApiManagerConfig.COMMON_INTERNAL_ERROR;
    private String error;
    private Map<String, Object> map;

    public MBAuthAsyncTask_Register(Context context, String name, String surname, String phone, Uri image,
                                    String email, String password, JSONArray contracts, String data) {
        this.weakContext = new WeakReference<>(context);
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.image = image;
        this.data = data;
        this.contracts = contracts;
    }

    public MBAuthAsyncTask_Register(Context context, String custom_action, String name, String surname,
                                    String phone, Uri image, String email, String password, JSONArray contracts, String data) {
        this.weakContext = new WeakReference<>(context);
        this.action = custom_action;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.image = image;
        this.data = data;
        this.contracts = contracts;
    }

    public MBAuthAsyncTask_Register(Context context, MBAuthApiRegisterListener listener, String name, String surname,
                                    String phone, Uri image, String email, String password, JSONArray contracts, String data) {
        this.weakContext = new WeakReference<>(context);
        this.listener = listener;
        this.email = email;
        this.password = password;
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

    public void putValuesAndCall() {
        ContentValues values = new ContentValues();
        values.put("name", name);

        if (surname != null) {
            values.put("surname", surname);
        }

        values.put("email", email);
        values.put("password", password);
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

        map = MBAPIManager3.callApi(weakContext.get(), MBApiManagerConfig.API_REGISTER, values,
                MBApiManagerConfig.MODE_POST, false, false);
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
                    listener.onRegistrationError(error);
                } else {
                    listener.onRegistrationSuccess();
                }
            }
        }
    }

}

