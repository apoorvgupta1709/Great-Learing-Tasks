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

public class PersonDetailsRequest {
	@NotBlank(message = "applicationId - cannot be empty")
    private String applicationId;
    @NotBlank(message = "name - cannot be empty")
    private String name;
    @NotBlank(message = "pan - cannot be empty")
    private String pan;
    @NotBlank(message = "dob - cannot be empty")
    private String dob;
    private String emailId;
}
