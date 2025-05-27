package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSurveyPriceModel;

import java.util.List;
import java.util.Map;

public interface FinaSurveyPriceModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSurveyPriceModel record);

    int insertSelective(FinaSurveyPriceModel record);

    FinaSurveyPriceModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSurveyPriceModel record);

    int updateByPrimaryKey(FinaSurveyPriceModel record);

    //数据
    List<FinaSurveyPriceModel> list(Map map);
    int listSize(Map map);

    int copyOneByPriceModelId(FinaSurveyPriceModel FinaSurveyPriceModel);
}