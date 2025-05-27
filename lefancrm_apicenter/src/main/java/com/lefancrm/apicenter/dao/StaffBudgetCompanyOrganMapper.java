package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffBudgetCompanyOrgan;
import com.lefancrm.apicenter.model.StaffCompanyOrgan;

import java.util.List;
import java.util.Map;

public interface StaffBudgetCompanyOrganMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffBudgetCompanyOrgan record);

    int insertSelective(StaffBudgetCompanyOrgan record);

    StaffBudgetCompanyOrgan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffBudgetCompanyOrgan record);

    int updateByPrimaryKey(StaffBudgetCompanyOrgan record);

    //数据
    List<StaffBudgetCompanyOrgan> list(Map map);

    int listSize(Map map);

    int deleteByInfo(Map map);

    StaffBudgetCompanyOrgan selectByOrganId(Long id);

    List<StaffBudgetCompanyOrgan> selectOrgans(Map map);
}