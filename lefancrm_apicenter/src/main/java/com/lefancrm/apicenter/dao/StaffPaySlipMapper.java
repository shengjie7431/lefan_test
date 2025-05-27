package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffPaySlip;

import java.util.List;
import java.util.Map;

public interface StaffPaySlipMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffPaySlip record);

    int insertSelective(StaffPaySlip record);

    StaffPaySlip selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffPaySlip record);

    int updateByPrimaryKey(StaffPaySlip record);

    //数据
    List<StaffPaySlip> list(Map map);
    int listSize(Map map);

    //通过日期时间查询
    StaffPaySlip selectByWorkTime(String code);

    List<StaffPaySlip> allList(Map map);
}