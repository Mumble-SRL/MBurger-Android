package mumble.mburger.sdk.MBClient.MBApiResultsLIsteners;

import android.content.Context;

import java.util.ArrayList;

import mumble.mburger.sdk.MBClient.MBData.MBBlocks.MBBlock;
import mumble.mburger.sdk.MBClient.MBData.MBPaginationInfo;
import mumble.mburger.sdk.MBClient.MBurgerTasks;
import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Interface to use with {@link MBurgerTasks#askForBlocks(Context, ArrayList, boolean)}}, and similar, methods,
 * returns an ArrayList of {@link MBBlock} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBApiBlocksResultListener {
    void onBlocksApiResult(ArrayList<MBBlock> blocks, MBPaginationInfo paginationInfos);
    void onBlocksApiError(String error);
}
