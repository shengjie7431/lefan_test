package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.CaseEstimateInfoDto;

import java.util.List;
import java.util.Map;

public interface CaseEstimateInfoMapper {

    //获取数据总值，包含条件查询后的结果
    int selectCountCaseEstimateInfo(Map<String, Object> paramMap);

    //列表list查询，包含条件查询
    List<CaseEstimateInfoDto> selectCaseEstimateInfoList(Map<String, Object> paramMap);

}