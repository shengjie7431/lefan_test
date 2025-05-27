package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSurveyConsignorEfficiencyModelOrg;

import java.util.List;
import java.util.Map;

public interface FinaSurveyConsignorEfficiencyModelOrgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSurveyConsignorEfficiencyModelOrg record);

    int insertSelective(FinaSurveyConsignorEfficiencyModelOrg record);

    FinaSurveyConsignorEfficiencyModelOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSurveyConsignorEfficiencyModelOrg record);

    int updateByPrimaryKey(FinaSurveyConsignorEfficiencyModelOrg record);

    //数据
    List<FinaSurveyConsignorEfficiencyModelOrg> list(Map map);
    int listSize(Map map);

    int deleteByModelId(Long efficiencyModelId);

    int deleteByOrgId(Long orgId);

    FinaSurveyConsignorEfficiencyModelOrg selectOne(Long orgId);
}