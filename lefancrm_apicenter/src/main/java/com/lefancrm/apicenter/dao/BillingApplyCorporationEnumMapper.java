package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.BillingApplyCorporationEnum;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface BillingApplyCorporationEnumMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillingApplyCorporationEnum record);

    int insertSelective(BillingApplyCorporationEnum record);

    BillingApplyCorporationEnum selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillingApplyCorporationEnum record);

    int updateByPrimaryKey(BillingApplyCorporationEnum record);

    List<BillingApplyCorporationEnum> list(Map map);
    int listSize(Map map);

    BillingApplyCorporationEnum selectByInfo(Map map);

    //根据条件删除
    int deleteByInfo(Map map);

    //筛选某角色的，已选择的公司名下开票产品
    List<BillingApplyCorporationEnum> selectByRoleId(Long roleId);
}