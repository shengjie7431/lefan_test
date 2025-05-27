package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseCenterInfoFined;
import com.lefancrm.apicenter.model.CaseCenterInfoFollow;

import java.util.List;
import java.util.Map;

public interface CaseCenterInfoFinedMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseCenterInfoFined record);

    int insertSelective(CaseCenterInfoFined record);

    CaseCenterInfoFined selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseCenterInfoFined record);

    int updateByPrimaryKey(CaseCenterInfoFined record);

    /**
     * 扣罚案件清单列表
     *
     */
    List<CaseCenterInfoFined> selectCaseCenterInfoFinedList(Map<String, Object> paramMap);
    int selectCaseCenterInfoFinedListSize(Map<String, Object> paramMap);

    /**
     * 根据“caseNo”查询扣罚记录详情
     *
     */
    CaseCenterInfoFined selectCaseCenterInfoFinedByCaseNo(Map<String, Object> paramMap);
}