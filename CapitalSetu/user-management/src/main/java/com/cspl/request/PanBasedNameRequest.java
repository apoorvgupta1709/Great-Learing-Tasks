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

public class PanBasedNameRequest {
	@NotBlank(message = "applicationId - cannot be empty")
    private String applicationId;
	
    @NotBlank(message = "panNo - cannot be empty")
    private String panNo;
    

}
