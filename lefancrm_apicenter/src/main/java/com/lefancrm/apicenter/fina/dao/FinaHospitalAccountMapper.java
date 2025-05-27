package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaHospitalAccount;

import java.util.List;
import java.util.Map;

public interface FinaHospitalAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaHospitalAccount record);

    int insertSelective(FinaHospitalAccount record);

    FinaHospitalAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaHospitalAccount record);

    int updateByPrimaryKey(FinaHospitalAccount record);

    List<FinaHospitalAccount> list(Map map);
    int listSize(Map map);

    FinaHospitalAccount selectByOne(Map map);
}