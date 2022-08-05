package com.cspl.integration.constants;

/**
 * Constants used across Integration Service
 * @author Ashutosh
 */
public class CommonConstants {

    private CommonConstants(){

    }

    // Request Falied with unknown reason
    public static final String CODE = "9999";
    public static final String ERROR_MESSAGE = "Unknown";


    // type of request
    public static final String POST = "post";
    public static final String GET = "get";
    public static final String FORM_DATA = "form-data";



    //api headers
    public static final String KARZA_HEADER_KEY = "x-karza-key";


    //api names
    public static final String KARZA_PAN_API_NAME = "panDetails";
    public static final String KARZA_PAN_AUTHENTICATE_API = "panAuthenticate";
    public static final String KARZA_GST_VERIFICATION_API = "GstVerification";


    //providers
    public static final String KARZA = "karza";


}
