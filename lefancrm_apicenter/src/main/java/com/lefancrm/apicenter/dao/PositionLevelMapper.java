package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.PositionLevelDto;
import com.lefancrm.apicenter.model.PositionInfo;
import com.lefancrm.apicenter.model.PositionLevel;

import java.util.List;
import java.util.Map;

public interface PositionLevelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PositionLevel record);

    int insertSelective(PositionLevel record);

    PositionLevel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PositionLevel record);

    int updateByPrimaryKey(PositionLevel record);

    //获取数据总值，包含条件查询后的结果
    int selectCountPositionLevel(Map<String, Object> paramMap);

    //列表list查询，包含条件查询
    List<PositionLevelDto> selectPositionLevelList(Map<String, Object> paramMap);

    //根据ID获取数据，返回DTO中（背景：获取parentName）
    PositionLevelDto selectDtoByPrimaryKey(Long id);

    //列表ManagerComrateId获取list
    List<PositionLevel> searchListByManagerComrateId(Long id);

    //列表ParentId获取list
    List<PositionLevel> searchListByParentId(Long id);

}