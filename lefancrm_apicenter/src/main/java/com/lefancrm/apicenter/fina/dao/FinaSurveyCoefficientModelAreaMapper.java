package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSurveyCoefficientModelArea;

import java.util.List;
import java.util.Map;

public interface FinaSurveyCoefficientModelAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSurveyCoefficientModelArea record);

    int insertSelective(FinaSurveyCoefficientModelArea record);

    FinaSurveyCoefficientModelArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSurveyCoefficientModelArea record);

    int updateByPrimaryKey(FinaSurveyCoefficientModelArea record);

    //数据
    List<FinaSurveyCoefficientModelArea> list(Map map);

}