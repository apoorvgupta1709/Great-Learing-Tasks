package com.cspl.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cspl.dao.OtpDao;
import com.cspl.models.Otp;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class OtpDaoImpl extends CommonDao implements OtpDao {

    @Override
    public Otp findOtpByMobileNumber(String mobileNumber) {
        log.info("Entering into [OtpDaoImpl → findOtpByMobileNumber] {}", mobileNumber);
        return mongoTemplate.findOne(new Query(Criteria.where("mobileNumber").is(mobileNumber)), Otp.class);
    }

    @Override
    public Boolean updateOtp(String mobileNumber, Map<String, Object> params) {

        log.info("Entering into [OtpDaoImpl → updateOtp] {}", mobileNumber);
        Map<String, Object> query = new HashMap<>();
        query.put("mobileNumber", mobileNumber);
        Map<String, Object> data = new HashMap<>();

        for (Entry<String, Object> entry : params.entrySet()) {
            data.put(entry.getKey(), entry.getValue());
        }

        Otp otp = update(query, data, Otp.class);
        return null != otp;
    }
}
