package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaApplicantInfo;

import java.util.List;
import java.util.Map;

public interface FinaApplicantInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaApplicantInfo record);

    int insertSelective(FinaApplicantInfo record);

    FinaApplicantInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaApplicantInfo record);

    int updateByPrimaryKey(FinaApplicantInfo record);

    List<FinaApplicantInfo> list(Map map);
    int listSize(Map map);

}