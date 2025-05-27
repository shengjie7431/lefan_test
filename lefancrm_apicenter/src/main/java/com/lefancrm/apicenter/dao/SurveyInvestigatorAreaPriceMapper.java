package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyInvestigatorAreaPrice2Dto;
import com.lefancrm.apicenter.model.SurveyInvestigatorAreaPrice;

import java.util.List;
import java.util.Map;

public interface SurveyInvestigatorAreaPriceMapper {
    int deleteByPrimaryKey(Long areaId);

    int insert(SurveyInvestigatorAreaPrice record);

    int insertSelective(SurveyInvestigatorAreaPrice record);

    SurveyInvestigatorAreaPrice selectByPrimaryKey(Long areaId);

    int updateByPrimaryKeySelective(SurveyInvestigatorAreaPrice record);

    int updateByPrimaryKey(SurveyInvestigatorAreaPrice record);

    //数据
    List<SurveyInvestigatorAreaPrice> list(Map map);
    int listSize(Map map);

    SurveyInvestigatorAreaPrice selectByInfo(Map map);

    int updateByInfo(SurveyInvestigatorAreaPrice record);


    //list数据。替换展示方式
    List<SurveyInvestigatorAreaPrice2Dto> selectList(Map map);

    //根据条件删除数据
    int deleteByInfo(Map map);

    List<SurveyInvestigatorAreaPrice2Dto> selectFranchiseePrice(Map map);
}