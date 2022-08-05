package com.cspl.integration.response;

import java.util.List;

import com.cspl.commons.response.GstInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActualPanBasedGstResponse {
	private String requestId;
	private String statusCode;
	private List<GstInfo> result;

}
