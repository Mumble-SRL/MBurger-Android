package mumble.nooko3.sdk.NControllers.NApiResultsLIsteners;

import android.content.Context;

import java.util.ArrayList;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NData.NBlocks.NBlock;
import mumble.nooko3.sdk.NData.NPaginationInfos;
import mumble.nooko3.sdk.NData.NProjects.NProject;

/**
 * Interface to use with {@link mumble.nooko3.sdk.Nooko3Tasks#askForBlocks(Context, ArrayList, boolean)}}, and similar, methods,
 * returns an ArrayList of {@link NBlock} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public interface NApiBlocksResultListener {
    void onBlocksApiResult(ArrayList<NBlock> blocks, NPaginationInfos paginationInfos);
    void onBlocksApiError(String error);
}
