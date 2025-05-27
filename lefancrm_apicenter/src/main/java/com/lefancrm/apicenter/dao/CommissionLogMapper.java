package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CommissionLog;

import java.util.List;
import java.util.Map;

public interface CommissionLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommissionLog record);

    int insertSelective(CommissionLog record);

    CommissionLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommissionLog record);

    int updateByPrimaryKey(CommissionLog record);

    CommissionLog selectByCaseIdAndCommissionType(Map<String, Object> paramMap);

    List<CommissionLog> searchCommissionLogList(Map<String, Object> paramMap);

}