package com.cspl.constants;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * Common constants across user module
 */
public interface Constants {

    @Getter
    enum UserStatus {
        ACTIVE("active"), INACTIVE("inactive"), BLOCKED("blocked"), DELETED("deleted");

        private final String status;

        UserStatus(String name) {
            this.status = name;
        }
    }

    String USER_COLLECTION_NAME = "user";

    @Getter
    enum AddressType {
        CURRENT("current"), BUSINESS("business"), PERMANENT("permanent");

        private final String addressType;

        AddressType(String name) {
            this.addressType = name;
        }

        public static Boolean isValidAddressType(String addressType) {
            if (StringUtils.isEmpty(addressType)) {
                return false;
            }
            for (AddressType ele : AddressType.values()) {
                if (StringUtils.equalsIgnoreCase(ele.getAddressType(), addressType)) {
                    return true;
                }

            }
            return false;
        }


    }

}
