package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.OrgAccountDetail;

import java.util.List;
import java.util.Map;

public interface OrgAccountDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrgAccountDetail record);

    int insertSelective(OrgAccountDetail record);

    OrgAccountDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrgAccountDetail record);

    int updateByPrimaryKey(OrgAccountDetail record);

    List<OrgAccountDetail> selectOrgAccountDetailByParam(Map<String, Object> paramMap);

    int selectCountOrgAccountDetailByParam(Map<String, Object> paramMap);
}