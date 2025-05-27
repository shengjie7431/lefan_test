package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.InfoSafeCompany;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface InfoSafeCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InfoSafeCompany record);

    int insertSelective(InfoSafeCompany record);

    InfoSafeCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InfoSafeCompany record);

    int updateByPrimaryKey(InfoSafeCompany record);

    List<InfoSafeCompany> selectList(ApiRequest request);
    int selectListSize(ApiRequest request);

}