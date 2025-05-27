package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.BillingRefundInfo;

import java.util.List;

public interface BillingRefundInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillingRefundInfo record);

    int insertSelective(BillingRefundInfo record);

    BillingRefundInfo selectByPrimaryKey(Long id);

    Double selectAllRefundMoneyByMatchId(Long id);

    List<BillingRefundInfo> selectByMatchId(Long id);

    int updateByPrimaryKeySelective(BillingRefundInfo record);

    int updateByPrimaryKey(BillingRefundInfo record);

    List<BillingRefundInfo> selectByMatchIds(String matchIds);
}