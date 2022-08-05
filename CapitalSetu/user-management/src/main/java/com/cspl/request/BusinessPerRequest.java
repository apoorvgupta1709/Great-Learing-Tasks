package com.cspl.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessPerRequest {

	@NotBlank(message = "applicationId - cannot be empty")
	private String applicationId;
	@NotBlank(message = "name - cannot be empty")
	private String name;
	@NotBlank(message = "dob - cannot be empty")
	private String dob;
	@NotBlank(message = "businessType - cannot be empty")
	private String businessType;
	@NotBlank(message = "pinCode - cannot be empty")
	private String pinCode;

}
