package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SuningWithholdApply;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SuningWithholdApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SuningWithholdApply record);

    int insertSelective(SuningWithholdApply record);

    SuningWithholdApply selectByPrimaryKey(Long id);

    List<SuningWithholdApply> selectByCaseInfo(Map<String, Object> map);

    List<SuningWithholdApply> selectList(Map<String,Object> map);
    int selectListSize(Map<String,Object> map);

    int updateByPrimaryKeySelective(SuningWithholdApply record);

    int updateByPrimaryKey(SuningWithholdApply record);

    int selectCountForAccountState(Map<String,Object> map);
}