package com.cspl.exception;

import com.cspl.commons.exception.BaseException;

public class MaxOptRetryException extends BaseException {
    private static final String ERROR_CODE = "CSPL-302";
    private static final String ERROR_MESSAGE = "Maximum try limit exceeded try after some time.";

    public MaxOptRetryException() {
        super(ERROR_CODE, ERROR_MESSAGE);
    }

    public MaxOptRetryException(String errorMessage) {
        super(ERROR_CODE, errorMessage);
    }

}
