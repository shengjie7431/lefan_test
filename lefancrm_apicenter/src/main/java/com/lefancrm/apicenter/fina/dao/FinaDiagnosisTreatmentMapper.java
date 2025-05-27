package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaDiagnosisTreatment;

import java.util.List;
import java.util.Map;

public interface FinaDiagnosisTreatmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaDiagnosisTreatment record);

    int insertSelective(FinaDiagnosisTreatment record);

    FinaDiagnosisTreatment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaDiagnosisTreatment record);

    int updateByPrimaryKey(FinaDiagnosisTreatment record);

    List<FinaDiagnosisTreatment> list(Map map);
    int listSize(Map map);

    FinaDiagnosisTreatment selectByOne(Map map);
}