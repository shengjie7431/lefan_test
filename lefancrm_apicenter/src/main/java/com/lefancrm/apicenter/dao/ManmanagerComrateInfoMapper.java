package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.ManmanagerComrateInfo;

public interface ManmanagerComrateInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ManmanagerComrateInfo record);

    int insertSelective(ManmanagerComrateInfo record);

    ManmanagerComrateInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManmanagerComrateInfo record);

    int updateByPrimaryKey(ManmanagerComrateInfo record);
}