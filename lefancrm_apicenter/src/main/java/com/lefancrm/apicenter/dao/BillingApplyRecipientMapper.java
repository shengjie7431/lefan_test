package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.BillingApplyRecipient;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface BillingApplyRecipientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillingApplyRecipient record);

    int insertSelective(BillingApplyRecipient record);

    BillingApplyRecipient selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillingApplyRecipient record);

    int updateByPrimaryKey(BillingApplyRecipient record);

    List<BillingApplyRecipient> selectList(ApiRequest request);

    int selectListSize(ApiRequest request);

    BillingApplyRecipient selectByInfo(Map<String, Object> map);

}