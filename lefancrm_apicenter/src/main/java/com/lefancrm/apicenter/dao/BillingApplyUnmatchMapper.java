package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.BillingApplyUnmatch;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface BillingApplyUnmatchMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillingApplyUnmatch record);

    int insertSelective(BillingApplyUnmatch record);

    BillingApplyUnmatch selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillingApplyUnmatch record);

    int updateByPrimaryKey(BillingApplyUnmatch record);

    //分页数据
    List<BillingApplyUnmatch> selectList(ApiRequest request);
    int selectListSize(ApiRequest request);

    BillingApplyUnmatch selectByCaseNo(String caseNo);
}