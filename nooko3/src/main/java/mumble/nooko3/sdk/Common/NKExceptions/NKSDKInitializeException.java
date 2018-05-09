package mumble.nooko3.sdk.Common.NKExceptions;

import mumble.nooko3.sdk.Common.NKConstants.NKConstants;

/**
 * Exception called when doing an operation before SDK initilization (put in API KEY).
 *
 * @author Enrico Ori
 * @version {@value NKConstants#version}
 */
public class NKSDKInitializeException extends RuntimeException {
    public NKSDKInitializeException(String message) {
        super(message);
    }
}