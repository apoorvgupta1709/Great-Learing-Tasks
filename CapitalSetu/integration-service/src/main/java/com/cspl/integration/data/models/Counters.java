package com.cspl.integration.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "counters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Counters extends AuditEntity {

	private static final long serialVersionUID = 1L;
	private String name;
	private Long seqId = 1L;
}