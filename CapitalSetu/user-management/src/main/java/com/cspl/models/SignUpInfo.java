package com.cspl.models;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpInfo extends AuditEntity {
	 private String userId;
	 private String mobileNumber;
	 private String businessType;
	   
}
