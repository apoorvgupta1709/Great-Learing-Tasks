package com.cspl.response;

import com.cspl.commons.request.karza.PanResAuthenticate;
import com.cspl.models.EntityDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ActualEntityResponse {
	
	private String businessName;
	private String businessType;
	private String userName;
	private String pan;
	
}
