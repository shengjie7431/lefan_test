package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.PinganBindCard;

public interface PinganBindCardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PinganBindCard record);

    int insertSelective(PinganBindCard record);

    PinganBindCard selectByPrimaryKey(Long id);
    PinganBindCard selectByCardNo(String cardNo);
    PinganBindCard selectByCustomerId(String customerId);

    int updateByPrimaryKeySelective(PinganBindCard record);

    int updateByPrimaryKey(PinganBindCard record);
}