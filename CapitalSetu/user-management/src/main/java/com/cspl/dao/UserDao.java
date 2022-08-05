package com.cspl.dao;

import com.cspl.models.User;

import java.util.Map;

/**
 *
 */
public interface UserDao {

    /**
     * This method will return a temporary user details by mobile
     *
     * @param mobileNumber mobile number of user
     */
    User findUserByMobileNumber(String mobileNumber);

    /**
     * This method will update user
     *
     * @param userId     user identifier
     * @param updateData update parameter
     */
    Boolean updateUser(String userId, Map<String, Object> updateData);

}
