package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.DistributionBasic;

public interface DistributionBasicMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DistributionBasic record);

    int insertSelective(DistributionBasic record);

    DistributionBasic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DistributionBasic record);

    int updateByPrimaryKey(DistributionBasic record);

    DistributionBasic selectDistributionBasic();
}