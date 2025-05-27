package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyConsignorDepartment;

import java.util.List;
import java.util.Map;

public interface SurveyConsignorDepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyConsignorDepartment record);

    int insertSelective(SurveyConsignorDepartment record);

    SurveyConsignorDepartment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyConsignorDepartment record);

    int updateByPrimaryKey(SurveyConsignorDepartment record);

    List<SurveyConsignorDepartment> list(Map map);
    int listSize(Map map);

    //根据名称查询
    SurveyConsignorDepartment selectByName(String name);
}