package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseClaimReport;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface CaseClaimReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseClaimReport record);

    int insertSelective(CaseClaimReport record);

    CaseClaimReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseClaimReport record);

    int updateByPrimaryKey(CaseClaimReport record);

    //报表list
    int selectCountList(ApiRequest request);
    List<CaseClaimReport> selectList(ApiRequest request);
}