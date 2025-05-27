package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSurveyConsignorEfficiencyModelInfo;

import java.util.List;
import java.util.Map;

public interface FinaSurveyConsignorEfficiencyModelInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSurveyConsignorEfficiencyModelInfo record);

    int insertSelective(FinaSurveyConsignorEfficiencyModelInfo record);

    FinaSurveyConsignorEfficiencyModelInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSurveyConsignorEfficiencyModelInfo record);

    int updateByPrimaryKey(FinaSurveyConsignorEfficiencyModelInfo record);

    //数据
    List<FinaSurveyConsignorEfficiencyModelInfo> list(Map map);
    int listSize(Map map);

    FinaSurveyConsignorEfficiencyModelInfo selectOne(Map map);

    //根据模板id，删除名下价格
    int deleteByModelId(Long efficiencyModelId);

    //获取单个模板下，同一业务类型，最长的时效
    int selectMaxDay(Map map);

    Integer getZaAgingDay(Integer subServiceId,Integer serviceId,Integer cityType,Integer entrustOrgId);

    Integer getAgingDay(Map<String,Object> paramMap);

    Integer getDays(Long entrustOrgId, Long taskId, Long districtId);
}