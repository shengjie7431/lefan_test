package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CasePersonalInfo;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface CasePersonalInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CasePersonalInfo record);

    int insertSelective(CasePersonalInfo record);

    CasePersonalInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CasePersonalInfo record);

    int updateByPrimaryKey(CasePersonalInfo record);

    //报表list
    int selectCountList(ApiRequest request);
    List<CasePersonalInfo> selectList(ApiRequest request);
}