package com.cspl.integration.response;

import com.cspl.commons.request.karza.PanResAuthenticate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActualPanAuthenticateResponse {
    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("status-code")
    private String statusCode;
    private PanResAuthenticate result;

}