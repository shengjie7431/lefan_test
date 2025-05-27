package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyUserConsignor;

import java.util.List;
import java.util.Map;

public interface SurveyUserConsignorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyUserConsignor record);

    int insertSelective(SurveyUserConsignor record);

    SurveyUserConsignor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyUserConsignor record);

    int updateByPrimaryKey(SurveyUserConsignor record);

    //数据
    List<SurveyUserConsignor> list(Map map);
    int listSize(Map map);

    //根据人员条件删除
    int deleteByUserId(Map map);

    //根据机构条件删除
    int deleteByConsignorId(Map map);

    //查询当前人，配置的机构ids
    String selectConsignorIds(Long userId);
}