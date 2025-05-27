package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyConsignerFile;

import java.util.List;
import java.util.Map;

public interface SurveyConsignerFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyConsignerFile record);

    int insertSelective(SurveyConsignerFile record);

    SurveyConsignerFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyConsignerFile record);

    int updateByPrimaryKey(SurveyConsignerFile record);

    //
    List<SurveyConsignerFile> selectByInfo(Map<String,Object> map);
}