package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyAssignOrgType;

import java.util.List;
import java.util.Map;

public interface SurveyAssignOrgTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyAssignOrgType record);

    int insertSelective(SurveyAssignOrgType record);

    SurveyAssignOrgType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyAssignOrgType record);

    int updateByPrimaryKey(SurveyAssignOrgType record);

    List<SurveyAssignOrgType> list(Map map);

}