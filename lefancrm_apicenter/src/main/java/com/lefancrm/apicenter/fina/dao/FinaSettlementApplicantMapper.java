package com.lefancrm.apicenter.fina.dao;

import com.lefancrm.apicenter.fina.model.FinaSettlementApplicant;

import java.util.List;
import java.util.Map;

public interface FinaSettlementApplicantMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinaSettlementApplicant record);

    int insertSelective(FinaSettlementApplicant record);

    FinaSettlementApplicant selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinaSettlementApplicant record);

    int updateByPrimaryKey(FinaSettlementApplicant record);

    void generate(Map<String,Object> paramMap);

    /**
     * 根据结算单ID 更新案件类型
     * @param paramMap
     * @return
     */
    int updCaseType(Map<String,Object> paramMap);

    /**
     * 根据结算单ID 更新案件状态及相关数据
     * @param paramMap
     * @return
     */
    int updCaseData(Map<String,Object> paramMap);

    List<FinaSettlementApplicant> list(Map<String,Object> paramMap);

    Long selectSettlementIdByApplicantId(Long finaInfoId);
}