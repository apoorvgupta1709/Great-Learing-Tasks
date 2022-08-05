package com.cspl.dao.impl;

import com.cspl.dao.ApplicationInfoDao;
import com.cspl.models.ApplicationInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Slf4j
public class ApplicationInfoDaoImpl extends CommonDao implements ApplicationInfoDao {

    @Override
    public Boolean updateApplicationInfo(String applicationId, Map<String, Object> updateData) {
        log.info("Entering into [ApplicationDaoImpl → updateDetails {}", applicationId);
        Map<String, Object> query = new HashMap<>();
        query.put("_id", applicationId);
        ApplicationInfo user = update(query, updateData, ApplicationInfo.class);

        return null != user;
    }


    @Override
    public ApplicationInfo getApplicationInfoById(String applicationId) {
        log.info("Entering into [ApplicationInfoDaoImpl -> getApplicationInfoById] with application id {}", applicationId);
        Map<String, Object> query = new HashMap<>();
        query.put("_id", applicationId);
        return findOne(query, ApplicationInfo.class);
    }


}
