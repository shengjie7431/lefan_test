package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.CaseApplyDto;
import com.lefancrm.apicenter.model.CaseApply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CaseApplyMapper {

    //列表list查询，包含条件查询
    List<CaseApplyDto> selectCaseApplyList(Map<String, Object> paramMap);
    int selectCaseApplyListSize(Map<String, Object> paramMap);

    int insertSelective(CaseApply record);

    CaseApply selectByPrimaryKey(Long id);

    int updateCaseNoAndStateById(HashMap<String,Object> map);

    int updateByPrimaryKeySelective(CaseApply record);
}