package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.BillingApplyProductOrg;

import java.util.List;
import java.util.Map;

public interface BillingApplyProductOrgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillingApplyProductOrg record);

    int insertSelective(BillingApplyProductOrg record);

    BillingApplyProductOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillingApplyProductOrg record);

    int updateByPrimaryKey(BillingApplyProductOrg record);

    List<BillingApplyProductOrg> list(Map map);
    int listSize(Map map);

    //根据条件删除
    int deleteByInfo(Map map);
}