package com.cspl.integration.data.models;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Version
	private Long version;
	@CreatedDate
	private DateTime createdDate;
	@LastModifiedDate
	private DateTime lastModifiedDate;
	@CreatedBy
	private String createdBy;
	@LastModifiedBy
	private String lastModifiedBy;

}