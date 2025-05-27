package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.ThinkDataDetail;

import java.util.List;
import java.util.Map;

public interface ThinkDataDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ThinkDataDetail record);

    int insertSelective(ThinkDataDetail record);

    ThinkDataDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThinkDataDetail record);

    int updateByPrimaryKey(ThinkDataDetail record);

    int insertStaffOrg(Map<String,Object> paramMap);

    int insertBillcorporation(Map<String,Object> paramMap);

    List<ThinkDataDetail> list(Map<String,Object> paramMap);
}