package com.cspl.commons.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class CommonUtility {

    /**
     * This method will generate otp with required length and characters
     *
     * @param otpLength - Length of otp to be generated
     */
    public static String generateOtp(int otpLength) {

        log.info("Entering into [CommonUtility -> generateOtp] with otp length {}" , otpLength);
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        char[] chars = new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

        for (int i = 0; i < otpLength; i++) {
            buffer.append(chars[random.nextInt(chars.length)]);
        }
        return buffer.toString();
    }
}
