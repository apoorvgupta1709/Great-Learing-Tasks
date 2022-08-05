package com.cspl.models;

import com.cspl.commons.response.GstVeriResponse;
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

public class EntityDetails {
	@JsonProperty("code")
	private String codeID;

	private GstVeriResponse data;

}
