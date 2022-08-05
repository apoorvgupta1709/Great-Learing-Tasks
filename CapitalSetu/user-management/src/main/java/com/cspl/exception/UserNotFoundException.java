package com.cspl.exception;

import com.cspl.commons.exception.BaseException;

public class UserNotFoundException extends BaseException {
    private static final String ERROR_CODE = "CSPL-301";
    private static final String ERROR_MESSAGE = "User not found";

    public UserNotFoundException() {
        super(ERROR_CODE, ERROR_MESSAGE);
    }

    public UserNotFoundException(String errorMessage) {
        super(ERROR_CODE, errorMessage);
    }

}
