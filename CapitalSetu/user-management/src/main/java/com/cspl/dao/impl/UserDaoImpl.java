package com.cspl.dao.impl;

import com.cspl.dao.UserDao;
import com.cspl.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class UserDaoImpl extends CommonDao implements UserDao {

    @Override
    public User findUserByMobileNumber(String mobileNumber) {
        log.info("Entering into [UserDaoImpl → findUserByMobileNumber] {}", mobileNumber);
        return mongoTemplate.findOne(new Query(Criteria.where("mobileNumber").is(mobileNumber)), User.class);
    }

    @Override
    public Boolean updateUser(String userId, Map<String, Object> params) {
        log.info("Entering into [UserDaoImpl → updateUser] {}", userId);
        Map<String, Object> query = new HashMap<>();
        query.put("userId", userId);
        Map<String, Object> data = new HashMap<>();

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            data.put(entry.getKey(), entry.getValue());
        }
        User user = update(query, data, User.class);
        return null != user;
    }
}
