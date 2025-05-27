package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SaleGoal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SaleGoalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaleGoal record);

    int insertSelective(SaleGoal record);

    SaleGoal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SaleGoal record);

    int updateByPrimaryKey(SaleGoal record);

    /**
     * 查询目标金额
     * @param paramMap
     * @return
     */
    int querySaleGoalGold(Map<String,Object> paramMap);

    int querySaleGoalGoldDate(Map<String,Object> paramMap);


    List<HashMap<String,Object>> list(Map<String,Object> paramMap);

    List<SaleGoal> saleGoalList();

    SaleGoal selectIsSaleGoalByUser(Map<String,Object> map);

    String getOrgRootList(Long orgId);

    int updateSaleGoalById(Map<String,Object> map);

    String selectOrgNameByOrgId(Long orgId);

}