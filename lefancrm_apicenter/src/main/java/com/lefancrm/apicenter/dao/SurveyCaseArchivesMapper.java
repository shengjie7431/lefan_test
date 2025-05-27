package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyRiskCaseInfoDto;
import com.lefancrm.apicenter.model.SurveyCaseArchives;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyCaseArchivesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyCaseArchives record);

    int insertSelective(SurveyCaseArchives record);

    SurveyCaseArchives selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyCaseArchives record);

    int updateByPrimaryKey(SurveyCaseArchives record);

    List<SurveyCaseArchives> list(ApiRequest apiRequest);
    int listSize(ApiRequest apiRequest);

    SurveyCaseArchives selectOne(Map map);

    //代理保司终审 -- 保司案件批量处理
    int insertByBsCaseList(Map<String,Object> paramMap);

    List<SurveyCaseArchives> selectArchives(ApiRequest apiRequest);
    int selectArchivesSize(ApiRequest apiRequest);
}