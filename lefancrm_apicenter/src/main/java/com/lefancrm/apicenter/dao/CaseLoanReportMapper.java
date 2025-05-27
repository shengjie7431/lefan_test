package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseLoanReport;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface CaseLoanReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseLoanReport record);

    int insertSelective(CaseLoanReport record);

    CaseLoanReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseLoanReport record);

    int updateByPrimaryKey(CaseLoanReport record);

    //报表list
    int selectCountList(ApiRequest request);
    List<CaseLoanReport> selectList(ApiRequest request);
}