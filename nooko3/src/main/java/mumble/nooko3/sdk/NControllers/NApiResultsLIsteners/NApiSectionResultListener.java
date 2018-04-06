package mumble.nooko3.sdk.NControllers.NApiResultsLIsteners;

import android.content.Context;

import java.util.ArrayList;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NData.NSections.NSection;

/**
 * Interface to use with {@link mumble.nooko3.sdk.Nooko3Tasks#askForSection(Context, long, boolean)}, and similar, methods,
 * returns a {@link NSection} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public interface NApiSectionResultListener {
    void onSectionApiResult(NSection section);
    void onSectionApiError(String error);
}
