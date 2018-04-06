package mumble.nooko3.sdk.NControllers.NApiResultsLIsteners;

import android.content.Context;

import mumble.nooko3.sdk.NConstants.NConst;
import mumble.nooko3.sdk.NData.NBlocks.NBlock;

/**
 * Interface to use with {@link mumble.nooko3.sdk.Nooko3Tasks#askForBlock(Context, long, boolean)}, and similar, methods,
 * returns a {@link NBlock} if all went right or an error message if something went wrong
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public interface NApiBlockResultListener {
    void onBlockApiResult(NBlock block);

    void onBlockApiError(String error);
}
