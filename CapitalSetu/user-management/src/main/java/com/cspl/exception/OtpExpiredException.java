package com.cspl.exception;

import com.cspl.commons.exception.BaseException;

public class OtpExpiredException extends BaseException {
    private static final String ERROR_CODE = "CSPL-306";
    private static final String ERROR_MESSAGE = "Otp is expired, please resend the otp";

    public OtpExpiredException(){
        super(ERROR_CODE, ERROR_MESSAGE);
    }

    public OtpExpiredException(String errorMessage){
        super(ERROR_CODE, errorMessage);
    }

}
