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

public class BusinessAddressDetailsRequest {
	 @NotBlank(message = "applicationId - cannot be empty")
	    private String applicationId;
	    @NotBlank(message = "businessName - cannot be empty")
	    private String businessName;
	    @NotBlank(message = "address - cannot be empty")
	    private String address;
	    @NotBlank(message = "landmark - cannot be empty")
	    private String landmark;
	    private String emailId;
	    
}
