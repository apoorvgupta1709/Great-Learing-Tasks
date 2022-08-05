package com.cspl.commons.exception;

public class ObjectMappingFailedException extends BaseException{
    private static final String errorCode = "CSPL-503";
    private static final String errorMessage = "Object mapping failed";

    public ObjectMappingFailedException() {
        super(errorCode, errorMessage);
    }

    public ObjectMappingFailedException(String errorMessage) {
        super(errorCode, errorMessage);
    }

}
