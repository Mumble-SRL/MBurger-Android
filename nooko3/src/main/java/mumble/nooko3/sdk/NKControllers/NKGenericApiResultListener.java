package mumble.nooko3.sdk.NKControllers;
import mumble.nooko3.sdk.NKData.NKAPIResponse;

/**
 * Interface to retrieve Nooko api result, must be implemented in Activities or Fragments
 * which are working with the API
 */
public interface NKGenericApiResultListener {
    void onApiResult(NKAPIResponse response);
}
