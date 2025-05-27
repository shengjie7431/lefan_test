package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyDirectionAreaHistory;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyDirectionAreaHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyDirectionAreaHistory record);

    int insertSelective(SurveyDirectionAreaHistory record);

    SurveyDirectionAreaHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyDirectionAreaHistory record);

    int updateByPrimaryKey(SurveyDirectionAreaHistory record);

    List<SurveyDirectionAreaHistory> list(Map map);

    int listSize(ApiRequest apiRequest);

    SurveyDirectionAreaHistory selectByInfo(Map map);
}