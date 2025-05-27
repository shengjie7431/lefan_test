package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.BillingApplyAccounts;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillingApplyAccountsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillingApplyAccounts record);

    int insertSelective(BillingApplyAccounts record);

    BillingApplyAccounts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillingApplyAccounts record);

    int updateByPrimaryKey(BillingApplyAccounts record);

    List<BillingApplyAccounts> selectListByBillId(Long id);

    List<BillingApplyAccounts> selectListByImgsId( @Param("billImgsId") Long billImgsId);
}