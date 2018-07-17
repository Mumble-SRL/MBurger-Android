package mumble.mburger.sdk.MBClient.MBApiResultsLIsteners;

import android.content.Context;

import mumble.mburger.sdk.MBClient.MBData.MBSections.MBSection;
import mumble.mburger.sdk.MBClient.MBurgerTasks;
import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Interface to use with {@link MBurgerTasks#askForSection(Context, long, boolean)}, and similar, methods,
 * returns a {@link mumble.mburger.sdk.MBClient.MBData.MBSections.MBSection} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBApiSectionResultListener {
    void onSectionApiResult(MBSection section, long section_id);
    void onSectionApiError(String error);
}
