package mumble.nooko3.sdk.NKClient.NKApiResultsLIsteners;

import android.content.Context;

import mumble.nooko3.sdk.NKClient.Nooko3Tasks;
import mumble.nooko3.sdk.Common.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKProjects.NKProject;

/**
 * Interface to use with {@link Nooko3Tasks#askForProject(Context)}, and similar, methods,
 * returns a {@link NKProject} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKApiProjectResultListener {
    void onProjectApiResult(NKProject project);
    void onProjectApiError(String error);
}
