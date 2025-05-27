package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyConsignorBillSubject;

import java.util.List;
import java.util.Map;

public interface SurveyConsignorBillSubjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyConsignorBillSubject record);

    int insertSelective(SurveyConsignorBillSubject record);

    SurveyConsignorBillSubject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyConsignorBillSubject record);

    int updateByPrimaryKey(SurveyConsignorBillSubject record);

    //数据
    List<SurveyConsignorBillSubject> list(Map map);
    int listSize(Map map);
}