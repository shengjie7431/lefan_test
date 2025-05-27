package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyConsignorAreaCity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyConsignorAreaCityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyConsignorAreaCity record);

    int insertSelective(SurveyConsignorAreaCity record);

    SurveyConsignorAreaCity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyConsignorAreaCity record);

    int updateByPrimaryKey(SurveyConsignorAreaCity record);

    List<SurveyConsignorAreaCity> getAreas(Map<String,Object> paramMap);

    int selectAllSelectedCount(@Param("parentIds") String parentIds, @Param("orgId") Long orgId);

    int deleteByAreaId(@Param("areaId") Long areaId,@Param("orgId") Long modelId);

    int deleteByOrgId(@Param("orgId") Long modelId);
}