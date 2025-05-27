package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.CaseApplicationInfoDto;
import com.lefancrm.apicenter.dto.CasePayInfoDto;
import com.lefancrm.apicenter.model.CasePayInfo;

import java.util.List;
import java.util.Map;

public interface CasePayInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CasePayInfo record);

    int insertSelective(CasePayInfo record);

    CasePayInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CasePayInfo record);

    int updateByPrimaryKey(CasePayInfo record);

    List<CasePayInfo> selectByCaseId(Long caseId);

    //获取数据总值，包含条件查询后的结果
    int selectCountCasePayInfo(Map<String, Object> paramMap);

    //列表list查询，包含条件查询
    List<CasePayInfoDto> selectCasePayInfoList(Map<String, Object> paramMap);
}