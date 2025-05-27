package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyRiskCaseTransfer;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyRiskCaseTransferMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyRiskCaseTransfer record);

    int insertSelective(SurveyRiskCaseTransfer record);

    SurveyRiskCaseTransfer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyRiskCaseTransfer record);

    int updateByPrimaryKey(SurveyRiskCaseTransfer record);

    List<SurveyRiskCaseTransfer> list(Map map);

    SurveyRiskCaseTransfer selectBySurveyId(Long surveyId);
}