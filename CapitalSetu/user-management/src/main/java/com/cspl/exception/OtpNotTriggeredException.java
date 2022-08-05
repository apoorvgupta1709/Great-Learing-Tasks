package com.cspl.exception;

import com.cspl.commons.exception.BaseException;

public class OtpNotTriggeredException extends BaseException {
    private static final String ERROR_CODE = "CSPL-305";
    private static final String ERROR_MESSAGE = "Otp was not triggered for given mobile number";

    public OtpNotTriggeredException(){
        super(ERROR_CODE, ERROR_MESSAGE);
    }

    public OtpNotTriggeredException(String errorMessage){
        super(ERROR_CODE, errorMessage);
    }

}
