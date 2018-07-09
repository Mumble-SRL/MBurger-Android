package mumble.nooko3.sdk.NKPay.NKPayResultsListener;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKAuth.NKAuthAsyncTasks.NKAuthAsyncTask_Authenticate;

/**
 * Interface to use with {@link NKAuthAsyncTask_Authenticate (Context)}, and similar, methods,
 * returns the authentication token
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKPayApiAddCardListener {
    void onCardAdded();
    void onCardAddedError(String error);
}
