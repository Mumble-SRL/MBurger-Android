package mumble.nooko3.sdk.NKControllers.NKApiResultsLIsteners;

import android.content.Context;

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKProjects.NKProject;

/**
 * Interface to use with {@link mumble.nooko3.sdk.Nooko3Tasks#askForProject(Context)}, and similar, methods,
 * returns a {@link NKProject} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKApiVotePollListener {
    void onPollVotedApiResult(int mine);
    void onPollVotedApiError(String error);
}
