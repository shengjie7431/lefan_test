package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.PaymentEstimateInquiry;

import java.util.List;
import java.util.Map;

public interface PaymentEstimateInquiryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaymentEstimateInquiry record);

    int insertSelective(PaymentEstimateInquiry record);

    PaymentEstimateInquiry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaymentEstimateInquiry record);

    int updateByPrimaryKey(PaymentEstimateInquiry record);

    PaymentEstimateInquiry selectPaymentEstimateInquiryByPeId(Map<String, Object> paramMap);
}