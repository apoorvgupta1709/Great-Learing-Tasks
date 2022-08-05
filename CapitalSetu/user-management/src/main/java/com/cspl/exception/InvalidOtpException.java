package com.cspl.exception;

import com.cspl.commons.exception.BaseException;

public class InvalidOtpException extends BaseException {
    private static final String ERROR_CODE = "CSPL-307";
    private static final String ERROR_MESSAGE = "Invalid otp";

    public InvalidOtpException(){
        super(ERROR_CODE, ERROR_MESSAGE);
    }

    public InvalidOtpException(String errorMessage){
        super(ERROR_CODE, errorMessage);
    }

}
