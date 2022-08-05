package com.cspl.service.helper;

import java.util.HashMap;
import java.util.Map;

import com.cspl.commons.exception.BaseException;
import com.cspl.commons.util.CommonUtility;
import com.cspl.constants.UserConfig;
import com.cspl.dao.OtpDao;
import com.cspl.exception.InvalidOtpException;
import com.cspl.exception.OtpExpiredException;
import com.cspl.exception.OtpNotTriggeredException;
import com.cspl.models.Otp;
import com.cspl.request.OtpRequest;
import com.cspl.request.TriggeredOtpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;

/**
 * This class contains Helper methods for otp service
 *
 * @author Ashutosh
 */
@Service
@Slf4j
public class OtpServiceHelper {

    @Autowired
    private OtpDao otpDao;

    @Autowired
    private UserConfig userConfig;

    /**
     * This method will validate request for otp validation
     *
     * @param data
     * @return
     */
    public Boolean validateOtpRequest(OtpRequest data) {

        log.info("Entering into [OtpServiceHelper → validateOtpRequest] for mobileNumber {}", data.getMobileNumber());

        return true;
    }

    /**
     * This method will check if otp is valid
     *
     * @param requestData Otp data entered by user
     * @return  true if otp is valid else throws exception
     */
    public void checkOtpValidity(TriggeredOtpRequest requestData) throws BaseException{
        log.info("Entering into OtpServiceHelper->checkOtpValidity for mobileNumber {}", requestData.getMobileNumber());
        Otp otp = otpDao.findOtpByMobileNumber(requestData.getMobileNumber());

        if(otp == null){
            log.error("Otp was not triggered for given mobile number {}", requestData.getMobileNumber());
            throw new OtpNotTriggeredException();
        }
        if (otp.getValidTill() < (System.currentTimeMillis())) {
            log.error("Otp Expired for mobileNumber {}", requestData.getMobileNumber());
            throw new OtpExpiredException();
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // otp is correct
        if (encoder.matches(requestData.getOtp(), otp.getOtp())) {
            Map<String, Object> otpQueryMap = new HashMap<>();
            otpQueryMap.put("retryCount", 0);
            otpQueryMap.put("invalidOtpCount", 0);
            otpDao.updateOtp(requestData.getMobileNumber(), otpQueryMap);
            return;
        }
        // update invalid otp count
        Map<String, Object> otpQueryMap = new HashMap<>();
        otpQueryMap.put("invalidOtpCount", otp.getInvalidOtpCount() + 1);
        otpDao.updateOtp(requestData.getMobileNumber(), otpQueryMap);
        log.error("Invalid otp {}", requestData.getMobileNumber());
        throw new InvalidOtpException("Invalid otp count " + (otp.getInvalidOtpCount() + 1));
    }

    public String generateAndSendOtp(OtpRequest request) throws BaseException {

        log.info("Entering into OtpServiceHelper->generateAndSendOtp for mobileNumber {}", request.getMobileNumber());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String otpString = userConfig.getOtpDefaultValue();

        if (userConfig.getOtpValidateFlag()) {
            otpString = CommonUtility.generateOtp(userConfig.getOtpLength());
            // Send otp via notification
            // throw exception if any error while sending otp
        }
        return encoder.encode(otpString);
    }

}
