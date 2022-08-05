package com.cspl.service;

import javax.servlet.http.HttpServletRequest;

import com.cspl.commons.exception.BaseException;
import com.cspl.request.OtpRequest;
import com.cspl.request.TriggeredOtpRequest;
import com.cspl.response.OtpResponse;

/**
 * This interface contains methods related to otp (trigger, validation)
 *
 * @author Ashutosh
 */
public interface OtpService {

    /**
     * This method will trigger otp
     */
    void triggerOtp(OtpRequest data) throws BaseException;

    /**
     * This method will validate mobile otp
     */
    OtpResponse validateOtp(TriggeredOtpRequest data, HttpServletRequest servletRequest) throws BaseException;

	
}
