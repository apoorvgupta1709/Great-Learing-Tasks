package com.cspl.request;


import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GstDetailsRequest {

    @NotBlank(message = "applicationId - cannot be empty")
    private String applicationId;
    @NotBlank(message = "name - cannot be empty")
    private String name;
    @NotBlank(message = "date - cannot be empty")
    private String date;
    @NotBlank(message = "bussinessType - cannot be empty")
    private String bussinessType;
    @NotBlank(message = "email - cannot be empty")
    private String email;
}
