package com.cspl.integration.service;

import com.cspl.commons.exception.BaseException;
import com.cspl.commons.request.karza.PanAuthenticateRequest;
import com.cspl.commons.request.karza.PanRequest;
import com.cspl.commons.request.karza.PanResAuthenticate;
import com.cspl.commons.response.PanResponse;
import com.cspl.commons.response.GstListWithGivenPan;
import com.cspl.commons.request.karza.GstVerificationRequest;
import com.cspl.commons.response.GstVeriResponse;


public interface KarzaService {

	PanResponse getPanDetails(PanRequest panRequest) throws BaseException;

	PanResAuthenticate AuthenticatePanDetails(PanAuthenticateRequest panRequest) throws BaseException;

	/**
	 * This method will show gst list
	 */
	GstListWithGivenPan getGstDetails(PanRequest panRequest) throws BaseException;

	/**
	 *Verify gst details and save
	 */
	GstVeriResponse GstDetails(GstVerificationRequest gstRequest) throws BaseException;

}
