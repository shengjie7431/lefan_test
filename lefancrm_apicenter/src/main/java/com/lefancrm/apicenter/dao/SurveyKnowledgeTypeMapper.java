package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyKnowledgeType;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyKnowledgeTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyKnowledgeType record);

    int insertSelective(SurveyKnowledgeType record);

    SurveyKnowledgeType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyKnowledgeType record);

    int updateByPrimaryKey(SurveyKnowledgeType record);

    //数据
    List<SurveyKnowledgeType> list(Map map);
    int listSize(Map map);

    //唯一置顶：修改其余所有数据为非置顶
    int updateOther(SurveyKnowledgeType record);
}