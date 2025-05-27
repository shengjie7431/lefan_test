package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.FinancialReApply;

import java.util.List;
import java.util.Map;

public interface FinancialReApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinancialReApply record);

    int insertSelective(FinancialReApply record);

    FinancialReApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinancialReApply record);

    int updateByPrimaryKey(FinancialReApply record);

    List<FinancialReApply> list(Map map);
    int listSize(Map map);

    //生成“付款管理”
    int generateSurveyPayInfo(Map<String,Object> map);

    //获取最新一条历史数据
    FinancialReApply selectLastOne(Long applyUserId);
}