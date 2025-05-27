package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.BillingApplyCompany;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface BillingApplyCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillingApplyCompany record);

    int insertSelective(BillingApplyCompany record);

    BillingApplyCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillingApplyCompany record);

    int updateByPrimaryKey(BillingApplyCompany record);

    List<BillingApplyCompany> selectList(ApiRequest request);

    int selectListSize(ApiRequest request);

    BillingApplyCompany selectByInfo(Map<String, Object> map);
}