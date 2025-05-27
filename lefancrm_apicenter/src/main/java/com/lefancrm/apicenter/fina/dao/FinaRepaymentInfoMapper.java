package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaRepaymentInfo;

import java.util.List;
import java.util.Map;

public interface FinaRepaymentInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaRepaymentInfo record);

    int insertSelective(FinaRepaymentInfo record);

    FinaRepaymentInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaRepaymentInfo record);

    int updateByPrimaryKey(FinaRepaymentInfo record);

    List<FinaRepaymentInfo> list(Map<String,Object> paramMap);
}