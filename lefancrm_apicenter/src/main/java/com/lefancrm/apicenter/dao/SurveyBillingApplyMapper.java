package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyBillingApply;

import java.util.List;
import java.util.Map;

public interface SurveyBillingApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyBillingApply record);

    int insertSelective(SurveyBillingApply record);

    SurveyBillingApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyBillingApply record);

    int updateByPrimaryKey(SurveyBillingApply record);

    List<SurveyBillingApply> selectByInfo(Map<String,Object> map);

    int insertItems(Map<String,Object> paramMap);
}