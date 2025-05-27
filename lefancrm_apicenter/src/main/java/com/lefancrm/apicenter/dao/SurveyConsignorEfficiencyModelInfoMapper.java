package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyConsignorEfficiencyModelInfo;

import java.util.List;
import java.util.Map;

public interface SurveyConsignorEfficiencyModelInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyConsignorEfficiencyModelInfo record);

    int insertSelective(SurveyConsignorEfficiencyModelInfo record);

    SurveyConsignorEfficiencyModelInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyConsignorEfficiencyModelInfo record);

    int updateByPrimaryKey(SurveyConsignorEfficiencyModelInfo record);

    //数据
    List<SurveyConsignorEfficiencyModelInfo> list(Map map);
    int listSize(Map map);

    SurveyConsignorEfficiencyModelInfo selectOne(Map map);

    //根据模板id，删除名下价格
    int deleteByModelId(Long efficiencyModelId);

    //获取单个模板下，同一业务类型，最长的时效
    int selectMaxDay(Map map);

    Integer getZaAgingDay(Integer subServiceId,Integer serviceId,Integer cityType,Integer entrustOrgId);

    Integer getAgingDay(Map<String,Object> paramMap);

    Long selectSurveyDaysByMap(Map<String,Object> paramMap);
}