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
public class AddressDetailsRequest {
    @NotBlank(message = "applicationId - cannot be empty")
    private String applicationId;
    @NotBlank(message = "addressLine1 - cannot be empty")
    private String addressLine1;
    @NotBlank(message = "addressLine2 - cannot be empty")
    private String addressLine2;
    @NotBlank(message = "landmark - cannot be empty")
    private String landmark;
    @NotBlank(message = "pincode - cannot be empty")
    private String pincode;
    @NotBlank(message = "ownership - cannot be empty")
    private String ownership;
    private Boolean isCurrentAddressSameAsPermanent;
    private Boolean isPermanentAddressSameAsBusiness;


}
