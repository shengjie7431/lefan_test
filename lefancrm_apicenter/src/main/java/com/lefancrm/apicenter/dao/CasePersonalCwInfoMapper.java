package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.CasePersonalCwInfoDto;
import com.lefancrm.apicenter.model.CasePersonalCwInfo;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface CasePersonalCwInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CasePersonalCwInfo record);

    int insertSelective(CasePersonalCwInfo record);

    CasePersonalCwInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CasePersonalCwInfo record);

    int updateByPrimaryKey(CasePersonalCwInfo record);

    //报表list
    int selectCountList(ApiRequest request);
    List<CasePersonalCwInfoDto> selectList(ApiRequest request);

    CasePersonalCwInfoDto selectById(Long id);
}