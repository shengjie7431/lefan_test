package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyTaskDirectionResult;

import java.util.List;
import java.util.Map;

public interface SurveyTaskDirectionResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyTaskDirectionResult record);

    int insertSelective(SurveyTaskDirectionResult record);

    SurveyTaskDirectionResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyTaskDirectionResult record);

    int updateByPrimaryKey(SurveyTaskDirectionResult record);

    //数据
    List<SurveyTaskDirectionResult> list(Map map);
    int listSize(Map map);

    SurveyTaskDirectionResult selectOneByInfo(Map map);
}