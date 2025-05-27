package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CrmCaseInfo;

public interface CrmCaseInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CrmCaseInfo record);

    int insertSelective(CrmCaseInfo record);

    CrmCaseInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CrmCaseInfo record);

    int updateByPrimaryCustomerIdSelective(CrmCaseInfo record);

    int updateByPrimaryKey(CrmCaseInfo record);

    CrmCaseInfo selectCaseByCustomerId(Long customerId);
}