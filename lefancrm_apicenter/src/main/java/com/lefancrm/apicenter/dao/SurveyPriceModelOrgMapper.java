package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyPriceModelOrg;

import java.util.List;
import java.util.Map;

public interface SurveyPriceModelOrgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyPriceModelOrg record);

    int insertSelective(SurveyPriceModelOrg record);

    SurveyPriceModelOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyPriceModelOrg record);

    int updateByPrimaryKey(SurveyPriceModelOrg record);

    //数据
    List<SurveyPriceModelOrg> list(Map map);
    int listSize(Map map);

    //根据条件删除
    int deleteByInfo(Map map);

    SurveyPriceModelOrg selectOneByInfo(Map map);
}