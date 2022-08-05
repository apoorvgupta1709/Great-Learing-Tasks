package com.cspl.models;

import org.springframework.data.mongodb.core.mapping.Document;

import com.cspl.request.ContactInfoRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "contact_info")
public class ContactInfo extends AuditEntity{

	private ContactInfoRequest contactInfoRequest;
}
