package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyEmailInfo;

import java.util.List;
import java.util.Map;

public interface SurveyEmailInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyEmailInfo record);

    int insertSelective(SurveyEmailInfo record);

    SurveyEmailInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyEmailInfo record);

    int updateByPrimaryKey(SurveyEmailInfo record);

    //数据
    List<SurveyEmailInfo> list(Map map);
    int listSize(Map map);
}