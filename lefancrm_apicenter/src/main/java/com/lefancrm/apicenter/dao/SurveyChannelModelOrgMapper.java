package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyChannelModelOrg;

import java.util.List;
import java.util.Map;

public interface SurveyChannelModelOrgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyChannelModelOrg record);

    int insertSelective(SurveyChannelModelOrg record);

    SurveyChannelModelOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyChannelModelOrg record);

    int updateByPrimaryKey(SurveyChannelModelOrg record);

    //数据
    List<SurveyChannelModelOrg> list(Map map);

    int listSize(Map map);

    int deleteByModelId(Long efficiencyModelId);

    SurveyChannelModelOrg selectOne(Long orgId);
}