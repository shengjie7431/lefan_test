package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyCaseFileDto;
import com.lefancrm.apicenter.dto.SurveyFileInfoDTO;
import com.lefancrm.apicenter.model.SurveyCaseFile;

import java.util.List;
import java.util.Map;

public interface SurveyCaseFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyCaseFile record);

    int insertSelective(SurveyCaseFile record);

    SurveyCaseFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyCaseFile record);

    int updateByPrimaryKey(SurveyCaseFile record);

    List<SurveyCaseFileDto> list(Map map);

    List<SurveyFileInfoDTO> getSurveyFileInfo(Long surveyInfoId);
}