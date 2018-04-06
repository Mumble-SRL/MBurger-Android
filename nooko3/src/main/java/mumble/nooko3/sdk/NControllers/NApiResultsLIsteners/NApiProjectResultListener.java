package mumble.nooko3.sdk.NControllers.NApiResultsLIsteners;

import android.content.Context;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NData.NProjects.NProject;
import mumble.nooko3.sdk.NData.NSections.NSection;

/**
 * Interface to use with {@link mumble.nooko3.sdk.Nooko3Tasks#askForProject(Context)}, and similar, methods,
 * returns a {@link NProject} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public interface NApiProjectResultListener {
    void onProjectApiResult(NProject project);
    void onProjectApiError(String error);
}
