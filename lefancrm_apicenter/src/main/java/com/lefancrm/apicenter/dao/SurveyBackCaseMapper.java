package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyBackCaseDto;
import com.lefancrm.apicenter.model.SurveyBackCase;

import java.util.List;
import java.util.Map;

public interface SurveyBackCaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyBackCase record);

    int insertSelective(SurveyBackCase record);

    SurveyBackCaseDto selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyBackCase record);

    int updateByPrimaryKey(SurveyBackCase record);

    List<SurveyBackCaseDto> list(Map map);

    int listSize(Map map);
}