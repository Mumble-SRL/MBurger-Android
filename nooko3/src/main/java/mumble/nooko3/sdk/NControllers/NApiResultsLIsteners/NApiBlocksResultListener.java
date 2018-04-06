package mumble.nooko3.sdk.NControllers.NApiResultsLIsteners;

import java.util.ArrayList;

import mumble.nooko3.sdk.NData.NBlocks.NBlock;
import mumble.nooko3.sdk.NData.NPaginationInfos;
import mumble.nooko3.sdk.NData.NProjects.NProject;

public interface NApiBlocksResultListener {
    void onBlocksApiResult(ArrayList<NBlock> blocks, NPaginationInfos paginationInfos);
    void onBlocksApiError(String error);
}
