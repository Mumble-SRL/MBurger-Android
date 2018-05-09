package mumble.nooko3.sdk.NKClient.NKApiResultsLIsteners;

import android.content.Context;

import java.util.ArrayList;

import mumble.nooko3.sdk.NKClient.Nooko3Tasks;
import mumble.nooko3.sdk.Common.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKPaginationInfo;
import mumble.nooko3.sdk.NKData.NKSections.NKSection;

/**
 * Interface to use with {@link Nooko3Tasks#askForSections(Context, long, ArrayList, boolean)}, and similar, methods,
 * returns an ArrayList of {@link NKSection} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKApiSectionsResultListener {
    void onSectionsApiResult(ArrayList<NKSection> sections, long block_id, NKPaginationInfo paginationInfos);
    void onSectionsApiError(String error);
}
