package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyTaskReport;

import java.util.List;
import java.util.Map;

public interface SurveyTaskReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyTaskReport record);

    int insertSelective(SurveyTaskReport record);

    SurveyTaskReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyTaskReport record);

    int updateByPrimaryKey(SurveyTaskReport record);

    List<SurveyTaskReport> selectAll(Map map);
    List<SurveyTaskReport> selectAllNew(Map map);
    List<SurveyTaskReport> selectAllByParentId(Map map);
    List<SurveyTaskReport> selectAllNewByParentId(Map map);

    List<SurveyTaskReport> selectOrg(Map map);
    List<SurveyTaskReport> selectOrgParentId(Map map);

    List<SurveyTaskReport> selectUser(Map map);
    List<SurveyTaskReport> selectUserParentId(Map map);

}