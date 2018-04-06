package mumble.nooko3.sdk.NExceptions;

import mumble.nooko3.sdk.NConstants.NConst;

/**
 * Exception called when doing an operation before SDK initilization (put in API KEY).
 *
 * @author Enrico Ori
 * @version {@value NConst#version}
 */
public class NSDKInitializeException extends RuntimeException {
    public NSDKInitializeException(String message) {
        super(message);
    }
}