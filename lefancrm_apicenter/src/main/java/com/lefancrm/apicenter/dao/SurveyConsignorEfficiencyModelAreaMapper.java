package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyConsignorEfficiencyModelArea;

import java.util.List;
import java.util.Map;

public interface SurveyConsignorEfficiencyModelAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyConsignorEfficiencyModelArea record);

    int insertSelective(SurveyConsignorEfficiencyModelArea record);

    SurveyConsignorEfficiencyModelArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyConsignorEfficiencyModelArea record);

    int updateByPrimaryKey(SurveyConsignorEfficiencyModelArea record);


    //数据
    List<SurveyConsignorEfficiencyModelArea> list(Map map);

    int listSize(Map map);

    List<SurveyConsignorEfficiencyModelArea> selectModelAreasByEntrustOrgId(Long entrustOrgId);


    int copyPirce(Map<String,Object> paramMap);
    int deleteByModelId(Long modelId);
}