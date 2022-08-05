package com.cspl.integration.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String requestType;
	private Map<String, Object> URLParams;
	private Map<String, Object> queryParams;
	private Object body;
	private Map<String, String> headers;

	public InputRequest(String requestType, Map<String, String> headerData, Object body) {
		this.requestType = requestType;
		this.headers = headerData;
		this.body = body;
	}

}
