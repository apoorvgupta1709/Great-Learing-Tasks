package com.cspl.models;

import org.springframework.data.mongodb.core.mapping.Document;

import com.cspl.request.ContactInfoRequest;
import com.cspl.request.MobileInfoRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "mobile_info")
public class MobileInfo  extends AuditEntity {
	
	private MobileInfoRequest infoRequest; 

}
