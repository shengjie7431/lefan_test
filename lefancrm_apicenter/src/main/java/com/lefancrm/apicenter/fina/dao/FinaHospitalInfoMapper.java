package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaHospitalInfo;

import java.util.List;
import java.util.Map;

public interface FinaHospitalInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaHospitalInfo record);

    int insertSelective(FinaHospitalInfo record);

    FinaHospitalInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaHospitalInfo record);

    int updateByPrimaryKey(FinaHospitalInfo record);

    List<FinaHospitalInfo> list(Map map);
    int listSize(Map map);
}