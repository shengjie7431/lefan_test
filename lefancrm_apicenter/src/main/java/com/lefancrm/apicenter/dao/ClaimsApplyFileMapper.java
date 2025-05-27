package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.ClaimsApplyFile;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface ClaimsApplyFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClaimsApplyFile record);

    int insertSelective(ClaimsApplyFile record);

    ClaimsApplyFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClaimsApplyFile record);

    int updateByPrimaryKey(ClaimsApplyFile record);

    List<ClaimsApplyFile> list(ApiRequest apiRequest);

    int listSize(ApiRequest apiRequest);
}