package com.cspl.models;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "counters")
@Getter
@Setter
public class Counters extends AuditEntity {

    private static final long serialVersionUID = 1L;
    private String name;
    private Long seqId = 1L;

}