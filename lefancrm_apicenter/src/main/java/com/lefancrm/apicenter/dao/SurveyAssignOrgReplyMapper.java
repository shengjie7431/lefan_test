package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.SurveyAssignOrgReply;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface SurveyAssignOrgReplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyAssignOrgReply record);

    int insertSelective(SurveyAssignOrgReply record);

    SurveyAssignOrgReply selectByPrimaryKey(Long id);

    Map selectInvPreByOrgCaseId(@Param("id") Long id,@Param("type") Integer type);

    int updateByPrimaryKeySelective(SurveyAssignOrgReply record);

    int updateByPrimaryKey(SurveyAssignOrgReply record);

    Map selectInvReplyInfo(@Param("replyId") Integer replyId,@Param("type")Integer type);
}