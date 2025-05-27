package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyConsigner;
import com.lefancrm.base.dto.ApiRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SurveyConsignerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyConsigner record);

    int insertSelective(SurveyConsigner record);

    SurveyConsigner selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyConsigner record);

    int updateByPrimaryKey(SurveyConsigner record);

    //数据
    List<SurveyConsigner> list(Map map);
    int listSize(Map map);

    SurveyConsigner selectByUserId(Long userId);

    List<SurveyConsigner> selectSurveyConsignerByDepartmentId(@Param("departmentId") Long departmentId);

}