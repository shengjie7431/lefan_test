package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CasePersonalMonthReport;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface CasePersonalMonthReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CasePersonalMonthReport record);

    int insertSelective(CasePersonalMonthReport record);

    CasePersonalMonthReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CasePersonalMonthReport record);

    int updateByPrimaryKey(CasePersonalMonthReport record);

    //报表list
    int selectCountList(ApiRequest request);
    List<CasePersonalMonthReport> selectList(ApiRequest request);

}