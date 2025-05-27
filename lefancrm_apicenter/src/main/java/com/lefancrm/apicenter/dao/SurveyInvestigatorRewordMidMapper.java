package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.SurveyInvestigatorRewordMid;

import java.util.List;
import java.util.Map;

public interface SurveyInvestigatorRewordMidMapper {
    int insert(SurveyInvestigatorRewordMid record);

    int insertSelective(SurveyInvestigatorRewordMid record);

    /**
     * 根据清单id删除
     * @param rewardListId
     * @return
     */
    Integer updateDeleteFlag(Long rewardListId);

    /**
     * 根据条件查询数据
     * @param map
     * @return
     */
    SurveyInvestigatorRewordMid selectByOne(Map map);

    /**
     * 根据实体类修改数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(SurveyInvestigatorRewordMid record);

    /**
     * 根据条件查询数据
     * @param map
     * @return
     */
    List<SurveyInvestigatorRewordMid> selectByMap(Map map);
}