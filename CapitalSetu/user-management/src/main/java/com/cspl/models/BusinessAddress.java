package com.cspl.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessAddress {
    private String businessName;
    private String address;
    private String landmark;
    private String emailId;
    
    public BusinessAddress setBusinessName(String businessName) {
        this.businessName = businessName;
        return this;
    }

    public BusinessAddress setAddress(String address) {
        this.address = address;
        return this;
    }

    public BusinessAddress setLandmark(String landmark) {
        this.landmark = landmark;
        return this;
    }

    public BusinessAddress setEmailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

}
