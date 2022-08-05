package com.cspl.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfo {
    private Address business;
    private Address permanent;
    private Address current;
    private Boolean isCurrentAddressSameAsPermanent;
    private Boolean isPermanentAddressSameAsBusiness;
}
