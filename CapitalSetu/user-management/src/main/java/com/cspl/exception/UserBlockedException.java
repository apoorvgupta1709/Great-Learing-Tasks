package com.cspl.exception;

import com.cspl.commons.exception.BaseException;

public class UserBlockedException extends BaseException {
    private static final String ERROR_CODE = "CSPL-304";
    private static final String ERROR_MESSAGE = "User is blocked";

    public UserBlockedException() {
        super(ERROR_CODE, ERROR_MESSAGE);
    }

    public UserBlockedException(String errorMessage) {
        super(ERROR_CODE, errorMessage);
    }

}
