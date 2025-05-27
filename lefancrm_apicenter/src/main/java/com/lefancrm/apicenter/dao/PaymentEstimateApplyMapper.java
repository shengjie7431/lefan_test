package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.PaymentEstimateApply;

import java.util.List;
import java.util.Map;

public interface PaymentEstimateApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaymentEstimateApply record);

    int insertSelective(PaymentEstimateApply record);

    PaymentEstimateApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaymentEstimateApply record);

    int updateByPrimaryKey(PaymentEstimateApply record);

    List<PaymentEstimateApply> selectPaymentEstimateApplyList(Map<String, Object> paramMap);

    int selectCountPaymentEstimateApply(Map<String, Object> paramMap);

    List<PaymentEstimateApply> selectPEAByCaseIdNew(Map<String, Object> paramMap);

    int selectCountPEAByCaseIdNew(Map<String, Object> paramMap);

    List<PaymentEstimateApply> selectPaymentEstimateApplyByCaseIdNew(Map<String, Object> paramMap);
    int selectCountPaymentEstimateApplyByCaseIdNew(Map<String, Object> paramMap);

    /**
     * 根据案件中心信息 查出唯一的测试申请记录
     * @param paramMap  caseId  caseType
     * @return
     */
    PaymentEstimateApply selectPEAByCaseCenterInfoNew(Map<String, Object> paramMap);
}