package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaAgreementInfo;

import java.util.List;
import java.util.Map;

public interface FinaAgreementInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaAgreementInfo record);

    int insertSelective(FinaAgreementInfo record);

    FinaAgreementInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaAgreementInfo record);

    int updateByPrimaryKey(FinaAgreementInfo record);

    List<FinaAgreementInfo> list(Map map);
    int listSize(Map map);
}