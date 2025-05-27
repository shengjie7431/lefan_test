package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffPerformanceManager;

import java.util.List;
import java.util.Map;

public interface StaffPerformanceManagerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffPerformanceManager record);

    int insertSelective(StaffPerformanceManager record);

    StaffPerformanceManager selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffPerformanceManager record);

    int updateByPrimaryKey(StaffPerformanceManager record);

    int generateOrganManager(Map<String,Object> map);
    int generateSuperiorManager(Map<String,Object> map);

    List<StaffPerformanceManager> list(Map map);

    StaffPerformanceManager selectByOne(Map map);
}