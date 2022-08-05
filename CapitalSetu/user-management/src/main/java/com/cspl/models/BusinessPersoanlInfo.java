package com.cspl.models;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessPersoanlInfo {
	private String name;
	private String dob;
	private String businessType;
	private String pinCode;

}
