package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffBudgetCompany;
import com.lefancrm.apicenter.model.StaffCompany;

import java.util.List;
import java.util.Map;

public interface StaffBudgetCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffBudgetCompany record);

    int insertSelective(StaffBudgetCompany record);

    StaffBudgetCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffBudgetCompany record);

    int updateByPrimaryKey(StaffBudgetCompany record);

    //数据
    List<StaffBudgetCompany> list(Map map);
    int listSize(Map map);

    StaffBudgetCompany selectByOne(String name);


}