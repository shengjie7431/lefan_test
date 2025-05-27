package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.BillingApplyEnumItem;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface BillingApplyEnumItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillingApplyEnumItem record);

    int insertSelective(BillingApplyEnumItem record);

    BillingApplyEnumItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillingApplyEnumItem record);

    int updateByPrimaryKey(BillingApplyEnumItem record);

    List<BillingApplyEnumItem> list(Map map);
    int listSize(Map map);

    BillingApplyEnumItem selectByInfo(Map map);

    //根据条件删除
    int deleteByInfo(Map map);

    //筛选某角色的，已选择的公司名下开票项目
    List<BillingApplyEnumItem> selectByRoleId(Long roleId);
}