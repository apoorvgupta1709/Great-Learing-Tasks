package com.cspl.contoller;

import static com.cspl.commons.util.Constants.OK;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.cspl.request.SignUpRequest;
import com.cspl.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cspl.commons.exception.BaseException;
import com.cspl.commons.request.karza.GstVerificationRequest;
import com.cspl.commons.request.karza.PanAuthenticateRequest;
import com.cspl.commons.request.karza.PanRequest;
import com.cspl.commons.response.GenericResponse;
import com.cspl.request.BusinessPerRequest;
import com.cspl.request.LoginRequest;
import com.cspl.request.OtpRequest;
import com.cspl.request.ProprietorVerifyRequest;
import com.cspl.request.ProprietorshipRequest;
import com.cspl.request.SavePasswordRequest;
import com.cspl.request.TriggeredOtpRequest;
import com.cspl.service.ApplicationInfoService;
import com.cspl.service.KarzaApiService;
import com.cspl.service.OtpService;
import com.cspl.service.PasswordService;

import lombok.extern.slf4j.Slf4j;

/**
 * This controller will handle otp related requests
 *
 * @author Ashutosh
 */
@RestController
@RequestMapping(value = "/v1/otp")
@Slf4j
public class UserVerificationController {

	@Autowired
	private OtpService otpService;

	@Autowired
	private PasswordService passwordService;

	@Autowired
	private ApplicationInfoService applicationInfoService;

	@Autowired
	private SignUpService signUpService;

	@Autowired
	private KarzaApiService karzaService;

	/**
	 * This API will trigger otp
	 *
	 * @param request request body to trigger otp
	 */
	@PostMapping("trigger")
	public GenericResponse triggerOtp(@Valid @RequestBody OtpRequest request) throws BaseException {

		log.info("Entering into [UserVerificationController → triggerOtp] for mobileNumber {}", request.getMobileNumber());
		otpService.triggerOtp(request);
		return new GenericResponse(OK, "Triggered otp successfully");
	}

	/**
	 * This API will validate mobile otp
	 *
	 * @param request request body to validate otp
	 */
	@PostMapping("validate")
	public GenericResponse validateOtp(@Valid @RequestBody TriggeredOtpRequest request, HttpServletRequest servletRequest) throws BaseException {

		log.info("Entering into [UserVerificationController → validateOtp] for mobileNumber {}", request.getMobileNumber());
		return new GenericResponse(otpService.validateOtp(request, servletRequest));
	}
	/**
	 * This API will update the password
	 *
	 * @param request request body to update password
	 */

	@PostMapping("update-password")
	public GenericResponse updatePassword(@Valid @RequestBody SavePasswordRequest request) throws BaseException {
		log.info("Entering into [UserVerificationController -> updatePassword] For Mobile Number {}", request.getMobileNumber());
		passwordService.savePassword(request);
		return new GenericResponse(OK, "Password saved successfully");
	}
	/**
	 * This API will save sign-up details
	 *
	 * @param request request body to save sign-up details
	 */

	@PostMapping("sign-up")
	public GenericResponse signUp(@Valid @RequestBody SignUpRequest request, HttpServletRequest servletRequest) throws BaseException {
		log.info("Entering into [UserVerificationController → signUp] for mobileNumber {}", request.getMobileNumber());
		return new GenericResponse(signUpService.signUp(request, servletRequest));
	}


	/**
	 * This API will save Proprietorship details
	 *
	 * @param request request body to ProprietorshipRequest info
	 */
	@PostMapping("ProprietorshipDetails")
	public GenericResponse saveProprietorshipDetails(@Valid @RequestBody ProprietorshipRequest request) throws BaseException {
		log.info("Entering into [UserVerificationController -> saveProprietorshipDetails] For application id {}", request.getApplicationId());
		applicationInfoService.saveProprietorshipInfo(request);
		return new GenericResponse(OK, "Proprietorship Details saved successfully");
	}

	/**
	 * This API will call Karza Api to verify Proprietorship details
	 *
	 * @param request request body to ProprietorshipRequest info
	 */
	@PostMapping("proprietorship-verify-details")
	public GenericResponse verifyProprietorshipDetails(@Valid @RequestBody ProprietorVerifyRequest request) throws BaseException {
		log.info("Entering into [UserVerificationController -> verifyProprietorshipDetails] For application id {}", request.getPan());

		return new GenericResponse(karzaService.verifyPropDetails(request));
	}

	/**
	 * This API will save business personal detail
	 *
	 * @param request request body to businessPersonalRequest info
	 */
	@PostMapping("business-personal-details")
	public GenericResponse businessPersonalDetail(@Valid @RequestBody BusinessPerRequest request) throws BaseException {
		log.info("Entering into [UserVerificationController -> businessPersonalDetail] For application id {}", request.getApplicationId());
		applicationInfoService.saveBusinessPerInfo(request);
		return new GenericResponse(OK, "business Details saved successfully");
	}

	/**
	 * This API will call Karza Api to fetch all gst numbers
	 *
	 * @param request request body to panRequest info
	 */
	@PostMapping("gst-list")
	public GenericResponse getPanBasedGst(@Valid @RequestBody PanRequest request) throws BaseException {
		log.info("Entering into [UserVerificationController -> getGstList] For application id {}", request.getUniqueId());

		return new GenericResponse(karzaService.getGstList(request));
	}
	/**
	 * This API will call Karza Api to fetch pan based name
	 *
	 * @param request request body to panRequest info
	 */
	@PostMapping("pan-based-name")
	public GenericResponse getPanBasedName(@Valid @RequestBody PanRequest request) throws BaseException {
		log.info("Entering into [UserVerificationController -> getPanName] For application id {}", request.getUniqueId());

		return new GenericResponse(karzaService.getPanBasedName(request));
	}
	/**
	 * This API will call Karza Api to fetch entity details
	 *
	 * @param request request body to panRequest info
	 */
	@PostMapping("entity-details")
	public GenericResponse getEntityDetails(@Valid @RequestBody GstVerificationRequest request) throws BaseException {
		log.info("Entering into [UserVerificationController -> getEntityDetails] For application id {}", request.getUniqueId());

		return new GenericResponse(karzaService.getEntityDetails(request));
	}

	/**
	 * Login Api
	 *
	 * @param request request body to save sign-up details
	 */

	@PostMapping("login")
	public GenericResponse login(@Valid @RequestBody LoginRequest request,HttpServletRequest servletRequest) throws BaseException {
		log.info("Entering into [UserVerificationController → login] for mobileNumber {}", request.getMobileNumber());

		return new GenericResponse(signUpService.login(request,servletRequest));
	}

}
