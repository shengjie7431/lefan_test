package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyKnowledgeComment;

import java.util.List;

public interface SurveyKnowledgeCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyKnowledgeComment record);

    int insertSelective(SurveyKnowledgeComment record);

    SurveyKnowledgeComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyKnowledgeComment record);

    int updateByPrimaryKey(SurveyKnowledgeComment record);

    List<SurveyKnowledgeComment> selectListByBaseId(Long id);
}