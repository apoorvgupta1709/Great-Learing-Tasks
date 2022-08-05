package com.cspl.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProprietorVerifyRequest {

	@NotBlank(message = "applicationId - cannot be empty")
	private String applicationId;

	@NotBlank(message = "consent - cannot be empty")
	private String consent;
	
	@NotBlank(message = "pan - cannot be empty")
	private String pan;

	@NotBlank(message = "name cannot be blank.")
	private String name;

	@NotBlank(message = "dob cannot be blank.")
	private String dob;

}
