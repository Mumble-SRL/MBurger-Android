package mumble.nooko3.sdk.NControllers;
import mumble.nooko3.sdk.NData.APIResponse;

/**
 * Interface to retrieve Nooko api result, must be implemented in Activities or Fragments
 * which are working with the API
 */
public interface ApiResultListener {
    void onApiResult(APIResponse response);
}
