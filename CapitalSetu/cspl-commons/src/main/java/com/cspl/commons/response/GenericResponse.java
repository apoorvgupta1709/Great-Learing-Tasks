package com.cspl.commons.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static com.cspl.commons.util.Constants.OK;

/**
 * Generic response object,
 * Used across the services
 * @author Ashutosh
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Slf4j
public class GenericResponse {

    private String code;
    private String message;
    private Object data;
    private List<String> errors;

    /**
     *
     * @param data Data to send back
     */
    public GenericResponse(Object data) {
        this.code = OK;
        this.data = data;
    }

    /**
     *
     * @param errorCode Error code to send back
     * @param message Message to send back
     */
    public GenericResponse(String errorCode, String message) {
        this.code = errorCode;
        this.message = message;
    }

    /**
     * Validation error response
     * @param applicationCode Identifier for application
     * @param message Message to send
     * @param errors List of error fields
     */
    public GenericResponse(String applicationCode, String message, List<String> errors) {
        this.code = applicationCode;
        this.message = message;
        this.errors = errors;
    }

    /**
     * Response with specific data
     * @param applicationCode Identifier for application
     * @param message Message to return back
     * @param data Data to return
     */
    public GenericResponse(String applicationCode, String message, Object data) {
        this.code = applicationCode;
        this.message = message;
        this.data = data;
    }

}
