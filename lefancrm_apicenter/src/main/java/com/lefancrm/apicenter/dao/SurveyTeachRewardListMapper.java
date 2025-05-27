package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.SurveyTeachRewardList;

import java.util.List;
import java.util.Map;

/**
 * @author EDZ
 */
public interface SurveyTeachRewardListMapper {

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据实体类新增数据
     * @param record
     * @return
     */
    int insert(SurveyTeachRewardList record);

    /**
     * 根据实体类新增数据
     * @param record
     * @return
     */
    int insertSelective(SurveyTeachRewardList record);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    SurveyTeachRewardList selectByPrimaryKey(Long id);

    /**
     * 根据实体类修改数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SurveyTeachRewardList record);

    /**
     * 根据实体类修改数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(SurveyTeachRewardList record);

    /**
     * 根据条件查询所有数据
     * @param map
     * @return
     */
    List<SurveyTeachRewardList> selectByMap(Map map);
}