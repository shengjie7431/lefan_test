package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyCommonAreaPrice2Dto;
import com.lefancrm.apicenter.model.SurveyCommonAreaPrice;

import java.util.List;
import java.util.Map;

public interface SurveyCommonAreaPriceMapper {
    int deleteByPrimaryKey(Long areaId);

    int insert(SurveyCommonAreaPrice record);

    int insertSelective(SurveyCommonAreaPrice record);

    SurveyCommonAreaPrice selectByPrimaryKey(Long areaId);

    int updateByPrimaryKeySelective(SurveyCommonAreaPrice record);

    int updateByPrimaryKey(SurveyCommonAreaPrice record);

    //数据
    List<SurveyCommonAreaPrice> list(Map map);
    int listSize(Map map);

    SurveyCommonAreaPrice selectByInfo(Map map);

    int updateByInfo(SurveyCommonAreaPrice record);

    //list数据。替换展示方式
    List<SurveyCommonAreaPrice2Dto> selectList(Map map);

    //根据条件删除数据
    int deleteByInfo(Map map);

    //查询“委托方机构”的价格体系
    List<SurveyCommonAreaPrice2Dto> selectConsignorPrice(Map map);
}