package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyFollowFileDto;
import com.lefancrm.apicenter.model.SurveyFollowFile;

import java.util.List;

public interface SurveyFollowFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyFollowFile record);

    int insertSelective(SurveyFollowFile record);

    SurveyFollowFileDto selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyFollowFile record);

    int updateByPrimaryKey(SurveyFollowFile record);

    List<SurveyFollowFileDto> getSurveyFollowFilesByFollowId(Long followId);
}