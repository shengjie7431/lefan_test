package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyKnowledgeBase;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyKnowledgeBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyKnowledgeBase record);

    int insertSelective(SurveyKnowledgeBase record);

    SurveyKnowledgeBase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyKnowledgeBase record);

    int updateByPrimaryKey(SurveyKnowledgeBase record);

    //数据
    List<SurveyKnowledgeBase> list(Map map);
    int listSize(Map map);

}