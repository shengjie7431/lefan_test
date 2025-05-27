package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.CaseApplicationInfoDto;

import java.util.List;
import java.util.Map;

public interface CaseApplicationInfoMapper {

    //获取数据总值，包含条件查询后的结果
    int selectCountCaseApplicationInfo(Map<String, Object> paramMap);

    //列表list查询，包含条件查询
    List<CaseApplicationInfoDto> selectCaseApplicationInfoList(Map<String, Object> paramMap);

}