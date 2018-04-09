package mumble.nooko3.sdk.NKControllers.NKApiResultsLIsteners;

import android.content.Context;

import java.util.ArrayList;

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKBlocks.NKBlock;
import mumble.nooko3.sdk.NKData.NKPaginationInfo;

/**
 * Interface to use with {@link mumble.nooko3.sdk.Nooko3Tasks#askForBlocks(Context, ArrayList, boolean)}}, and similar, methods,
 * returns an ArrayList of {@link NKBlock} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKApiBlocksResultListener {
    void onBlocksApiResult(ArrayList<NKBlock> blocks, NKPaginationInfo paginationInfos);
    void onBlocksApiError(String error);
}
