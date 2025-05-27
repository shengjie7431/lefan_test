package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.AgentApply;

import java.util.Map;

public interface AgentApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgentApply record);

    int insertSelective(AgentApply record);

    AgentApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AgentApply record);

    int updateByPrimaryKey(AgentApply record);

    AgentApply searchAgentApplyByAgentNo(Map<String, Object> paramMap);

    AgentApply selectAgentApplyByParam(Map<String, Object> paramMap);
}