package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.PinganRepayInfo;

public interface PinganRepayInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PinganRepayInfo record);

    int insertSelective(PinganRepayInfo record);

    PinganRepayInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PinganRepayInfo record);

    int updateByPrimaryKey(PinganRepayInfo record);
}