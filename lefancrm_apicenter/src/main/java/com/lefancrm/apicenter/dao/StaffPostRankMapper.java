package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.StaffPostRank;

import java.util.List;
import java.util.Map;

public interface StaffPostRankMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StaffPostRank record);

    int insertSelective(StaffPostRank record);

    StaffPostRank selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffPostRank record);

    int updateByPrimaryKey(StaffPostRank record);

    List<StaffPostRank> list(Map map);
    int listSize(Map map);

    List<StaffPostRank> listAll(Map map);

    StaffPostRank selectByOne(String rankName);

}