package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.BillingApplyCorporationDto;
import com.lefancrm.apicenter.dto.think.SRCompanyDTO;
import com.lefancrm.apicenter.model.BillingApplyCorporation;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface BillingApplyCorporationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillingApplyCorporation record);

    int insertSelective(BillingApplyCorporation record);

    BillingApplyCorporation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillingApplyCorporation record);

    int updateByPrimaryKey(BillingApplyCorporation record);

    List<BillingApplyCorporation> list(ApiRequest request);
    int listSize(ApiRequest request);

    List<BillingApplyCorporationDto> getDtos(Map map);

    List<SRCompanyDTO>   srCompanyBillData(Map paramMap);
    List<SRCompanyDTO>   srAccCompanyData(Map paramMap);

}