package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyReimbursementInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyReimbursementInfoMapper {
    int deleteByPrimaryKey(Long id);

    int deleteBySurveyDirectionId(Long id);

    int insert(SurveyReimbursementInfo record);

    int insertSelective(SurveyReimbursementInfo record);

    SurveyReimbursementInfo selectByPrimaryKey(Long id);

    SurveyReimbursementInfo selectBySurveyDirectionId(Long id);

    int updateByPrimaryKeySelective(SurveyReimbursementInfo record);

    int updateByPrimaryKey(SurveyReimbursementInfo record);

    int updateBySurveyDirectionId(SurveyReimbursementInfo record);

    List<SurveyReimbursementInfo> selectSurveyReimbursementInfoBySurveyInfoId(Long surveyInfoId);

    String selectSurveyReimbursementInfoTotalMoneyBySurveyInfoId(@Param("surveyInfoId") Long surveyInfoId,@Param("investigatorCaseId")Long investigatorCaseId,@Param("currentUserId")  Long currentUserId);

    String selectSurveyReimbursementInfoOneMoneyByDirectionId(Long dicectionId);

    Map<String, Double> selectAllMoneyByInfoIds(Map paramMap);
}