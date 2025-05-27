package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSurveyConsignorEfficiencyModelArea;

import java.util.List;
import java.util.Map;

public interface FinaSurveyConsignorEfficiencyModelAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSurveyConsignorEfficiencyModelArea record);

    int insertSelective(FinaSurveyConsignorEfficiencyModelArea record);

    FinaSurveyConsignorEfficiencyModelArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSurveyConsignorEfficiencyModelArea record);

    int updateByPrimaryKey(FinaSurveyConsignorEfficiencyModelArea record);


    //数据
    List<FinaSurveyConsignorEfficiencyModelArea> list(Map map);

    int listSize(Map map);

    List<FinaSurveyConsignorEfficiencyModelArea> selectModelAreasByEntrustOrgId(Long entrustOrgId);


    int copyPirce(Map<String,Object> paramMap);
    int deleteByModelId(Long modelId);
}