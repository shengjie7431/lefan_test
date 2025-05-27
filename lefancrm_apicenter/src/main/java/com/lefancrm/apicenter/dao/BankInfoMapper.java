package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.BankInfo;

import java.util.List;
import java.util.Map;

public interface BankInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BankInfo record);

    int insertSelective(BankInfo record);

    BankInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BankInfo record);

    int updateByPrimaryKey(BankInfo record);

    List<BankInfo> selectBankInfoList(Map<String, Object> paramMap);
}