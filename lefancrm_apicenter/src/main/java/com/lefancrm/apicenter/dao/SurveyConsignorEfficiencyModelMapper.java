package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyConsignorEfficiencyModel;

import java.util.List;
import java.util.Map;

public interface SurveyConsignorEfficiencyModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyConsignorEfficiencyModel record);

    int insertSelective(SurveyConsignorEfficiencyModel record);

    SurveyConsignorEfficiencyModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyConsignorEfficiencyModel record);

    int updateByPrimaryKey(SurveyConsignorEfficiencyModel record);

    //数据
    List<SurveyConsignorEfficiencyModel> list(Map map);
    int listSize(Map map);
}