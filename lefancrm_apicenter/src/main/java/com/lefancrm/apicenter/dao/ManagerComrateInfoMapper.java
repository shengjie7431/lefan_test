package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.ManagerComrateInfo;

import java.util.List;
import java.util.Map;

public interface ManagerComrateInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ManagerComrateInfo record);

    int insertSelective(ManagerComrateInfo record);

    ManagerComrateInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerComrateInfo record);

    int updateByPrimaryKey(ManagerComrateInfo record);

    //获取数据总值，包含条件查询后的结果
    int selectManagerComrateInfo(Map<String, Object> paramMap);

    //列表list查询，包含条件查询
    List<ManagerComrateInfo> selectManagerComrateInfoList(Map<String, Object> paramMap);
}