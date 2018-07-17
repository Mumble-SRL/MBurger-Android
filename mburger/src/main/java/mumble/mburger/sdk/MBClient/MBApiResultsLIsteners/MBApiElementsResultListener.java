package mumble.mburger.sdk.MBClient.MBApiResultsLIsteners;

import android.content.Context;

import java.util.ArrayList;
import java.util.Map;

import mumble.mburger.sdk.Common.MBConstants.MBConstants;
import mumble.mburger.sdk.MBClient.MBData.MBAtomic.MBClass;
import mumble.mburger.sdk.MBClient.MBurgerTasks;

/**
 * Interface to use with {@link MBurgerTasks#askForSections(Context, long, ArrayList, boolean)}, and similar, methods,
 * returns an ArrayList of {@link mumble.mburger.sdk.MBClient.MBData.MBSections.MBSection} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBApiElementsResultListener {
    void onElementsApiResult(Map<String, MBClass> elements, long section_id);
    void onElementsApiError(String error);
}
