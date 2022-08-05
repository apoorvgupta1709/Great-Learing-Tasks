package com.cspl.models;

import org.springframework.data.mongodb.core.mapping.Document;

import com.cspl.request.EmailRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "email_info")
public class EmailInfo extends AuditEntity{
	
	private EmailRequest email; 


}
