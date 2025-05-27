package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyOrder;

import java.util.List;
import java.util.Map;

public interface SurveyOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyOrder record);

    int insertSelective(SurveyOrder record);

    SurveyOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyOrder record);

    int updateByPrimaryKey(SurveyOrder record);


    List<SurveyOrder> selectSurveyOrderListByParam(Map<String, Object> paramMap);
    SurveyOrder selectSurveyOrderDetailsByOrderCode(Map<String, Object> paramMap);

    //数据
    List<SurveyOrder> list(Map map);
    int listSize(Map map);
}