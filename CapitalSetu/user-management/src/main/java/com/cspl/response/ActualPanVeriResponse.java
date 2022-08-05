package com.cspl.response;

import com.cspl.commons.request.karza.PanResAuthenticate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActualPanVeriResponse {

	@JsonProperty("code")
	private String codeID;

	private PanResAuthenticate data;

}
