package mumble.nooko3.sdk.NKPay.NKPayResultsListener;

import java.util.ArrayList;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKPay.NKPayData.NKStripeCard;

/**
 * Interface to use with {@link mumble.nooko3.sdk.NKPay.NKPayAsyncTasks.NKPayAsyncTask_GetCards (Context)}, and similar, methods,
 * returns the authentication token
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKPayApiCardsListener {
    void onCardsObtained(ArrayList<NKStripeCard> cards);
    void onCardsObtainedError(String error);
}
