package mumble.mburger.sdk.MBClient.MBApiResultsLIsteners;

import android.content.Context;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBData.MBBlocks.MBBlock;
import mumble.mburger.sdk.MBClient.MBurgerTasks;

/**
 * Interface to use with {@link MBurgerTasks#askForBlock(Context, long, boolean)}, and similar, methods,
 * returns a {@link mumble.mburger.sdk.MBClient.MBData.MBBlocks.MBBlock} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBApiBlockResultListener {
    void onBlockApiResult(MBBlock block);

    void onBlockApiError(String error);
}
