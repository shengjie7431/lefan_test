package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyConsignorModel;

import java.util.List;
import java.util.Map;

public interface SurveyConsignorModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyConsignorModel record);

    int insertSelective(SurveyConsignorModel record);

    SurveyConsignorModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyConsignorModel record);

    int updateByPrimaryKey(SurveyConsignorModel record);

    //数据
    List<SurveyConsignorModel> list(Map map);
    int listSize(Map map);

    //根据条件删除
    int deleteByInfo(Map map);

    //根据consignorId查询数据  limit 1
    SurveyConsignorModel selectByConsignorId(Long consignorId);
}