package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaTreatmentInfo;

import java.util.List;
import java.util.Map;

public interface FinaTreatmentInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaTreatmentInfo record);

    int insertSelective(FinaTreatmentInfo record);

    FinaTreatmentInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaTreatmentInfo record);

    int updateByPrimaryKey(FinaTreatmentInfo record);

    List<FinaTreatmentInfo> list(Map map);
    int listSize(Map map);

    List<FinaTreatmentInfo> selectByDiagnosis(Map map);
}