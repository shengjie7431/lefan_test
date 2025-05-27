package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaMedicalInsurance;

import java.util.List;
import java.util.Map;

public interface FinaMedicalInsuranceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaMedicalInsurance record);

    int insertSelective(FinaMedicalInsurance record);

    FinaMedicalInsurance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaMedicalInsurance record);

    int updateByPrimaryKey(FinaMedicalInsurance record);

    List<FinaMedicalInsurance> list(Map map);
    int listSize(Map map);

    FinaMedicalInsurance selectOne(Map map);
}