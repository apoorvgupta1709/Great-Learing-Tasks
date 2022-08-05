package com.cspl.dao;

import java.util.Map;

import com.cspl.models.Otp;

/**
 * Methods for Otp related database queries
 *
 * @author Parul
 */
public interface OtpDao {

    /**
     * This method will find otp using mobile number
     *
     * @param mobileNumber Input to find otp
     * @return returns otp object
     */
    Otp findOtpByMobileNumber(String mobileNumber);

    /**
     * This method will update otp
     *
     * @param mobileNumber Mobile number for updating otp
     * @param updateData   Update data
     */
    Boolean updateOtp(String mobileNumber, Map<String, Object> updateData);

}
