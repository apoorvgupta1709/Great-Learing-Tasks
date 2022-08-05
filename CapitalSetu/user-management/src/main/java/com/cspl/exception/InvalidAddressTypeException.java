package com.cspl.exception;

import com.cspl.commons.exception.BaseException;

public class InvalidAddressTypeException extends BaseException {
    private static final String ERROR_CODE = "CSPL-310";
    private static final String ERROR_MESSAGE = "Invalid Address Type";

    public InvalidAddressTypeException(){
        super(ERROR_CODE, ERROR_MESSAGE);
    }

    public InvalidAddressTypeException(String errorMessage){
        super(ERROR_CODE, errorMessage);
    }

}
