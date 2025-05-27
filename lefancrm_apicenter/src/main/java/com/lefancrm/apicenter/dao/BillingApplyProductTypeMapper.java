package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.BillingApplyProductTypeDto;
import com.lefancrm.apicenter.model.BillingApplyProductType;

import java.util.List;
import java.util.Map;

public interface BillingApplyProductTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillingApplyProductType record);

    int insertSelective(BillingApplyProductType record);

    BillingApplyProductType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillingApplyProductType record);

    int updateByPrimaryKey(BillingApplyProductType record);

    List<BillingApplyProductType> list(Map map);
    int listSize(Map map);

    List<BillingApplyProductTypeDto> getDtos(Map map);
}