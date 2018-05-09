package mumble.nooko3.sdk.NKClient.NKApiResultsLIsteners;

import android.content.Context;

import mumble.nooko3.sdk.NKClient.Nooko3Tasks;
import mumble.nooko3.sdk.Common.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKSections.NKSection;

/**
 * Interface to use with {@link Nooko3Tasks#askForSection(Context, long, boolean)}, and similar, methods,
 * returns a {@link NKSection} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKApiSectionResultListener {
    void onSectionApiResult(NKSection section, long section_id);
    void onSectionApiError(String error);
}
