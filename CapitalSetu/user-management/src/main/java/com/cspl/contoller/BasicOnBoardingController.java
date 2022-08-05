package com.cspl.contoller;

import static com.cspl.commons.util.Constants.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.appstream.model.Application;
import com.cspl.commons.exception.BaseException;
import com.cspl.commons.response.GenericResponse;
import com.cspl.request.AddressDetailsRequest;
import com.cspl.request.BusinessAddressDetailsRequest;
import com.cspl.request.ContactInfoRequest;
import com.cspl.request.EmailRequest;
import com.cspl.request.GstDetailsRequest;
import com.cspl.request.MobileInfoRequest;
import com.cspl.request.PersonDetailsRequest;
import com.cspl.service.ApplicationInfoService;

import lombok.extern.slf4j.Slf4j;

/**
 * This controller will handle ApplicationInfo related requests
 */

@RestController
@RequestMapping(value = "/v1/onboarding")
@Slf4j
public class BasicOnBoardingController {

    @Autowired
    private ApplicationInfoService applicationInfoService;

    /**
     * This API will be used to save gst info with manual flow
     *
     * @param request request body to gst info
     */

    @PostMapping("gst-manual")
    public GenericResponse saveGstInfo( @RequestBody GstDetailsRequest request) throws BaseException {

        log.info("Entering into [BasicOnBoardingController -> saveGstInfo] for application number {}", request.getApplicationId());
        applicationInfoService.saveGstInfo(request);
        return new GenericResponse(OK, "info saved successfully");

    }

    /**
     * This API will be used to save gst info with manual flow
     *
     * @param request request body to save address
     */

    @PostMapping("address/{type}")
    public GenericResponse saveAddressInfo(@PathVariable @RequestBody String type, @RequestBody AddressDetailsRequest request) throws BaseException {

        log.info("Entering into [BasicOnBoardingController -> saveAddressInfo] for application number {}", request.getApplicationId());
        applicationInfoService.saveAddressInfo(type, request);
        return new GenericResponse(OK, "info saved successfully");

    }
    
    /**
     * This API will be used to save contact info 
     *
     * @param request request body to contact info
     */

    @PostMapping("contact-info")
    public GenericResponse saveContactInfo( @RequestBody ContactInfoRequest request) throws BaseException {

        log.info("Entering into [BasicOnBoardingController -> saveContactInfo] for Phone number number {}", request.getPhone());
        applicationInfoService.saveContact(request);
        return new GenericResponse(OK, "info saved successfully");

    }
    
    /**
     * This API will be used to save mobile Number 
     *
     * @param request request body to contact info
     */

    @PostMapping("mobile-info")
    public GenericResponse mobileNoInfo( @RequestBody MobileInfoRequest request) throws BaseException {

        log.info("Entering into [BasicOnBoardingController -> mobileNoInfo] for Phone number number {}", request.getPhone());
        applicationInfoService.saveMobile(request);
        return new GenericResponse(OK, "info saved successfully");

    }
    /**
     * This API will be used to save business address details
     *
     * @param request request body to save business address details
     */

    @PostMapping("/business-address-details")
    public GenericResponse saveBusinessAddressInfo(@RequestBody BusinessAddressDetailsRequest request) throws BaseException {

        log.info("Entering into [BasicOnBoardingController -> saveBusinessAddressInfo] for application number {}", request.getApplicationId());
        applicationInfoService.saveBusinessAddressInfo(request);
        return new GenericResponse(OK, "info saved successfully");

    }
    /**
     * This API will be used to save person details
     *
     * @param request request body to save person details
     */

    @PostMapping("/person-details")
    public GenericResponse savePersonInfo(@RequestBody PersonDetailsRequest request) throws BaseException {

        log.info("Entering into [BasicOnBoardingController -> savePersonInfo] for application number {}", request.getApplicationId());
        applicationInfoService.savePersonInfo(request);
        return new GenericResponse(OK, "info saved successfully");

    }
    
    
    /**
     * This API will be used to save email info 
     *
     * @param request request body to contact info
     */

    @PostMapping("email-info")
    public GenericResponse saveEmailInfo( @RequestBody EmailRequest request) throws BaseException {

        log.info("Entering into [BasicOnBoardingController -> saveEmailInfo] for email {}", request.getEmail());
        applicationInfoService.saveEmail(request);
        return new GenericResponse(OK, "info saved successfully");

    }

}
