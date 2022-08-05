package com.cspl.commons.exception;

public class RequestFailedException extends BaseException {

    private static final String errorCode = "CSPL-502";
    private static final String errorMessage = "Third party API Down";

    public RequestFailedException() {
        super(errorCode, errorMessage);
    }

    public RequestFailedException(String errorMessage) {
        super(errorCode, errorMessage);
    }

}
