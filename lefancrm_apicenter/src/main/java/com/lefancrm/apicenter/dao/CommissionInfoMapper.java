package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CommissionInfo;

import java.util.List;
import java.util.Map;

public interface CommissionInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommissionInfo record);

    int insertSelective(CommissionInfo record);

    CommissionInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommissionInfo record);

    int updateByPrimaryKey(CommissionInfo record);

    CommissionInfo selectByLevelId(Long levelId);

    //获取数据总值，包含条件查询后的结果
    int selectCountCommissionInfo(Map<String, Object> paramMap);

    //列表list查询，包含条件查询
    List<CommissionInfo> selectCommissionInfoList(Map<String, Object> paramMap);

    //查询CommissionInfo.LevelId=id的list
    List<CommissionInfo> searchCommissionInfoByLevelId(Long id);

}