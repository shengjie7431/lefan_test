package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SaleMoneyReport;

import java.util.Map;

public interface SaleMoneyReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaleMoneyReport record);

    int insertSelective(SaleMoneyReport record);

    SaleMoneyReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SaleMoneyReport record);

    int updateByPrimaryKey(SaleMoneyReport record);

    /**
     * 查询实际金额
     * @param paramMap
     * @return
     */
    int querySaleAmount(Map<String,Object> paramMap);
    /**
     * 查询超过人数
     * @param paramMap
     * @return
     */
    int queryOutNumberByUserId(Map<String,Object> paramMap);

    SaleMoneyReport queryOrgNameByUserId(Map<String,Object> paramMap);

}