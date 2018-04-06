package mumble.nooko3.sdk.NControllers.NApiResultsLIsteners;

import mumble.nooko3.sdk.NData.NProjects.NProject;

public interface NApiProjectResultListener {
    void onProjectApiResult(NProject project);
    void onProjectApiError(String error);
}
