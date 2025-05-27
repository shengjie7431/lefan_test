package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyConsignorPrice2Dto;
import com.lefancrm.apicenter.model.SurveyConsignorPrice;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyConsignorPriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyConsignorPrice record);

    int insertSelective(SurveyConsignorPrice record);

    SurveyConsignorPrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyConsignorPrice record);

    int updateByPrimaryKey(SurveyConsignorPrice record);

    //数据
    List<SurveyConsignorPrice> list(Map map);
    int listSize(Map map);

    //单条数据
    SurveyConsignorPrice selectInfo(Map<String,Object> map);

    //查询“委托方机构”的价格体系
    List<SurveyConsignorPrice2Dto> selectListByConsignorId(Map map);

    //根据条件删除数据
    int deleteByInfo(Map map);

    //查询“委托方机构”的价格体系
    List<SurveyConsignorPrice2Dto> selectConsignorPrice(Long enturyId);
}