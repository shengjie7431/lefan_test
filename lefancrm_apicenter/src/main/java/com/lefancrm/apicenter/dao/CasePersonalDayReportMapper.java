package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CasePersonalDayReport;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface CasePersonalDayReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CasePersonalDayReport record);

    int insertSelective(CasePersonalDayReport record);

    CasePersonalDayReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CasePersonalDayReport record);

    int updateByPrimaryKey(CasePersonalDayReport record);

    //报表list
    int selectCountList(ApiRequest request);
    List<CasePersonalDayReport> selectList(ApiRequest request);

}