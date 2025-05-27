package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.SurveyAccountLog;

import java.util.List;
import java.util.Map;

/**
 * 申请结算价格调整表接口层
 * @author EDZ
 */
public interface SurveyAccountLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyAccountLog record);

    int insertSelective(SurveyAccountLog record);

    SurveyAccountLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyAccountLog record);

    int updateByPrimaryKey(SurveyAccountLog record);

    List<SurveyAccountLog> selectByList(Map map);
}