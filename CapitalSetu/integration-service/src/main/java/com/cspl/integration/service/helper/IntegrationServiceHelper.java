package com.cspl.integration.service.helper;

import static com.cspl.integration.constants.CommonConstants.CODE;
import static com.cspl.integration.constants.CommonConstants.ERROR_MESSAGE;
import static com.cspl.integration.constants.CommonConstants.KARZA_HEADER_KEY;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspl.integration.config.IntegrationConfig;
import com.cspl.integration.constants.IntegrationStatus;
import com.cspl.integration.dao.CommonDao;
import com.cspl.integration.data.models.IntegrationDetails;
import com.cspl.integration.data.models.Response;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IntegrationServiceHelper {

	@Autowired
	private IntegrationConfig integrationConfig;

	@Autowired
	private CommonDao commonDao;

	public boolean checkIfStatusCodeIsValidForSuccess(String statusCode,String apiSuccessCode) {
		return StringUtils.equalsIgnoreCase(apiSuccessCode,statusCode);
	}


	public boolean checkIfStatusCodeIsValidForSuccess(int statusCode,int apiSuccessCode) {
		return statusCode==apiSuccessCode;
	}

	/**
	 * This method will create raw data from response
	 *
	 * @param IntegrationDetails,response
	 * @return
	 */
	public void prepareRawResponseData(IntegrationDetails integrationDetails, String response) {
		log.info("Entering into [IntegrationServiceHelper -> prepareRawResponseData] for uniqueId {}", integrationDetails.getUniqueId());

		Response resp = integrationDetails.getResponseData() == null ? new Response()
				: integrationDetails.getResponseData();
		resp.setRawData(response);
		integrationDetails.setResponseData(resp);
	}

	/**
	 * This method will create header for Api
	 *
	 * @return
	 */
	public Map<String, String> prepareKarzaHeader() {
		Map<String, String> headers = new HashMap<>();
		headers.put(KARZA_HEADER_KEY, integrationConfig.getKarzaHeaderValue());
		return headers;
	}

	/**
	 * This method will update error details
	 *
	 * @param IntegrationDetails,Exception
	 * @return
	 */
	public void updateErrorDetailsAndSave(IntegrationDetails integrationDetails, Exception ex) {
		log.info("Entering into [IntegrationServiceHelper -> updateErrorDetailsAndSave] for uniqueId {}", integrationDetails.getUniqueId());

		integrationDetails.setStatus(IntegrationStatus.FAILURE.getStatus());
		integrationDetails.setIntegrationStatus(IntegrationStatus.FAILURE.getStatus());
		if (ex instanceof HttpResponseException) {
			int statusCode = ((HttpResponseException) ex).getStatusCode();
			String reason = ((HttpResponseException) ex).getReasonPhrase();
			integrationDetails.setErrorCode(String.valueOf(statusCode));
			integrationDetails.setErrorMessage(reason);
			commonDao.save(integrationDetails);
			return;
		}
		// Unknown error
		integrationDetails.setErrorCode(CODE)
		.setErrorMessage(ERROR_MESSAGE);

		commonDao.save(integrationDetails);
	}



}
