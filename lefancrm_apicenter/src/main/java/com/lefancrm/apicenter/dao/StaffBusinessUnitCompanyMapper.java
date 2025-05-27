package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffBusinessUnitCompany;

import java.util.List;
import java.util.Map;

public interface StaffBusinessUnitCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffBusinessUnitCompany record);

    int insertSelective(StaffBusinessUnitCompany record);

    StaffBusinessUnitCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffBusinessUnitCompany record);

    int updateByPrimaryKey(StaffBusinessUnitCompany record);

    //数据
    List<StaffBusinessUnitCompany> list(Map map);
    int listSize(Map map);

    int deleteByInfo(Map map);

}