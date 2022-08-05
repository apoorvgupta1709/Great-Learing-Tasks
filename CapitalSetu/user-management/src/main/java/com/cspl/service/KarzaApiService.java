package com.cspl.service;

import javax.validation.Valid;

import com.cspl.commons.exception.BaseException;
import com.cspl.commons.request.karza.GstVerificationRequest;
import com.cspl.commons.request.karza.PanRequest;
import com.cspl.commons.request.karza.PanResAuthenticate;
import com.cspl.commons.response.GstListWithGivenPan;
import com.cspl.commons.response.GstVeriResponse;
import com.cspl.commons.response.PanResponse;
import com.cspl.request.PanBasedNameRequest;
import com.cspl.request.ProprietorVerifyRequest;
import com.cspl.response.ActualEntityResponse;

public interface KarzaApiService {

	/**
	 * This method will verify Pan details
	 */
	PanResAuthenticate verifyPropDetails(ProprietorVerifyRequest request) throws BaseException;

	/**
	 * This method will gst list of all available gst
	 */
	GstListWithGivenPan getGstList( PanRequest request) throws BaseException;
	
	/**
	 * This method will give pan based name
	 */	
	PanResponse getPanBasedName(PanRequest request) throws BaseException;
	
	/**
	 * This method will give entity details
	 */	
	ActualEntityResponse getEntityDetails(GstVerificationRequest request) throws BaseException;
	


}
