package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CrmInjuryInfo;

public interface CrmInjuryInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CrmInjuryInfo record);

    int insertSelective(CrmInjuryInfo record);

    CrmInjuryInfo selectByPrimaryKey(Long id);

    CrmInjuryInfo selectByPrimaryCustomerId(Long id);

    int updateByPrimaryKeySelective(CrmInjuryInfo record);

    int updateByPrimaryCustomerIdSelective(CrmInjuryInfo record);

    int updateByPrimaryKey(CrmInjuryInfo record);
}