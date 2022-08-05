package com.cspl.models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "permissions")
public class Permission extends AuditEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String permissionName;
	private List<String> permissions;
}
