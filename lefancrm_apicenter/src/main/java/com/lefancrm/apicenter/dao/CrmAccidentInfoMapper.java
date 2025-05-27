package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CrmAccidentInfo;

public interface CrmAccidentInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CrmAccidentInfo record);

    int insertSelective(CrmAccidentInfo record);

    CrmAccidentInfo selectByPrimaryKey(Long id);

    CrmAccidentInfo selectByPrimaryCustomerId(Long customerId);

    int updateByPrimaryKeySelective(CrmAccidentInfo record);

    int updateByPrimaryCustomerIdSelective(CrmAccidentInfo record);

    int updateByPrimaryKey(CrmAccidentInfo record);


}