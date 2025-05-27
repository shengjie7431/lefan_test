package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyFileCatalog;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;

public interface SurveyFileCatalogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyFileCatalog record);

    int insertSelective(SurveyFileCatalog record);

    SurveyFileCatalog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyFileCatalog record);

    int updateByPrimaryKey(SurveyFileCatalog record);

    List<SurveyFileCatalog> list(ApiRequest request);

    int listSize(ApiRequest request);
}