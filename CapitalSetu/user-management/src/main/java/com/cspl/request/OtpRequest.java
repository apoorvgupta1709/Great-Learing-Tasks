package com.cspl.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.cspl.commons.util.Constants.MOBILE_REGEX;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtpRequest {

    @NotBlank(message = "mobileNumber - cannot be empty")
    @Pattern(regexp = MOBILE_REGEX, message = "mobileNumber - should follow the pattern [6-9][0-9]{9}")
    private String mobileNumber;

}
