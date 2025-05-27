package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyProductDto;
import com.lefancrm.apicenter.model.SurveyProduct;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyProduct record);

    int insertSelective(SurveyProduct record);

    SurveyProductDto selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyProduct record);

    int updateByPrimaryKeyWithBLOBs(SurveyProduct record);

    int updateByPrimaryKey(SurveyProduct record);

    //数据
    List<SurveyProduct> list(Map map);
    int listSize(Map map);
}