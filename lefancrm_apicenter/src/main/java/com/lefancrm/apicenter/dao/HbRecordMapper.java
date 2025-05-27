package com.lefancrm.apicenter.dao;


import com.lefancrm.apicenter.model.HbRecord;

import java.util.List;
import java.util.Map;

public interface HbRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HbRecord record);

    int insertSelective(HbRecord record);

    HbRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HbRecord record);

    int updateByPrimaryKey(HbRecord record);
    List<HbRecord> selectList(Map map);
    int selectListSize(Map  map);

}