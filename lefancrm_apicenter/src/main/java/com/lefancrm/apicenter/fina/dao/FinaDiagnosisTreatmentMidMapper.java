package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaDiagnosisTreatmentMid;

import java.util.List;
import java.util.Map;

public interface FinaDiagnosisTreatmentMidMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaDiagnosisTreatmentMid record);

    int insertSelective(FinaDiagnosisTreatmentMid record);

    FinaDiagnosisTreatmentMid selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaDiagnosisTreatmentMid record);

    int updateByPrimaryKey(FinaDiagnosisTreatmentMid record);

    List<FinaDiagnosisTreatmentMid> list(Map map);
    int listSize(Map map);

    FinaDiagnosisTreatmentMid selectOne(Map map);
}