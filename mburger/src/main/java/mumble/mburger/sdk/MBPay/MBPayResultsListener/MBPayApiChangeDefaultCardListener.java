package mumble.mburger.sdk.MBPay.MBPayResultsListener;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBPay.MBPayAsyncTasks.MBPayAsyncTask_ChangeDefaultCard;

/**
 * Interface to use with {@link MBPayAsyncTask_ChangeDefaultCard (Context)}, and similar, methods,
 * returns the authentication token
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBPayApiChangeDefaultCardListener {
    void onDefaultCardChanged();

    void onDefaultCardChangedError(String error);
}
