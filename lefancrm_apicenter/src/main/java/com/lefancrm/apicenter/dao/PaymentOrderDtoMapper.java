package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.PaymentOrderDto;

public interface PaymentOrderDtoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaymentOrderDto record);

    int insertSelective(PaymentOrderDto record);

    PaymentOrderDto selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaymentOrderDto record);

    int updateByPrimaryKey(PaymentOrderDto record);
}