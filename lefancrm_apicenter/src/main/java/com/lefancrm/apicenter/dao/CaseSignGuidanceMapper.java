package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseSignGuidance;

import java.util.Map;

public interface CaseSignGuidanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseSignGuidance record);

    int insertSelective(CaseSignGuidance record);

    CaseSignGuidance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseSignGuidance record);

    int updateByPrimaryKey(CaseSignGuidance record);

    CaseSignGuidance searchInfoByCaseId(Map<String,Object> map);
}