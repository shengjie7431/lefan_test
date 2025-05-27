package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyPriceModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyPriceModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyPriceModel record);

    int insertSelective(SurveyPriceModel record);

    SurveyPriceModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyPriceModel record);

    int updateByPrimaryKey(SurveyPriceModel record);

    //数据
    List<SurveyPriceModel> list(Map map);
    int listSize(Map map);

    int copyOneByPriceModelId(SurveyPriceModel surveyPriceModel);

    SurveyPriceModel selectModelByOrgId(Map paramMap);
}