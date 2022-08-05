package com.cspl.integration.response;

import com.cspl.commons.request.karza.PanResAuthenticate;
import com.cspl.commons.response.GstVeriResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActualGstVeriResponse {
	@JsonProperty("requestId")
	private String requestId;


	private GstVeriResponse result;

	@JsonProperty("statusCode")
	private int statusCode;


}
