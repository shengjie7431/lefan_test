package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSurveyPriceModelOrg;

import java.util.List;
import java.util.Map;

public interface FinaSurveyPriceModelOrgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSurveyPriceModelOrg record);

    int insertSelective(FinaSurveyPriceModelOrg record);

    FinaSurveyPriceModelOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSurveyPriceModelOrg record);

    int updateByPrimaryKey(FinaSurveyPriceModelOrg record);

    //数据
    List<FinaSurveyPriceModelOrg> list(Map map);
    int listSize(Map map);

    //根据条件删除
    int deleteByInfo(Map map);

    int deleteByModelId(Long priceModelId);

    FinaSurveyPriceModelOrg selectOneByInfo(Map map);
}