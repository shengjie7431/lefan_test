package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyConsignorReportRule;

public interface SurveyConsignorReportRuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyConsignorReportRule record);

    int insertSelective(SurveyConsignorReportRule record);

    SurveyConsignorReportRule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyConsignorReportRule record);

    int updateByPrimaryKey(SurveyConsignorReportRule record);

    //根据委托机构id查询
    SurveyConsignorReportRule selectByConsignorId(Long consignorId);
}