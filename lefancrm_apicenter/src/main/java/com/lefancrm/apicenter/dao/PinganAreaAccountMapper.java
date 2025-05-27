package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.PinganAreaAccount;

public interface PinganAreaAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PinganAreaAccount record);

    int insertSelective(PinganAreaAccount record);

    PinganAreaAccount selectByPrimaryKey(Long id);

    PinganAreaAccount selectByCaseId(Long caseId);

    PinganAreaAccount selectByCustomerId(String customerId);

    int updateByPrimaryKeySelective(PinganAreaAccount record);

    int updateByPrimaryKey(PinganAreaAccount record);
}