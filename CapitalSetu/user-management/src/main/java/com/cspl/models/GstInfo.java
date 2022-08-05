package com.cspl.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GstInfo {
    private String gstin;
    private String entityName;
    private String gstRegDate;
    private String constitution;
    private String state;
    private String gstStatus;
    private String entityPan;
    private String email;
    private String businessType;
    private String tradeName;
    private Boolean isVerified;

    public GstInfo setGstin(String gstin) {
        this.gstin = gstin;
        return this;
    }

    public GstInfo setEntityName(String entityName) {
        this.entityName = entityName;
        return this;

    }

    public GstInfo setGstRegDate(String gstRegDate) {
        this.gstRegDate = gstRegDate;
        return this;

    }

    public GstInfo setConstitution(String constitution) {
        this.constitution = constitution;
        return this;

    }

    public GstInfo setState(String state) {
        this.state = state;
        return this;

    }

    public GstInfo setGstStatus(String gstStatus) {
        this.gstStatus = gstStatus;
        return this;

    }

    public GstInfo setEntityPan(String entityPan) {
        this.entityPan = entityPan;
        return this;

    }

    public GstInfo setEmail(String email) {
        this.email = email;
        return this;

    }

    public GstInfo setBusinessType(String businessType) {
        this.businessType = businessType;
        return this;

    }

    public GstInfo setTradeName(String tradeName) {
        this.tradeName = tradeName;
        return this;

    }

    public GstInfo setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
        return this;

    }
}
