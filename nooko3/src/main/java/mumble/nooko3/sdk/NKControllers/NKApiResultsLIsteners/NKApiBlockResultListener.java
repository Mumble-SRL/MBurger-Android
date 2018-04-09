package mumble.nooko3.sdk.NKControllers.NKApiResultsLIsteners;

import android.content.Context;

import mumble.nooko3.sdk.NKConstants.NKConstants;
import mumble.nooko3.sdk.NKData.NKBlocks.NKBlock;

/**
 * Interface to use with {@link mumble.nooko3.sdk.Nooko3Tasks#askForBlock(Context, long, boolean)}, and similar, methods,
 * returns a {@link NKBlock} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public interface NKApiBlockResultListener {
    void onBlockApiResult(NKBlock block);
    void onBlockApiError(String error);
}
