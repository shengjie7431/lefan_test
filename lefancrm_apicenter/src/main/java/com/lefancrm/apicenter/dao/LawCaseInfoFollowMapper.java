package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.LawCaseInfoFollow;

import java.util.List;
import java.util.Map;

public interface LawCaseInfoFollowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LawCaseInfoFollow record);

    int insertSelective(LawCaseInfoFollow record);

    LawCaseInfoFollow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LawCaseInfoFollow record);

    int updateByPrimaryKey(LawCaseInfoFollow record);

    /**
     * 根据‘案件编号’查询案件跟踪信息
     *
     */
    List<LawCaseInfoFollow> searchFollowByCaseId(Map<String, Object> paramMap);

}