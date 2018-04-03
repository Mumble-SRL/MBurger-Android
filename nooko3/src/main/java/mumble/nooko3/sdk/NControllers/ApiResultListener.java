package mumble.nooko3.sdk.NControllers;
import mumble.nooko3.sdk.NData.APIResponse;

public interface ApiResultListener {
    void onApiResult(APIResponse response);
}
