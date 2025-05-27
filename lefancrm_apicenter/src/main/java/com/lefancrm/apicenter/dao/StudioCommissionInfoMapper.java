package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StudioCommissionInfo;

import java.util.List;
import java.util.Map;

public interface StudioCommissionInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudioCommissionInfo record);

    int insertSelective(StudioCommissionInfo record);

    StudioCommissionInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudioCommissionInfo record);

    int updateByPrimaryKey(StudioCommissionInfo record);

     StudioCommissionInfo queryByLevelId(Long levelId);

    //获取数据总值，包含条件查询后的结果
    int selectCountStudioCommissionInfo(Map<String, Object> paramMap);

    //列表list查询，包含条件查询
    List<StudioCommissionInfo> selectStudioCommissionInfoList(Map<String, Object> paramMap);

    //查询StudioCommissionInfo.LevelId=id的list
    List<StudioCommissionInfo> searchStudioCommissionInfoByLevelId(Long id);
}