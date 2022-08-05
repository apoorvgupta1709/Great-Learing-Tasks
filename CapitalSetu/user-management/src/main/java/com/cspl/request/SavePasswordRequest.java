package com.cspl.request;

import static com.cspl.commons.util.Constants.MOBILE_REGEX;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SavePasswordRequest {
	
	@NotBlank(message = "mobileNumber - cannot be blank.")
	@Pattern(regexp = MOBILE_REGEX, message = "mobileNumber - should follow the pattern [6-9][0-9]{9}")
	private String mobileNumber;
	
	@NotBlank(message = "password cannot be blank.")
	private String password;
	
	@NotBlank(message = "reEnteredPassword cannot be blank.")
	private String reEnteredPassword;

}
