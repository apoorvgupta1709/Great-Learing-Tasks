package com.cspl.request;

import javax.validation.constraints.NotBlank;

import org.mapstruct.Named;

import com.cspl.models.AddressInfo;
import com.cspl.models.GstInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Named("Details")
public class ContactInfoRequest {

    @NotBlank(message = "name - cannot be empty")
	private String name;
    @NotBlank(message = "email - cannot be empty")
    private String email;
    @NotBlank(message = "phone - cannot be empty")
    private String phone;
    
    private String message;
}
