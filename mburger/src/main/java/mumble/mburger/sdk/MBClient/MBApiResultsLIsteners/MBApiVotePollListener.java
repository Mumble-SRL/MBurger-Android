package mumble.mburger.sdk.MBClient.MBApiResultsLIsteners;

import android.content.Context;

import mumble.mburger.sdk.MBClient.MBurgerTasks;
import mumble.mburger.sdk.Common.MBConstants.MBConstants;

/**
 * Interface to use with {@link MBurgerTasks#askForProject(Context)}, and similar, methods,
 * returns a {@link mumble.mburger.sdk.MBClient.MBData.MBProjects.MBProject} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value MBConstants#version}
 */
public interface MBApiVotePollListener {
    void onPollVotedApiResult(int mine);
    void onPollVotedApiError(String error);
}
