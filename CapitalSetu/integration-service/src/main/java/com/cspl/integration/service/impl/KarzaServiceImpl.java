package com.cspl.integration.service.impl;

import com.cspl.commons.exception.BaseException;
import com.cspl.commons.exception.RequestFailedException;
import com.cspl.commons.request.karza.PanRequest;
import com.cspl.commons.response.GstListWithGivenPan;
import com.cspl.commons.service.HttpClientUtil;
import com.cspl.commons.util.ObjectMapperUtil;
import com.cspl.integration.config.IntegrationConfig;
import com.cspl.integration.constants.IntegrationStatus;
import com.cspl.integration.dao.CommonDao;
import com.cspl.integration.dao.IntegrationDao;
import com.cspl.integration.data.models.InputRequest;
import com.cspl.integration.response.ActualGstVeriResponse;
import com.cspl.integration.data.models.IntegrationDetails;
import com.cspl.integration.data.models.Response;
import com.cspl.commons.response.GstVeriResponse;
import com.cspl.commons.request.karza.GstVerificationRequest;
import com.cspl.integration.response.ActualPanAuthenticateResponse;
import com.cspl.integration.response.ActualPanBasedGstResponse;
import com.cspl.integration.response.ActualPanResponse;
import com.cspl.commons.request.karza.PanResAuthenticate;
import com.cspl.commons.response.PanResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspl.integration.service.KarzaService;
import com.cspl.integration.service.helper.*;
import com.cspl.commons.request.karza.PanAuthenticateRequest;


import java.util.HashMap;
import java.util.Map;

import static com.cspl.commons.util.Constants.CONTENT_TYPE_JSON;
import static com.cspl.commons.util.Constants.SUCCESS;
import static com.cspl.integration.constants.CommonConstants.*;

/**
 * Karza Api related operation
 *
 */
@Service
@Slf4j
public class KarzaServiceImpl implements KarzaService {

	@Autowired
	private IntegrationDao integrationDao;

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private IntegrationConfig integrationConfig;

	@Autowired
	private IntegrationServiceHelper integrationHelper;

	/**
	 *Verify gst details and save
	 *
	 * @param request Input request
	 * @throws BaseException
	 */
	@Override
	public GstVeriResponse GstDetails(GstVerificationRequest gstRequest) throws BaseException {
		log.info("Entering into [KarzaServiceImpl -> GstgstVerification] for Gstin {} ", gstRequest.getGstin());

		String url = StringUtils.join(integrationConfig.getKarzaBaseURL(), integrationConfig.getKarzaGstVerification());
		IntegrationDetails integrationDetails = prepareGstVerificationDetailsAndSave(gstRequest, url);
		long startTime = System.currentTimeMillis();
		try {
			String response = HttpClientUtil.post(url, gstRequest, integrationHelper.prepareKarzaHeader(), CONTENT_TYPE_JSON);
			log.info("Successfully got the response for uniqueId & URL {} {}", gstRequest.getUniqueId(), url);
			integrationDetails.setApiResponseTime(System.currentTimeMillis() - startTime);
			integrationHelper.prepareRawResponseData(integrationDetails, response);
			ActualGstVeriResponse actualPanResponse = ObjectMapperUtil.extractObject(response, new TypeReference<ActualGstVeriResponse>() {
			});
			log.info("Successfully extracted the data uniqueId & URL {} {}", gstRequest.getUniqueId(), url);
			integrationDetails.setResponseData(integrationDetails.getResponseData().setParsedData(actualPanResponse));
			updateGstVerificationStatusAsSuccess(integrationDetails, actualPanResponse);
			commonDao.save(integrationDetails);
			return actualPanResponse.getResult();
		} catch (Exception ex) {
			log.error("Error occurred while getting response from remoteURL for uniqueId & URL {} {}", gstRequest.getUniqueId(), url);
			System.out.println(ex);
			integrationDetails.setApiResponseTime(System.currentTimeMillis() - startTime);
			integrationHelper.updateErrorDetailsAndSave(integrationDetails, ex);
			throw new RequestFailedException();
		}

	}

	private void updateGstVerificationStatusAsSuccess(IntegrationDetails integrationDetails, ActualGstVeriResponse panResponse) {
		log.info("Entering into [KarzaServiceImpl -> updateGstVerificationStatusAsSuccess] for uniqueId {}", integrationDetails.getUniqueId());

		if (integrationHelper.checkIfStatusCodeIsValidForSuccess(panResponse.getStatusCode(),integrationConfig.getGststatusCode())) {
			integrationDetails.setIntegrationStatus(IntegrationStatus.SUCCESS.getStatus())
			.setStatus(IntegrationStatus.SUCCESS.getStatus());
			return;
		}
		integrationDetails.setIntegrationStatus(IntegrationStatus.FAILURE.getStatus())
		.setStatus(IntegrationStatus.FAILURE.getStatus())
		.setErrorCode(""+panResponse.getStatusCode());
	}

	private IntegrationDetails prepareGstVerificationDetailsAndSave(GstVerificationRequest gstRequest, String url) {
		log.info("Entering into [KarzaServiceImpl -> prepareGstVerificationDetailsAndSave] for uniqueId {}", gstRequest.getGstin());

		IntegrationDetails integrationDetails = new IntegrationDetails()
				.setApiURL(url)
				.setUniqueId(gstRequest.getUniqueId())
				.setApiName(KARZA_GST_VERIFICATION_API)
				.setRequestData(new InputRequest(POST, integrationHelper.prepareKarzaHeader(), gstRequest))
				.setProvider(KARZA)
				.setStatus(IntegrationStatus.INITIATED.getStatus())
				.setIntegrationStatus(IntegrationStatus.INITIATED.getStatus());
		return commonDao.saveAndGet(integrationDetails, IntegrationDetails.class);
	}
	/**
	 *Fetch pan details
	 *
	 * @param request Input request
	 * @throws BaseException
	 */

	@Override
	public PanResponse getPanDetails(PanRequest panRequest) throws BaseException {
		log.info("Entering into [KarzaServiceImpl -> getPanDetails] for pan {} ", panRequest.getPan());

		String url = StringUtils.join(integrationConfig.getKarzaTestBaseURL(), integrationConfig.getKarzaPanURL());
		IntegrationDetails integrationDetails = preparePanIntegrationDetailsAndSave(panRequest, url);
		long startTime = System.currentTimeMillis();
		try {
			String response = HttpClientUtil.post(url, panRequest, integrationHelper.prepareKarzaHeader(), CONTENT_TYPE_JSON);
			log.info("Successfully got the response for uniqueId & URL {} {}", panRequest.getUniqueId(), url);

			integrationDetails.setApiResponseTime(System.currentTimeMillis() - startTime);
			integrationHelper.prepareRawResponseData(integrationDetails, response);
			ActualPanResponse actualPanResponse = ObjectMapperUtil.extractObject(response, new TypeReference<ActualPanResponse>() {
			});
			log.info("Successfully extracted the data uniqueId & URL {} {}", panRequest.getUniqueId(), url);

			integrationDetails.setResponseData(integrationDetails.getResponseData().setParsedData(actualPanResponse));
			updatePanIntegrationStatusAsSuccess(integrationDetails, actualPanResponse);
			commonDao.save(integrationDetails);

			return new PanResponse(actualPanResponse.getResult().getName());
		} catch (Exception ex) {
			log.error("Error occurred while getting response from remoteURL for uniqueId & URL {} {}", panRequest.getUniqueId(), url);

			integrationDetails.setApiResponseTime(System.currentTimeMillis() - startTime);
			integrationHelper.updateErrorDetailsAndSave(integrationDetails, ex);
			throw new RequestFailedException();
		}

	}

	private IntegrationDetails preparePanIntegrationDetailsAndSave(PanRequest panRequest, String url) {
		log.info("Entering into [KarzaServiceImpl -> prepareIntegrationDetailsAndSave] for uniqueId {}", panRequest.getUniqueId());

		IntegrationDetails integrationDetails = new IntegrationDetails()
				.setApiURL(url)
				.setUniqueId(panRequest.getUniqueId())
				.setApiName(KARZA_PAN_API_NAME)
				.setRequestData(new InputRequest(POST, integrationHelper.prepareKarzaHeader(), panRequest))
				.setProvider(KARZA)
				.setStatus(IntegrationStatus.INITIATED.getStatus())
				.setIntegrationStatus(IntegrationStatus.INITIATED.getStatus());
		return commonDao.saveAndGet(integrationDetails, IntegrationDetails.class);
	}

	private void updatePanIntegrationStatusAsSuccess(IntegrationDetails integrationDetails, ActualPanResponse panResponse) {
		log.info("Entering into [KarzaServiceImpl -> updatePanIntegrationStatusAsSuccess] for uniqueId {}", integrationDetails.getUniqueId());

		if (integrationHelper.checkIfStatusCodeIsValidForSuccess(panResponse.getStatusCode(), integrationConfig.getPanStatusCode())) {
			integrationDetails.setIntegrationStatus(IntegrationStatus.SUCCESS.getStatus())
			.setStatus(IntegrationStatus.SUCCESS.getStatus());
			return;
		}

		integrationDetails.setIntegrationStatus(IntegrationStatus.FAILURE.getStatus())
		.setStatus(IntegrationStatus.FAILURE.getStatus())
		.setErrorCode(panResponse.getStatusCode());

	}


	@Override
	public PanResAuthenticate AuthenticatePanDetails(PanAuthenticateRequest panRequest) throws BaseException {
		log.info("Entering into [KarzaServiceImpl -> AuthenticatePanDetails] for pan {} ", panRequest.getPan());

		String url = StringUtils.join(integrationConfig.getKarzaTestBaseURL(), integrationConfig.getKarzaPanAuthenticateURL());
		IntegrationDetails integrationDetails = preparePanAuthenticateIntegrationDetailsAndSave(panRequest, url);
		long startTime = System.currentTimeMillis();
		try {
			String response = HttpClientUtil.post(url, panRequest, integrationHelper.prepareKarzaHeader(), CONTENT_TYPE_JSON);
			log.info("Successfully got the response for uniqueId & URL {} {}", panRequest.getUniqueId(), url);

			integrationDetails.setApiResponseTime(System.currentTimeMillis() - startTime);
			integrationHelper.prepareRawResponseData(integrationDetails, response);
			ActualPanAuthenticateResponse actualPanResponse = ObjectMapperUtil.extractObject(response, new TypeReference<ActualPanAuthenticateResponse>() {
			});
			log.info("Successfully extracted the data uniqueId & URL {} {}", panRequest.getUniqueId(), url);

			integrationDetails.setResponseData(integrationDetails.getResponseData().setParsedData(actualPanResponse));
			updatePanAuthenticateIntegrationStatusAsSuccess(integrationDetails, actualPanResponse);
			commonDao.save(integrationDetails);
			//return new ActualPanAuthenticateResponse(actualPanResponse.getResult().getName());
			return new PanResAuthenticate(actualPanResponse.getResult().getStatus(), actualPanResponse.getResult().isDuplicate(),
					actualPanResponse.getResult().isNameMatch(), actualPanResponse.getResult().isDobMatch());
		} catch (Exception ex) {
			log.error("Error occurred while getting response from remoteURL for uniqueId & URL {} {}", panRequest.getUniqueId(), url);

			integrationDetails.setApiResponseTime(System.currentTimeMillis() - startTime);
			integrationHelper.updateErrorDetailsAndSave(integrationDetails, ex);
			throw new RequestFailedException();
		}


	}

	private IntegrationDetails preparePanAuthenticateIntegrationDetailsAndSave(PanAuthenticateRequest panRequest, String url) {
		log.info("Entering into [KarzaServiceImpl -> prepareIntegrationDetailsAndSave] for uniqueId {}", panRequest.getUniqueId());

		IntegrationDetails integrationDetails = new IntegrationDetails()
				.setApiURL(url)
				.setUniqueId(panRequest.getUniqueId())
				.setApiName(KARZA_PAN_AUTHENTICATE_API)
				.setRequestData(new InputRequest(POST, integrationHelper.prepareKarzaHeader(), panRequest))
				.setProvider(KARZA)
				.setStatus(IntegrationStatus.INITIATED.getStatus())
				.setIntegrationStatus(IntegrationStatus.INITIATED.getStatus());
		return commonDao.saveAndGet(integrationDetails, IntegrationDetails.class);
	}

	private void updatePanAuthenticateIntegrationStatusAsSuccess(IntegrationDetails integrationDetails, ActualPanAuthenticateResponse panResponse) {
		log.info("Entering into [KarzaServiceImpl -> updatePanAuthenticateIntegrationStatusAsSuccess] for uniqueId {}", integrationDetails.getUniqueId());

		if (integrationHelper.checkIfStatusCodeIsValidForSuccess(panResponse.getStatusCode(), integrationConfig.getPanStatusCode())) {
			integrationDetails.setIntegrationStatus(IntegrationStatus.SUCCESS.getStatus())
			.setStatus(IntegrationStatus.SUCCESS.getStatus());
			return;
		}

		integrationDetails.setIntegrationStatus(IntegrationStatus.FAILURE.getStatus())
		.setStatus(IntegrationStatus.FAILURE.getStatus())
		.setErrorCode(panResponse.getStatusCode());

	}

	@Override
	public GstListWithGivenPan getGstDetails(PanRequest panRequest) throws BaseException {
		log.info("Entering into [KarzaServiceImpl -> PanBasedGstDetails] for pan {} ", panRequest.getPan());

		String url = StringUtils.join(integrationConfig.getKarzaBaseURL(), integrationConfig.getKarzaPanBasedGstURL());

		IntegrationDetails integrationDetails = preparePanBasedGstIntegrationDetailsAndSave(panRequest, url);

		long startTime = System.currentTimeMillis();
		try {
			String response = HttpClientUtil.post(url, panRequest, integrationHelper.prepareKarzaHeader(), CONTENT_TYPE_JSON);
			log.info("Successfully got the response for uniqueId & URL {} {}", panRequest.getUniqueId(), url);

			integrationDetails.setApiResponseTime(System.currentTimeMillis() - startTime);
			integrationHelper.prepareRawResponseData(integrationDetails, response);
			ActualPanBasedGstResponse actualPanBasedGstResponse = ObjectMapperUtil.extractObject(response, new TypeReference<ActualPanBasedGstResponse>() {
			});
			log.info("Successfully extracted the data uniqueId & URL {} {}", panRequest.getUniqueId(), url);

			integrationDetails.setResponseData(integrationDetails.getResponseData().setParsedData(actualPanBasedGstResponse));
			updatePanBasedGstIntegrationStatusAsSuccess(integrationDetails, actualPanBasedGstResponse);
			commonDao.save(integrationDetails);

			return new GstListWithGivenPan(actualPanBasedGstResponse.getResult());

		} catch (Exception ex) {
			log.error("Error occurred while getting response from remoteURL for uniqueId & URL {} {}", panRequest.getUniqueId(), url);

			integrationDetails.setApiResponseTime(System.currentTimeMillis() - startTime);
			integrationHelper.updateErrorDetailsAndSave(integrationDetails, ex);
			throw new RequestFailedException();
		}

	}

	private IntegrationDetails preparePanBasedGstIntegrationDetailsAndSave(PanRequest panRequest, String url) {
		log.info("Entering into [KarzaServiceImpl -> prepareIntegrationDetailsAndSave] for uniqueId {}", panRequest.getUniqueId());

		IntegrationDetails integrationDetails = new IntegrationDetails()
				.setApiURL(url)
				.setUniqueId(panRequest.getUniqueId())
				.setApiName(KARZA_PAN_AUTHENTICATE_API)
				.setRequestData(new InputRequest(POST, integrationHelper.prepareKarzaHeader(), panRequest))
				.setProvider(KARZA);
		updateIntegrationDetailStatus(integrationDetails, IntegrationStatus.INITIATED);
		return commonDao.saveAndGet(integrationDetails, IntegrationDetails.class);
	}

	private void updatePanBasedGstIntegrationStatusAsSuccess(IntegrationDetails integrationDetails, ActualPanBasedGstResponse panResponse) {
		log.info("Entering into [KarzaServiceImpl -> updatePanAuthenticateIntegrationStatusAsSuccess] for uniqueId {}", integrationDetails.getUniqueId());

		if (integrationHelper.checkIfStatusCodeIsValidForSuccess(panResponse.getStatusCode(), integrationConfig.getPanStatusCode())) {
			updateIntegrationDetailStatus(integrationDetails, IntegrationStatus.SUCCESS);
			return;
		}

		updateIntegrationDetailStatus(integrationDetails, IntegrationStatus.FAILURE);
		integrationDetails.setErrorCode(panResponse.getStatusCode());


	}

	private void updateIntegrationDetailStatus(IntegrationDetails integrationDetails, IntegrationStatus status) {
		integrationDetails.setIntegrationStatus(status.getStatus())
		.setStatus(status.getStatus());

	}
}
