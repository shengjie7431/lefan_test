package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaApplicantMoney;

public interface FinaApplicantMoneyMapper {
    int deleteByPrimaryKey(Long finaInfoId);

    int insert(FinaApplicantMoney record);

    int insertSelective(FinaApplicantMoney record);

    FinaApplicantMoney selectByPrimaryKey(Long finaInfoId);

    int updateByPrimaryKeySelective(FinaApplicantMoney record);

    int updateByPrimaryKey(FinaApplicantMoney record);
}