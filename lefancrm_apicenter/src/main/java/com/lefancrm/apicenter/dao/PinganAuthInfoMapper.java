package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.PinganAuthInfo;

import java.util.Map;

public interface PinganAuthInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PinganAuthInfo record);

    int insertSelective(PinganAuthInfo record);

    PinganAuthInfo selectByPrimaryKey(Long id);

    PinganAuthInfo selectByParams(PinganAuthInfo authInfo);

    PinganAuthInfo selectByCustomerId(String customerId);

    PinganAuthInfo selectByLoanAgreementNo(String loanAgreementNo);

    int updateByPrimaryKeySelective(PinganAuthInfo record);

    int updateByPrimaryKey(PinganAuthInfo record);
}