package com.lefancrm.apicenter.fina.dao;


import com.lefancrm.apicenter.fina.model.FinaTaskInfo;

import java.util.List;

public interface FinaTaskInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaTaskInfo record);

    int insertSelective(FinaTaskInfo record);

    FinaTaskInfo selectByPrimaryKey(Long id);

    List<FinaTaskInfo> selectList();

    int updateByPrimaryKeySelective(FinaTaskInfo record);

    int updateByPrimaryKey(FinaTaskInfo record);
}