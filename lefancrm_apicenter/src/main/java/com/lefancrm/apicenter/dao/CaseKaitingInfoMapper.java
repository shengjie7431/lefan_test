package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseKaitingInfo;

import java.util.List;

public interface CaseKaitingInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseKaitingInfo record);

    int insertSelective(CaseKaitingInfo record);

    CaseKaitingInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseKaitingInfo record);

    int updateByPrimaryKey(CaseKaitingInfo record);

    List<CaseKaitingInfo> selectByCaseCenterId(Long caseCenterId);
}