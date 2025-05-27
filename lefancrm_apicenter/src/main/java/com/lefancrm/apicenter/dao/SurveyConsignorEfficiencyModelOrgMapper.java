package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyConsignorEfficiencyModelOrg;

import java.util.List;
import java.util.Map;

public interface SurveyConsignorEfficiencyModelOrgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyConsignorEfficiencyModelOrg record);

    int insertSelective(SurveyConsignorEfficiencyModelOrg record);

    SurveyConsignorEfficiencyModelOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyConsignorEfficiencyModelOrg record);

    int updateByPrimaryKey(SurveyConsignorEfficiencyModelOrg record);

    //数据
    List<SurveyConsignorEfficiencyModelOrg> list(Map map);
    int listSize(Map map);

    int deleteByModelId(Long efficiencyModelId);

    SurveyConsignorEfficiencyModelOrg selectOne(Long orgId);
}