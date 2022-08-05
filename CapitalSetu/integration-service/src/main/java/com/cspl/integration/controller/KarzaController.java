package com.cspl.integration.controller;

import com.cspl.commons.exception.BaseException;
import com.cspl.commons.request.karza.PanRequest;
import com.cspl.commons.response.GenericResponse;
import com.cspl.integration.service.KarzaService;
import com.cspl.commons.request.karza.PanAuthenticateRequest;
import com.cspl.commons.request.karza.GstVerificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * KARZA Related API integration
 *
 * @author Ashutosh
 */
@RestController
@RequestMapping(value = "karza/v1")
@Slf4j
public class KarzaController {

	@Autowired
	private KarzaService karzaService;

	@PostMapping("/pan")
	public GenericResponse getPanDetails(@Valid @RequestBody PanRequest panRequest) throws BaseException {
		log.info("Entering into [KarzaController -> getPanDetails] for pan {}", panRequest.getPan());
		return new GenericResponse(karzaService.getPanDetails(panRequest));
	}

	@PostMapping("/pan-authenticate")
	public GenericResponse verifyPanDetails(@Valid @RequestBody PanAuthenticateRequest panRequest) throws BaseException {
		log.info("Entering into [KarzaController -> verifyPanDetails] for pan {}", panRequest.getPan());
		return new GenericResponse(karzaService.AuthenticatePanDetails(panRequest));
	}

	/**
	 * @param panRequest request body to show gst list
	 * @throws BaseException
	 * @description This API will be used to show gst list
	 */
	@PostMapping("/gst-list")
	public GenericResponse getGstDetails(@Valid @RequestBody PanRequest panRequest) throws BaseException {
		log.info("Entering into [KarzaController -> getGstList] for pan {}", panRequest.getPan());
		return new GenericResponse(karzaService.getGstDetails(panRequest));
	}

	/**
	 * This API will verify gst info
	 *
	 * @param request request body to gst info
	 */
	@PostMapping("/Gst-Verification")
	public GenericResponse VerifyGstDetails(@Valid @RequestBody GstVerificationRequest gstRequest) throws BaseException {
		log.info("Entering into [KarzaController -> VerifyGstDetails] for pan {}", gstRequest.getGstin());
		return new GenericResponse(karzaService.GstDetails(gstRequest));
	}


}
