package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.PositionInfo;

import java.util.List;
import java.util.Map;

public interface PositionInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PositionInfo record);

    int insertSelective(PositionInfo record);

    PositionInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PositionInfo record);

    int updateByPrimaryKey(PositionInfo record);

    //获取数据总值，包含条件查询后的结果
    int selectCountPositionInfo(Map<String, Object> paramMap);

    //列表list查询，包含条件查询
    List<PositionInfo> selectPositionInfoList(Map<String, Object> paramMap);

    //查询positionLevelId与将要被删除的级别数据相关的list
    List<PositionInfo> searchPositionInfoByLevelId(Long id);

    PositionInfo selectByPositionIdAndLevelId(Map<String, Object> paramMap);
}