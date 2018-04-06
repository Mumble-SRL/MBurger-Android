package mumble.nooko3.sdk.NControllers.NApiResultsLIsteners;

import java.util.ArrayList;

import mumble.nooko3.sdk.NData.NBlocks.NBlock;
import mumble.nooko3.sdk.NData.NPaginationInfos;
import mumble.nooko3.sdk.NData.NSections.NSection;

public interface NApiSectionsResultListener {
    void onSectionsApiResult(ArrayList<NSection> sections, NPaginationInfos paginationInfos);
    void onSectionsApiError(String error);
}
