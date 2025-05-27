package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffPaySlipManager;

import java.util.List;
import java.util.Map;

public interface StaffPaySlipManagerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffPaySlipManager record);

    int insertSelective(StaffPaySlipManager record);

    StaffPaySlipManager selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffPaySlipManager record);

    int updateByPrimaryKey(StaffPaySlipManager record);

    int generateOrganManager(Map<String,Object> map);
    int generateSuperiorManager(Map<String,Object> map);

    List<StaffPaySlipManager> list(Map map);

    StaffPaySlipManager selectByOne(Map map);
}