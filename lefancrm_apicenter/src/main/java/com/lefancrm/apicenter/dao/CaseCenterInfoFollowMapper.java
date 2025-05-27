package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.CaseCenterInfoFollow;

import java.util.List;
import java.util.Map;

public interface CaseCenterInfoFollowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CaseCenterInfoFollow record);

    int insertSelective(CaseCenterInfoFollow record);

    CaseCenterInfoFollow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CaseCenterInfoFollow record);

    int updateByPrimaryKey(CaseCenterInfoFollow record);

    /**
     * 案件跟踪列表
     *
     */
    List<CaseCenterInfoFollow> selectCaseCenterInfoFollowList(Map<String, Object> paramMap);

    /**
     * 根据‘案件编号’查询案件跟踪信息
     *
     */
    List<CaseCenterInfoFollow> searchCaseCenterInfoFollowByCaseId(Map<String, Object> paramMap);
    int searchCaseCenterInfoFollowSizeByCaseId(Map<String, Object> paramMap);

    int updateListByGradationStateAndCaseId(Map<String, Object> paramMap);


    CaseCenterInfoFollow searchCaseCenterInfoFollowByInfo(CaseCenterInfoFollow caseCenterInfoFollow);

}