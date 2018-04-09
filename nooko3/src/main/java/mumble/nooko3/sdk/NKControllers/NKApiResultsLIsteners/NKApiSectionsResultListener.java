package mumble.nooko3.sdk.NKControllers.NKApiResultsLIsteners;

import android.content.Context;

import java.util.ArrayList;

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKPaginationInfo;
import mumble.nooko3.sdk.NKData.NKSections.NKSection;

/**
 * Interface to use with {@link mumble.nooko3.sdk.Nooko3Tasks#askForSections(Context, long, ArrayList, boolean)}, and similar, methods,
 * returns an ArrayList of {@link NKSection} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKApiSectionsResultListener {
    void onSectionsApiResult(ArrayList<NKSection> sections, NKPaginationInfo paginationInfos);
    void onSectionsApiError(String error);
}
