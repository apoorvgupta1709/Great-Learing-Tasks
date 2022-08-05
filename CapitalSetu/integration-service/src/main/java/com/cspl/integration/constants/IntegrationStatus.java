package com.cspl.integration.constants;

import lombok.Getter;

/**
 * status of any integration call
 * @author Ashutosh
 */
@Getter
public enum IntegrationStatus {

    INITIATED("initiated"),
    SUCCESS("success"),
    FAILURE("failure");

    private final String status;

    IntegrationStatus(String status){
        this.status = status;
    }
}
