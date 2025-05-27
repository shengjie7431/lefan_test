package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSurveyConsignorEfficiencyModel;

import java.util.List;
import java.util.Map;

public interface FinaSurveyConsignorEfficiencyModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSurveyConsignorEfficiencyModel record);

    int insertSelective(FinaSurveyConsignorEfficiencyModel record);

    FinaSurveyConsignorEfficiencyModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSurveyConsignorEfficiencyModel record);

    int updateByPrimaryKey(FinaSurveyConsignorEfficiencyModel record);

    //数据
    List<FinaSurveyConsignorEfficiencyModel> list(Map map);
    int listSize(Map map);
}