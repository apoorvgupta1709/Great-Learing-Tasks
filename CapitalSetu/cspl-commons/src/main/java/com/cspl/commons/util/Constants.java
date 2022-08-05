package com.cspl.commons.util;

/**
 * Common constants used across the application
 * @author Ashutosh
 */
public class Constants {
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String ERROR = "error";
    public static final String MOBILE_REGEX = "[6-9][0-9]{9}";
    public static final String GST_REGEX = "[0-9]{2}[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}[A-Za-z0-9]{3}";
    public static final String PIN_REGEX = "[1-9][0-9]{5}";
    public static final String AADHAR_REGEX = "[2-9]{2}[0-9]{10}";
    public static final String PAN_REGEX = "[A-Z]{5}[0-9]{4}[A-Z]{1}";

    // Http calls related constants
    public static final String HTTP_SERVICE_FAILED = "000";
    public static final String CONTENT_TYPE_JSON = "application/json";

    // Application specific code
    public static final String OK = "CSPL-200";
    public static final String INTERNAL_ERROR = "CSPL-500";
    public static final String DEPENDENCY_FAILED = "CSPL-501";
    public static final String INVALID_INPUT = "CSPL-300";

}
