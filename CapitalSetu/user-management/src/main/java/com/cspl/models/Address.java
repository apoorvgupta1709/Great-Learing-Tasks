package com.cspl.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String line1;
    private String line2;
    private String landmark;
    private String pincode;
    private String ownership;

    public Address setLine1(String line1) {
        this.line1 = line1;
        return this;
    }

    public Address setLine2(String line2) {
        this.line2 = line2;
        return this;
    }

    public Address setLandmark(String landmark) {
        this.landmark = landmark;
        return this;
    }

    public Address setPincode(String pincode) {
        this.pincode = pincode;
        return this;
    }

    public Address setOwnership(String ownership) {
        this.ownership = ownership;
        return this;
    }


}
