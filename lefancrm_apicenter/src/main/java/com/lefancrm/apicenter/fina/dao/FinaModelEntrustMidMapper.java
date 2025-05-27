package com.lefancrm.apicenter.fina.dao;


import com.lefancrm.apicenter.fina.model.FinaModelEntrustMid;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinaModelEntrustMidMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByModelId(Long id);

    int deleteByEntrustOrgId(Long id);

    int insert(FinaModelEntrustMid record);

    int insertSelective(FinaModelEntrustMid record);

    FinaModelEntrustMid selectByPrimaryKey(Long id);

    List<FinaModelEntrustMid> selectByMtypeAndOrgId(@Param("mType") Integer type, @Param("orgId") Long id);

    List<FinaModelEntrustMid> selectListByModelId(Long modelId);

    int updateByPrimaryKeySelective(FinaModelEntrustMid record);

    int updateByPrimaryKey(FinaModelEntrustMid record);
}