package com.cspl.models;

import com.cspl.commons.request.karza.PanRequest;
import com.cspl.commons.response.GstListWithGivenPan;
import com.cspl.commons.response.PanResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class PanBasedName {
	@JsonProperty("code")
	private String codeID;

	private PanResponse data;

}
