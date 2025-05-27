package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.BillingApplyMaterialDto;
import com.lefancrm.apicenter.model.BillingApplyMaterial;

import java.util.List;

public interface BillingApplyMaterialMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillingApplyMaterial record);

    int insertSelective(BillingApplyMaterial record);

    BillingApplyMaterial selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillingApplyMaterial record);

    int updateByPrimaryKey(BillingApplyMaterial record);

    List<BillingApplyMaterial> selectListByBillId(Long billId);

    List<BillingApplyMaterialDto> selectByBillId(Long billId);
}