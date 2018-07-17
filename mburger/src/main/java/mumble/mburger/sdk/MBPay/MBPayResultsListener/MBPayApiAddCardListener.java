package mumble.mburger.sdk.MBPay.MBPayResultsListener;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_Authenticate;

/**
 * Interface to use with {@link MBAuthAsyncTask_Authenticate (Context)}, and similar, methods,
 * returns the authentication token
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBPayApiAddCardListener {
    void onCardAdded();
    void onCardAddedError(String error);
}
