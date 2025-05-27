package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.OrgAccount;

import java.util.List;
import java.util.Map;

public interface OrgAccountMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(OrgAccount record);

    int insertSelective(OrgAccount record);

    OrgAccount selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(OrgAccount record);

    int updateByPrimaryKey(OrgAccount record);

    int addMoneyOrgAccountByOrgId(Map<String, Object> paramMap);

    int lessMoneyOrgAccountByOrgId(Map<String, Object> paramMap);

    List<OrgAccount> selectOrgAccountByParam(Map<String, Object> paramMap);
    int selectCountOrgAccountByParam(Map<String, Object> paramMap);
}