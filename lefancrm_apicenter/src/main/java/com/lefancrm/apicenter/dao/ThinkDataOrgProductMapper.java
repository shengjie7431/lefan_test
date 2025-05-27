package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.ThinkDataDetail;
import com.lefancrm.apicenter.model.ThinkDataOrgProduct;

import java.util.List;
import java.util.Map;

public interface ThinkDataOrgProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ThinkDataOrgProduct record);

    int insertSelective(ThinkDataOrgProduct record);

    ThinkDataOrgProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThinkDataOrgProduct record);

    int updateByPrimaryKey(ThinkDataOrgProduct record);

    int insertOrgProduct(Map<String,Object> paramMap);

    List<ThinkDataOrgProduct> list(Map<String,Object> paramMap);

    List<ThinkDataOrgProduct> thinkData(Map<String,Object> paramMap);

    List<ThinkDataDetail> thinkDataDetail(Map<String,Object> paramMap);
}