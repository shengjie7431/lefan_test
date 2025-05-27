package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.BillingApplyRefund;

import java.util.List;
import java.util.Map;

public interface BillingApplyRefundMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillingApplyRefund record);

    int insertSelective(BillingApplyRefund record);

    BillingApplyRefund selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillingApplyRefund record);

    int updateByPrimaryKey(BillingApplyRefund record);

    List<BillingApplyRefund> list(Map map);

    int listSize(Map map);
}