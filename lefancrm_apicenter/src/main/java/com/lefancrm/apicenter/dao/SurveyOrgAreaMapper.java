package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyOrgArea;

import java.util.List;
import java.util.Map;

/**
 * 机构片区表
 * @author LSG
 */
public interface SurveyOrgAreaMapper {
    /**
     * 根据条件查询
     * @param map
     * @return
     */
    List<SurveyOrgArea> selectByList(Map map);

    /**
     * 根据条件查询一条数据
     * @param map
     * @return
     */
    SurveyOrgArea selectOne(Map map);

    /**
     * 新增一条数据
     * @param surveyOrgArea
     * @return
     */
    Integer addOne(SurveyOrgArea surveyOrgArea);

    /**
     * 修改一条数据
     * @param surveyOrgArea
     * @return
     */
    Integer updOne(SurveyOrgArea surveyOrgArea);
}
