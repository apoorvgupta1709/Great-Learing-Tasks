package com.cspl.models;


import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "otp_info")
public class Otp extends AuditEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String mobileNumber;
    private String otp;
    private Integer retryCount = 0;
    private Long validTill;
    private Long unBlockAt;
    private Integer invalidOtpCount = 0;

    public Otp(String mobileNumber, String otp, Long validTill) {
        this.mobileNumber = mobileNumber;
        this.otp = otp;
        this.validTill = validTill;
    }
}
