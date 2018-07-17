package mumble.mburger.sdk.MBAuth.MBAuthResultsListener;

import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_Authenticate;
import mumble.mburger.sdk.MBAuth.MBAuthAsyncTasks.MBAuthAsyncTask_Register;
import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Interface to use with {@link MBAuthAsyncTask_Register}, and similar, methods,
 * returns nothing, to obtain jwt token should call {@link MBAuthAsyncTask_Authenticate}
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBAuthApiRegisterListener {
    void onRegistrationSuccess();
    void onRegistrationError(String error);
}
