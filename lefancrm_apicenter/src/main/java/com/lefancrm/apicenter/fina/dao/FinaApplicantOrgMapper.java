package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaApplicantOrg;

import java.util.List;
import java.util.Map;

public interface FinaApplicantOrgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaApplicantOrg record);

    int insertSelective(FinaApplicantOrg record);

    FinaApplicantOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaApplicantOrg record);

    int updateByPrimaryKey(FinaApplicantOrg record);

    List<FinaApplicantOrg> list(Map map);
    int listSize(Map map);

    FinaApplicantOrg selectByOne(Map map);

    Double serviceMoneyBySettlementId(Long settlementId);
}