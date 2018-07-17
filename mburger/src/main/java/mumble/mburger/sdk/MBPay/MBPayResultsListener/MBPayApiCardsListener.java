package mumble.mburger.sdk.MBPay.MBPayResultsListener;

import java.util.ArrayList;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBPay.MBPayData.MBStripeCard;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_GetCards;

/**
 * Interface to use with {@link MBPayAsyncTask_GetCards (Context)}, and similar, methods,
 * returns the authentication token
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBPayApiCardsListener {
    void onCardsObtained(ArrayList<MBStripeCard> cards);
    void onCardsObtainedError(String error);
}
