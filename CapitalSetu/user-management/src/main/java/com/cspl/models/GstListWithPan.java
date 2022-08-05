package com.cspl.models;

import com.cspl.commons.request.karza.PanResAuthenticate;
import com.cspl.commons.response.GstListWithGivenPan;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GstListWithPan {

	@JsonProperty("code")
	private String codeID;

	private GstListWithGivenPan data;


}
