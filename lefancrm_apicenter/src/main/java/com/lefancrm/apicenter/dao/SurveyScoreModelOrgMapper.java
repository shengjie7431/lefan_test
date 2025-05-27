package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyScoreModelOrg;

import java.util.List;
import java.util.Map;

public interface SurveyScoreModelOrgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyScoreModelOrg record);

    int insertSelective(SurveyScoreModelOrg record);

    SurveyScoreModelOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyScoreModelOrg record);

    int updateByPrimaryKey(SurveyScoreModelOrg record);

    //数据
    List<SurveyScoreModelOrg> list(Map map);

    int listSize(Map map);

    int deleteByModelId(Long efficiencyModelId);

    SurveyScoreModelOrg selectOne(Long orgId);
}