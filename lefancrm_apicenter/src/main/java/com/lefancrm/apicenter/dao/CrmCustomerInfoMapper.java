package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.CciListDto;
import com.lefancrm.apicenter.dto.CrmCustomerFollowsDto;
import com.lefancrm.apicenter.model.CrmCustomerInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CrmCustomerInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CrmCustomerInfo record);

    int insertSelective(CrmCustomerInfo record);

    CrmCustomerInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CrmCustomerInfo record);

    int updateByPrimaryKey(CrmCustomerInfo record);

    List<CrmCustomerInfo> selectCustomerByOrgIdOrCcId(HashMap<String,Object> map);
    int selectCustomerByOrgIdOrCcIdCount(HashMap<String,Object> map);

    List<CrmCustomerInfo> selectCustomerByOrgIds(HashMap<String,Object> map);

    int selectCustomerByOrgIdsCount(HashMap<String,Object> map);

    List<CrmCustomerInfo> selectCrmCustomerInfoByCcid(Map<String, Object> paramMap);

    List<CrmCustomerFollowsDto> listForFollowStatus(Map<String, Object> paramMap);
    List<CrmCustomerFollowsDto> listForFollowOverTime(Map<String, Object> paramMap);
    List<CrmCustomerFollowsDto> listForAlreadyOrGiveupFollowStatus(Map<String, Object> paramMap);
    List<CrmCustomerFollowsDto> listForGiveupFollowStatus(Map<String, Object> paramMap);

    List<HashMap<String,Object>> queryAllOrgInfo();

    List<HashMap<String,Object>> queryUserInfoByCc();

    List<HashMap<String,Object>> queryOrgIdAndOrgNameByOrgId(Long orgId);

    List<HashMap<String,Object>> queryUserIdAndUserNameByOrgIds(Long orgId);

    List<HashMap<String,Object>> queryUserIdAndUserNameByOrgId(Long orgId);

    List<CciListDto> backendList(HashMap<String,Object> map);

    int backendListCount(HashMap<String,Object> map);

    Long selectAreaByAreaName(HashMap<String,Object> map);

}