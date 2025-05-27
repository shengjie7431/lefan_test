package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.LawCaseInfoDto;
import com.lefancrm.apicenter.model.LawCaseInfo;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface LawCaseInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LawCaseInfo record);

    int insertSelective(LawCaseInfo record);

    LawCaseInfoDto selectByPrimaryKey(Long id);

    LawCaseInfoDto selectByCaseNo(String caseNo);

    int updateByPrimaryKeySelective(LawCaseInfo record);

    int updateByPrimaryKey(LawCaseInfo record);

    int findListSize(ApiRequest apiRequest);

    List<LawCaseInfoDto> findList(ApiRequest apiRequest);
}