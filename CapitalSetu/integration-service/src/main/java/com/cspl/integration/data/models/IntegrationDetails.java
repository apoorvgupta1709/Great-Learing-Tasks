package com.cspl.integration.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "integration_details")
public class IntegrationDetails extends AuditEntity {

	private static final long serialVersionUID = 1L;

	private String uniqueId;
	private String apiName;
	private String apiURL;
	private String provider;
	private String integrationStatus;
	private InputRequest requestData;
	private Response responseData;
	private long apiResponseTime;
	private String status;
	private String errorCode;
	private String errorMessage;

	public IntegrationDetails setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
		return this;
	}


	public IntegrationDetails setRequestData(InputRequest requestData) {
		this.requestData = requestData;
		return this;
	}

	public IntegrationDetails setResponseData(Response responseData) {
		this.responseData = responseData;
		return this;
	}

	public IntegrationDetails setResponseTime(Integer responseTime) {
		this.apiResponseTime = responseTime;
		return this;
	}

	public IntegrationDetails setErrorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public IntegrationDetails setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	public IntegrationDetails setApiName(String apiName) {
		this.apiName = apiName;
		return this;
	}

	public IntegrationDetails setApiURL(String apiURL) {
		this.apiURL = apiURL;
		return this;
	}

	public IntegrationDetails setProvider(String provider) {
		this.provider = provider;
		return this;
	}

	public IntegrationDetails setIntegrationStatus(String integrationStatus) {
		this.integrationStatus = integrationStatus;
		return this;
	}

	public IntegrationDetails setApiResponseTime(long apiResponseTime) {
		this.apiResponseTime = apiResponseTime;
		return this;
	}

	public IntegrationDetails setStatus(String status) {
		this.status = status;
		return this;
	}
}
