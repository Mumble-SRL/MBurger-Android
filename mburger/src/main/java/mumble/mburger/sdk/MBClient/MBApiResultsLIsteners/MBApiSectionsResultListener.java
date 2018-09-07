package mumble.mburger.sdk.MBClient.MBApiResultsLIsteners;

import android.content.Context;

import java.util.ArrayList;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBData.MBPaginationInfo;
import mumble.mburger.sdk.MBClient.MBData.MBSections.MBSection;
import mumble.mburger.sdk.MBClient.MBurgerTasks;

/**
 * Interface to use with {@link MBurgerTasks#askForSections(Context, long, ArrayList, boolean)}, and similar, methods,
 * returns an ArrayList of {@link mumble.mburger.sdk.MBClient.MBData.MBSections.MBSection} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBApiSectionsResultListener {
    void onSectionsApiResult(ArrayList<MBSection> sections, long block_id, MBPaginationInfo paginationInfos);
    void onSectionsApiError(String error);
}
