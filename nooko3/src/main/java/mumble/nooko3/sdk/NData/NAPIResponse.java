package mumble.nooko3.sdk.NData;

import android.os.Bundle;

import java.io.Serializable;

import mumble.nooko3.sdk.NConstants.NConst;

/**
 * Identifies a Nooko API response:
 * Result which says if API resulted ok or not
 * Error which, if not null, contains a localized error
 * Payload is a bundle which contains the payload of the api
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class NAPIResponse implements Serializable {

    private boolean result;
    private String error;
    private String apiAction;
    private Bundle payload;

    public NAPIResponse(boolean result, String error, String apiAction, Bundle payload) {
        this.result = result;
        this.error = error;
        this.apiAction = apiAction;
        this.payload = payload;
    }

    /**Get the result of the API*/
    public boolean getResult() {
        return result;
    }

    /**Set the result of the API*/
    public void setResult(boolean result) {
        this.result = result;
    }

    /**Get the localized error, if it happened*/
    public String getError() {
        return error;
    }

    /**Get the payload bundle, which contains the objects from the APIs*/
    public Bundle getPayload() {
        return payload;
    }

    /**Sets a payload bundle, which contains the objects from the APIs*/
    public void setPayload(Bundle payload) {
        this.payload = payload;
    }

    /**Get API action associated with the API called*/
    public String getApiAction() {
        return apiAction;
    }

    public void setApiAction(String apiAction) {
        this.apiAction = apiAction;
    }
}
