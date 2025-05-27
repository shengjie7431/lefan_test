package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyAssignOrgExtension;
import com.lefancrm.apicenter.model.SurveyBackReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SurveyBackReplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyBackReply record);

    int insertSelective(SurveyBackReply record);

    SurveyBackReply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyBackReply record);

    int updateByPrimaryKey(SurveyBackReply record);

    List<SurveyBackReply> getSurveyBackReplyBySurveyInfoId(Long surveyInfoId);


    List<SurveyBackReply> listByIds(@Param("surveyInfoIds") List<Long> surveyInfoIds);
}