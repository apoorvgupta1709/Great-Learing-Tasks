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
public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
	private String rawData;
	private Object parsedData;
	private Map<String, Object> headers;

	public Response(String rawData, Object parsedData) {
		this.rawData = rawData;
		this.parsedData = parsedData;
	}

	public Response setRawData(String rawData) {
		this.rawData = rawData;
		return this;
	}

	public Response setParsedData(Object parsedData) {
		this.parsedData = parsedData;
		return this;
	}

	public Response setHeaders(Map<String, Object> headers) {
		this.headers = headers;
		return this;
	}
}
