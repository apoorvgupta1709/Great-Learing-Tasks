package com.cspl.request;

import javax.validation.constraints.NotBlank;

import org.mapstruct.Named;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MobileInfoRequest {
	 @NotBlank(message = "phone - cannot be empty")
		private String phone;

}
