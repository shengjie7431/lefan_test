package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.PaymentEstimateReport;

import java.util.List;
import java.util.Map;

public interface PaymentEstimateReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaymentEstimateReport record);

    int insertSelective(PaymentEstimateReport record);

    PaymentEstimateReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaymentEstimateReport record);

    int updateByPrimaryKey(PaymentEstimateReport record);

    List<PaymentEstimateReport> selectPaymentEstimateReportByPeId(Map<String, Object> paramMap);

    int updatePaymentEstimateReport(Map<String, Object> paramMa);
}