package com.cspl.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
@PropertySource("classpath:application.properties")
public class UserConfig {

    @Value("${byPass.services}")
    private String byPassServices;

    private List<String> allowedServices;

    /*
     * Otp configs
     */
    @Value("${otp.length}")
    private Integer otpLength;
    @Value("${otp.validate.flag}")
    private Boolean otpValidateFlag;
    @Value("${otp.default.value}")
    private String otpDefaultValue;
    @Value("${otp.retry.count}")
    private Integer retryCount;
    @Value("${otp.validity.time}")
    private Long otpValidity;
    @Value("${otp.retry.reset.time}")
    private Long retryResetTime;
    @Value("${otp.invalid.count}")
    private Integer invalidOtpCount;


    @PostConstruct
    private void setAllowedServices() {
        allowedServices = new ArrayList<>();
        if (StringUtils.isNotEmpty(byPassServices)) {
            String[] split = byPassServices.split(",");
            allowedServices.addAll(Arrays.asList(split));
        }
    }

}
