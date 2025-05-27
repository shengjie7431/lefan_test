package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.SurveyInvestigator;
import com.lefancrm.apicenter.model.SurveyPreReimbursement;
import com.lefancrm.base.dto.ApiRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SurveyPreReimbursementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyPreReimbursement record);

    int insertSelective(SurveyPreReimbursement record);

    SurveyPreReimbursement selectByPrimaryKey(Long id);

    List<SurveyPreReimbursement> selectBySelective(ApiRequest apiRequest);

    SurveyPreReimbursement selectByUserId(@Param("userId") Long userId,@Param("preInfoDate") String preInfoDate,@Param("surveyOrgId") Long surveyOrgId);

    SurveyPreReimbursement selectMoneyBySelective(String clockIds);

    SurveyPreReimbursement selectEveryMoneyBySelective(String clockIds);

    int updateByPrimaryKeySelective(SurveyPreReimbursement record);

    int updateBySelect(@Param("id") Long id,@Param("userId") Long userId,@Param("orgId") Long orgId);

    int updateByPrimaryKey(SurveyPreReimbursement record);

    SurveyPreReimbursement selectAllCaseTotalMoneyByUserId(Long userId,Long orgId);

    List<SurveyInvestigator> selectUserClockGroupOrgId(Long userId, String preInfoDate);
}