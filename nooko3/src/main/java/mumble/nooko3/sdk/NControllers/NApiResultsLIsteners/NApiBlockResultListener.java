package mumble.nooko3.sdk.NControllers.NApiResultsLIsteners;

import java.util.ArrayList;

import mumble.nooko3.sdk.NData.NBlocks.NBlock;

public interface NApiBlockResultListener {
    void onBlockApiResult(NBlock block);
    void onBlockApiError(String error);
}
