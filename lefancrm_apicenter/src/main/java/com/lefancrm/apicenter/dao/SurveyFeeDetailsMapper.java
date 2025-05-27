package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyFeeDetails;

import java.util.List;
import java.util.Map;

public interface SurveyFeeDetailsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyFeeDetails record);

    int insertSelective(SurveyFeeDetails record);

    SurveyFeeDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyFeeDetails record);

    int updateByPrimaryKey(SurveyFeeDetails record);

    List<SurveyFeeDetails> list(Map map);

    int listSize(Map map);

    List<SurveyFeeDetails> getSurveyFeeDetailsByDirectionId(Long directionId);

    SurveyFeeDetails getSurveyFeeDetailsByDirectionIdOrType(Map map);
}