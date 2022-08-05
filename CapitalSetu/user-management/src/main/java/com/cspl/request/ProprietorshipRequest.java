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
public class ProprietorshipRequest {

	@NotBlank(message = "applicationId - cannot be empty")
	private String applicationId;

	@NotBlank(message = "dob cannot be blank.")
	private String dob;

	@NotBlank(message = "email cannot be blank.")
	private String email;

}
