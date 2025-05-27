package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.CasePerkpdzMonthReportDto;
import com.lefancrm.apicenter.model.CasePerkpdzMonthReport;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface CasePerkpdzMonthReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CasePerkpdzMonthReport record);

    int insertSelective(CasePerkpdzMonthReport record);

    CasePerkpdzMonthReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CasePerkpdzMonthReport record);

    int updateByPrimaryKey(CasePerkpdzMonthReport record);

    //报表list
    int selectCountList(ApiRequest request);
    List<CasePerkpdzMonthReportDto> selectList(ApiRequest request);
}