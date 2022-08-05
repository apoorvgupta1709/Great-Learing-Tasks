package com.cspl.exception;

import com.cspl.commons.exception.BaseException;

public class UserDeletedException extends BaseException {
    private static final String ERROR_CODE = "CSPL-303";
    private static final String ERROR_MESSAGE = "User is deleted";

    public UserDeletedException() {
        super(ERROR_CODE, ERROR_MESSAGE);
    }

    public UserDeletedException(String errorMessage) {
        super(ERROR_CODE, errorMessage);
    }
}
