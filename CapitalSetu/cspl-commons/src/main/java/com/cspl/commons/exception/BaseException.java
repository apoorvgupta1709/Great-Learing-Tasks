package com.cspl.commons.exception;

/**
 * Base Exception class, used by any other exception thrown across application
 * @author Ashutosh
 */
public class BaseException extends Exception {
    // Default error code
    private String errorCode = "CSPL-999";

    public BaseException() {
    }

    public BaseException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(String message) {
        super(message);
    }

    public String getErrorCode() {
        return errorCode;
    }
}
