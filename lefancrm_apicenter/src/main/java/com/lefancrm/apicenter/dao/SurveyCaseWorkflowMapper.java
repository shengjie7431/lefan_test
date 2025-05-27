package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyRiskCaseInfoDto;
import com.lefancrm.apicenter.model.SurveyCaseWorkflow;

import java.util.List;
import java.util.Map;

public interface SurveyCaseWorkflowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyCaseWorkflow record);

    int insertSelective(SurveyCaseWorkflow record);

    SurveyCaseWorkflow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyCaseWorkflow record);

    int updateByPrimaryKey(SurveyCaseWorkflow record);

    List<SurveyCaseWorkflow> list(Map map);

    int listSize(Map map);

    //代理保司终审 -- 保司案件批量处理
    int insertByBsCaseList(Map<String,Object> paramMap);
}