package com.cspl.exception;

import com.cspl.commons.exception.BaseException;

public class PasswordMismatchException extends BaseException {
    private static final String ERROR_CODE = "CSPL-308";
    private static final String ERROR_MESSAGE = "Password mismatch found";

    public PasswordMismatchException() {
        super(ERROR_CODE, ERROR_MESSAGE);
    }

    public PasswordMismatchException(String errorMessage) {
        super(ERROR_CODE, errorMessage);
    }

}
