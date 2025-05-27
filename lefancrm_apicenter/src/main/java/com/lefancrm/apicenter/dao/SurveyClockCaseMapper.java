package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.SurveyClockCase;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyClockCaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyClockCase record);

    int insertSelective(SurveyClockCase record);

    SurveyClockCase selectByPrimaryKey(Long id);

    List<SurveyClockCase> selectByClocId(Long clockId);

    double selectTotalMoneyByClocId(Long clockId);

    int updateByPrimaryKeySelective(SurveyClockCase record);

    int updateByPrimaryKey(SurveyClockCase record);

    List<SurveyClockCase> selectSurveyClockCase(@Param("id") Long id, @Param("type") String type);

    double surveyInvCaseAllMoney(@Param("surveyInfoId") Long surveyInfoId,@Param("surveyUserId") Long surveyUserId);

    String selectOrgAllCase(Map paramMap);

    /**
     * 根据条件进行统计
     * @param map
     * @return
     */
    Integer selectPunchClockCount(Map map);
}