package mumble.nooko3.sdk.NControllers.NApiResultsLIsteners;

import java.util.ArrayList;

import mumble.nooko3.sdk.NData.NSections.NSection;

public interface NApiSectionResultListener {
    void onSectionApiResult(NSection section);
    void onSectionApiError(String error);
}
