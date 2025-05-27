package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.BillingReceiveInfo;

import java.util.List;
import java.util.Map;

/**
 * 认领明细表
 * @author EDZ
 */
public interface BillingReceiveInfoMapper {

    /**
     * 查询所有数据
     * @param map
     * @return
     */
    List<BillingReceiveInfo> selectByMap(Map map);
    List<BillingReceiveInfo> selectByList(Map map);

    /**
     * 查询一条数据
     * @param map
     * @return
     */
    BillingReceiveInfo selectByOne(Map map);

    /**
     * 修改一条数据
     * @param billingReceiveInfo
     * @return
     */
    Integer updateOne(BillingReceiveInfo billingReceiveInfo);

    /**
     * 新增一条数据
     * @param billingReceiveInfo
     * @return
     */
    Integer insertOne(BillingReceiveInfo billingReceiveInfo);

    /**
     * 删除一条数据
     * @param id
     * @return
     */
    Integer deleteOne(Integer id);

    List<BillingReceiveInfo> selectByMatchIds(String matchIds);
}
