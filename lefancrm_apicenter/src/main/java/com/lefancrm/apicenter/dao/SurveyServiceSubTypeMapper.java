package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.SurveyServiceSubType;

import java.util.List;

public interface SurveyServiceSubTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyServiceSubType record);

    int insertSelective(SurveyServiceSubType record);

    SurveyServiceSubType selectByPrimaryKey(Long id);

    List<SurveyServiceSubType> selectByServiceId(Long id);

    int updateByPrimaryKeySelective(SurveyServiceSubType record);

    int updateByPrimaryKey(SurveyServiceSubType record);
}