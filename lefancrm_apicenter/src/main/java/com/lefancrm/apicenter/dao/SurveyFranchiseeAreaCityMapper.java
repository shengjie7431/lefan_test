package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyFranchiseeAreaCity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyFranchiseeAreaCityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyFranchiseeAreaCity record);

    int insertSelective(SurveyFranchiseeAreaCity record);

    SurveyFranchiseeAreaCity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyFranchiseeAreaCity record);

    int updateByPrimaryKey(SurveyFranchiseeAreaCity record);

    List<SurveyFranchiseeAreaCity> getAreas(Map<String,Object> paramMap);

    int selectAllSelectedCount(@Param("parentIds") String parentIds, @Param("orgId") Long orgId);

    int deleteByAreaId(@Param("areaId") Long areaId,@Param("orgId") Long modelId);

    int deleteByOrgId(@Param("orgId") Long modelId);
}