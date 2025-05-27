package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CrmCustomerFollows;

import java.util.List;
import java.util.Map;

public interface CrmCustomerFollowsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CrmCustomerFollows record);

    int insertSelective(CrmCustomerFollows record);

    CrmCustomerFollows selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CrmCustomerFollows record);

    int updateByPrimaryKey(CrmCustomerFollows record);

    CrmCustomerFollows selectByPrimaryCustomerId(Long customerId);

    List<CrmCustomerFollows> selectFollowsByCustomerId(Long customerId);

    int countToFollowToday(Map<String, Object> map);
    int countToFollowOverTime(Map<String, Object> map);
    int countForFollowStatus(Map<String, Object> map);
    int countForGiveupFollowStatus(Map<String, Object> map);
}