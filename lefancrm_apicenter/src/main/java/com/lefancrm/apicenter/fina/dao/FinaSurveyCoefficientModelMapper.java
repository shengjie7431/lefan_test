package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSurveyCoefficientModel;
import com.lefancrm.apicenter.fina.model.FinaSurveyConsignorEfficiencyModel;

import java.util.HashMap;
import java.util.List;

public interface FinaSurveyCoefficientModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSurveyCoefficientModel record);

    int insertSelective(FinaSurveyCoefficientModel record);

    FinaSurveyCoefficientModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSurveyCoefficientModel record);

    int updateByPrimaryKey(FinaSurveyCoefficientModel record);

    List<FinaSurveyCoefficientModel> selectListByParam(HashMap<String, Object> paramMap);

    Integer selectListByParamCount(HashMap<String, Object> paramMap);

    FinaSurveyCoefficientModel selectByOne();
}