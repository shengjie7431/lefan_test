package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyMessage;

import java.util.List;
import java.util.Map;

public interface SurveyMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyMessage record);

    int insertSelective(SurveyMessage record);

    SurveyMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyMessage record);

    int updateByPrimaryKey(SurveyMessage record);

    //数据
    List<SurveyMessage> list(Map map);
    int listSize(Map map);

    //批量更新 已读（根据toUserId）
    int updateByToUserIdAndType(Map map);
}