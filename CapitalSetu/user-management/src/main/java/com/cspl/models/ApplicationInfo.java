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
@Document(collection = "application_info")
public class ApplicationInfo extends AuditEntity {

    private String userId;
    private String mobileNumber;
    private GstInfo gstInfo;
    private AddressInfo addressInfo;
    private BusinessAddress businessAddress;
    private PersonInfo personInfo;
    private SignUpInfo signUpInfo;

}
