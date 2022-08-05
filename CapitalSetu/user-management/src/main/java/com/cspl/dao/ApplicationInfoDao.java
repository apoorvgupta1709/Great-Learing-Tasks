package com.cspl.dao;

import java.util.Map;

import com.cspl.models.ApplicationInfo;

public interface ApplicationInfoDao {


    Boolean updateApplicationInfo(String applicationId, Map<String, Object> updateData);

    ApplicationInfo getApplicationInfoById(String applicationId);

}
