package com.cspl.commons.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GstInfo {
	String authStatus;
	String applicationStatus;
	String emailId;
	String gstinId;
	String gstinRefId;
	String mobNum;
	String pan;
	String regType;
	String registrationName;
	String tinNumber;

}
