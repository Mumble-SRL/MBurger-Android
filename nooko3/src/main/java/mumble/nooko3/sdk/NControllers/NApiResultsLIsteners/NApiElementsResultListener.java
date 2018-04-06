package mumble.nooko3.sdk.NControllers.NApiResultsLIsteners;

import android.content.Context;

import java.util.ArrayList;
import java.util.Map;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NData.NAtomic.NClass;
import mumble.nooko3.sdk.NData.NPaginationInfos;
import mumble.nooko3.sdk.NData.NSections.NSection;

/**
 * Interface to use with {@link mumble.nooko3.sdk.Nooko3Tasks#askForSections(Context, long, ArrayList, boolean)}, and similar, methods,
 * returns an ArrayList of {@link NSection} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public interface NApiElementsResultListener {
    void onElementsApiResult(Map<String, NClass> elements);
    void onElementsApiError(String error);
}
