package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaDiagnosisInfo;

import java.util.List;
import java.util.Map;

public interface FinaDiagnosisInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaDiagnosisInfo record);

    int insertSelective(FinaDiagnosisInfo record);

    FinaDiagnosisInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaDiagnosisInfo record);

    int updateByPrimaryKey(FinaDiagnosisInfo record);

    List<FinaDiagnosisInfo> list(Map map);
    int listSize(Map map);
}