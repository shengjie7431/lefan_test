package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.CaseAssessmentkpdzMonthReportDto;
import com.lefancrm.apicenter.model.CaseAssessmentkpdzMonthReport;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface CaseAssessmentkpdzMonthReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseAssessmentkpdzMonthReport record);

    int insertSelective(CaseAssessmentkpdzMonthReport record);

    CaseAssessmentkpdzMonthReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseAssessmentkpdzMonthReport record);

    int updateByPrimaryKey(CaseAssessmentkpdzMonthReport record);

    //报表list
    int selectCountList(ApiRequest request);
    List<CaseAssessmentkpdzMonthReportDto> selectList(ApiRequest request);
}