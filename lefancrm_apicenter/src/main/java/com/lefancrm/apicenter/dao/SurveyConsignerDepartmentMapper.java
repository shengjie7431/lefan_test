package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyConsignerDepartment;

import java.util.List;
import java.util.Map;

public interface SurveyConsignerDepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyConsignerDepartment record);

    int insertSelective(SurveyConsignerDepartment record);

    SurveyConsignerDepartment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyConsignerDepartment record);

    int updateByPrimaryKey(SurveyConsignerDepartment record);

    SurveyConsignerDepartment selectOne(Map<String,Object> paramMap);

    int delete(Map<String,Object> paramMap);

    int deleteByUserAndDepartment(Map<String,Object> paramMap);

    List<SurveyConsignerDepartment> list(Map<String,Object> paramMap);
}