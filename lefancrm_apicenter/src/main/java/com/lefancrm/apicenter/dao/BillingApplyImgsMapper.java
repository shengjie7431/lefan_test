package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.BillingApplyImgs;

import java.util.List;
import java.util.Map;

public interface BillingApplyImgsMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByBillImgId(Long billImgId);

    int insert(BillingApplyImgs record);

    int insertSelective(BillingApplyImgs record);

    BillingApplyImgs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillingApplyImgs record);

    int updateByPrimaryKey(BillingApplyImgs record);

    List<BillingApplyImgs> selectListByBillId(Long id);

    Double selectBillMoneyByBillId(Long billId);

    int repeatBillingCodeCount(String billingCode);
}