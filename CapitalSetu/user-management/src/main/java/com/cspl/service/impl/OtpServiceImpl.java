package com.cspl.service.impl;

import static com.cspl.constants.Constants.USER_COLLECTION_NAME;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspl.commons.exception.BaseException;
import com.cspl.constants.Constants;
import com.cspl.constants.UserConfig;
import com.cspl.dao.OtpDao;
import com.cspl.dao.UserDao;
import com.cspl.dao.impl.CommonDao;
import com.cspl.exception.MaxOptRetryException;
import com.cspl.exception.UserBlockedException;
import com.cspl.exception.UserDeletedException;
import com.cspl.models.ApplicationInfo;
import com.cspl.models.Otp;
import com.cspl.models.User;
import com.cspl.request.OtpRequest;
import com.cspl.request.TriggeredOtpRequest;
import com.cspl.response.OtpResponse;
import com.cspl.service.OtpService;
import com.cspl.service.helper.OtpServiceHelper;
import com.cspl.service.helper.UserServiceHelper;
import com.cspl.utils.HttpRequestUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Otp related operation
 *
 * @author Ashutosh
 */
@Service
@Slf4j
public class OtpServiceImpl implements OtpService {

	
    @Autowired
    private OtpServiceHelper otpServiceHelper;

    @Autowired
    private UserConfig userConfig;

    @Autowired
    private OtpDao otpDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private HttpRequestUtil httpRequestUtil;

    @Autowired
    private UserServiceHelper userServiceHelper;

    @Override
    public void triggerOtp(OtpRequest request) throws BaseException {
        log.info("Entering into [OtpServiceImpl -> triggerOtp] {}", request.getMobileNumber());
        User user = userDao.findUserByMobileNumber(request.getMobileNumber());
        if (null == user) {
            // user not present in the system, trigger otp or update it
            checkIfOtpExistsOrNotAndTriggerOtp(request);
        } else if (StringUtils.equalsIgnoreCase(user.getStatus(), Constants.UserStatus.DELETED.getStatus())) {
            log.error("User deleted for mobileNumber {}.", request.getMobileNumber());
            throw new UserDeletedException();
        } else if (StringUtils.equalsIgnoreCase(user.getStatus(), Constants.UserStatus.BLOCKED.getStatus())) {
            // user is blocked, check if it is possible to unblock
            checkIfUserCanBeUnBlockedAndTriggerOtp(request, user);
        } else if (StringUtils.equalsIgnoreCase(user.getStatus(), Constants.UserStatus.ACTIVE.getStatus())) {
            // User is active, check if user has not crossed max limit or else send otp
            checkIfUserHasCrossedLimitAndTriggerOtp(request, user);
        }
        //TODO - Any other cases needs to be implemented here
    }

    /**
     * Check if user has crossed retry limit, if yes @throw Exception
     * or else trigger otp
     *
     * @param request Input request
     * @param user    User details for given mobile number
     * @throws BaseException
     */
    private void checkIfUserHasCrossedLimitAndTriggerOtp(OtpRequest request, User user) throws BaseException {
        log.info("Entering into [OtpServiceImpl -> checkIfUserHasCrossedLimitAndTriggerOtp] {}", request.getMobileNumber());
        Otp otp = otpDao.findOtpByMobileNumber(request.getMobileNumber());

        if (null != otp) {
            if (otp.getRetryCount() >= userConfig.getRetryCount()) {
                Map<String, Object> userQueryMap = new HashMap<>();
                userQueryMap.put("status", Constants.UserStatus.BLOCKED.getStatus());
                userDao.updateUser(user.getUserId(), userQueryMap);

                // update otp unblocked time
                Map<String, Object> otpQueryMap = new HashMap<>();
                otpQueryMap.put("unBlockAt", System.currentTimeMillis() + userConfig.getRetryResetTime() * 1000 * 60 * 60);
                otpDao.updateOtp(request.getMobileNumber(), otpQueryMap);

                log.error("Maximum try limit exceeded for mobileNumber {}", request.getMobileNumber());
                throw new MaxOptRetryException("Maximum try limit exceeded try after " + userConfig.getRetryResetTime() + " hours.");
            }
            String encodedOtp = otpServiceHelper.generateAndSendOtp(request);
            // update otp details
            Map<String, Object> otpQueryMap = new HashMap<>();
            otpQueryMap.put("otp", encodedOtp);
            otpQueryMap.put("validTill", System.currentTimeMillis() + userConfig.getOtpValidity() * 1000 * 60);
            otpQueryMap.put("retryCount", otp.getRetryCount() + 1);
            otpDao.updateOtp(request.getMobileNumber(), otpQueryMap);
            log.info("Otp sent for mobileNumber {}", request.getMobileNumber());
        }
    }

    /**
     * Check if user is blocked and if can be unblocked then trigger otp else @throw exception
     *
     * @param request Input request
     * @param user    user with given input
     * @throws BaseException
     */
    private void checkIfUserCanBeUnBlockedAndTriggerOtp(OtpRequest request, User user) throws BaseException {
        log.info("Entering into [OtpServiceImpl -> checkIfUserCanBeUnBlockedAndTriggerOtp] {}", request.getMobileNumber());
        Otp otp = otpDao.findOtpByMobileNumber(request.getMobileNumber());

        if (null != otp && otp.getUnBlockAt() < System.currentTimeMillis()) {
            String encodedOtp = otpServiceHelper.generateAndSendOtp(request);
            Map<String, Object> otpQueryMap = new HashMap<>();
            otpQueryMap.put("otp", encodedOtp);
            otpQueryMap.put("validTill", System.currentTimeMillis() + userConfig.getOtpValidity() * 1000 * 60);
            otpQueryMap.put("retryCount", 0);
            otpDao.updateOtp(request.getMobileNumber(), otpQueryMap);
            // update user
            Map<String, Object> userQueryMap = new HashMap<>();
            userQueryMap.put("status", Constants.UserStatus.ACTIVE.getStatus());
            userDao.updateUser(user.getUserId(), userQueryMap);

            log.info("Otp sent for mobileNumber {}", request.getMobileNumber());
            return;
        }

        log.error("User blocked for mobileNumber {}", request.getMobileNumber());
        throw new UserBlockedException();
    }

    /**
     * Trigger otp for the user who is not yet validated
     *
     * @param request Input request
     * @throws BaseException
     */
    private void checkIfOtpExistsOrNotAndTriggerOtp(OtpRequest request) throws BaseException {
        log.info("Entering into [OtpServiceImpl -> checkIfOtpExistsOrNotAndTriggerOtp] {}", request.getMobileNumber());
        Otp otp = otpDao.findOtpByMobileNumber(request.getMobileNumber());

        if (null == otp) {
            String encodedOtp = otpServiceHelper.generateAndSendOtp(request);
            otp = new Otp(request.getMobileNumber(), encodedOtp, System.currentTimeMillis() + userConfig.getOtpValidity() * 1000 * 60);
            commonDao.save(otp);
            log.info("Otp sent for mobileNumber {}.", request.getMobileNumber());
        } else if (otp.getRetryCount() >= userConfig.getRetryCount()) {
            if (null != otp.getUnBlockAt() && otp.getUnBlockAt() <= System.currentTimeMillis()) {
                triggerAndUpdateOtp(request);
                return;
            }
            Map<String, Object> otpQueryMap = new HashMap<>();
            otpQueryMap.put("unBlockAt", System.currentTimeMillis() + userConfig.getRetryResetTime() * 1000 * 60 * 60L);
            otpDao.updateOtp(request.getMobileNumber(), otpQueryMap);

            log.error("Maximum try limit exceeded for mobileNumber {}.", request.getMobileNumber());
            throw new MaxOptRetryException(StringUtils.join("Maximum try limit exceeded try after ", userConfig.getRetryResetTime(), " hours."));
        }

        String encodedOtp = otpServiceHelper.generateAndSendOtp(request);
        Map<String, Object> otpQueryMap = new HashMap<>();
        otpQueryMap.put("otp", encodedOtp);
        otpQueryMap.put("validTill", System.currentTimeMillis() + userConfig.getOtpValidity() * 1000 * 60);
        otpQueryMap.put("retryCount", otp.getRetryCount() + 1);
        otpDao.updateOtp(request.getMobileNumber(), otpQueryMap);

        log.info("Otp sent for mobileNumber {}.", request.getMobileNumber());
    }

    private void triggerAndUpdateOtp(OtpRequest request) throws BaseException {
        Map<String, Object> otpQueryMap = new HashMap<>();
        String encodedOtp = otpServiceHelper.generateAndSendOtp(request);
        otpQueryMap.put("otp", encodedOtp);
        otpQueryMap.put("validTill", StringUtils.join(System.currentTimeMillis(), userConfig.getOtpValidity() * 1000 * 60));
        otpQueryMap.put("retryCount", 0);
        otpQueryMap.put("unBlockAt", null);
        otpDao.updateOtp(request.getMobileNumber(), otpQueryMap);
    }

    @Override
    public OtpResponse validateOtp(TriggeredOtpRequest data, HttpServletRequest servletRequest) throws BaseException {
        // validates otp, in case its invalid, request will fail
        otpServiceHelper.checkOtpValidity(data);
        // reached here, means otp is valid
        User user = userDao.findUserByMobileNumber(data.getMobileNumber());

        if (null == user) {
            Long seqId = commonDao.getNextSequenceId(USER_COLLECTION_NAME, User.class);
            user = new User(userServiceHelper.generateCustomUserId(seqId), data.getMobileNumber()
                    , Constants.UserStatus.ACTIVE.getStatus(), servletRequest.getRemoteAddr());
        }

        user.setLastVisitedIp(servletRequest.getRemoteAddr())
                .setLastStatusUpdatedDate(LocalDateTime.now());
        commonDao.save(user);
        // Generate token here ? or after password is set ?
        ApplicationInfo applicationInfo=new ApplicationInfo();
        applicationInfo.setUserId(user.getUserId());
        applicationInfo.setMobileNumber(user.getMobileNumber());
        applicationInfo=commonDao.saveAndGet(applicationInfo);
        
        return new OtpResponse(applicationInfo.getId());
    }

	


}
