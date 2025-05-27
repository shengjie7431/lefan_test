package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyAttrUpdRecord;

import java.util.List;
import java.util.Map;

public interface SurveyAttrUpdRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyAttrUpdRecord record);

    int insertSelective(SurveyAttrUpdRecord record);

    SurveyAttrUpdRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyAttrUpdRecord record);

    int updateByPrimaryKey(SurveyAttrUpdRecord record);

    List<SurveyAttrUpdRecord> list(Map<String,Object> paramMap);
}