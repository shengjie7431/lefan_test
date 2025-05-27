package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyConsignor;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyConsignorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyConsignor record);

    int insertSelective(SurveyConsignor record);

    SurveyConsignor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyConsignor record);

    int updateByPrimaryKey(SurveyConsignor record);

    //数据
    List<SurveyConsignor> listPage(Map map);
    int listPageSize(Map map);
    List<SurveyConsignor> list(Map map);
    int listSize(Map map);

    SurveyConsignor selectByName(String name);

    //“平台终审人员”对应的“委托机构”
    List<SurveyConsignor> selectConsignorListForFinalUser(Map map);
    List<SurveyConsignor> selectConsignorListForFinalUserNew(Map map);

    //非分页数据 -- 仅返回数个字段
    List<SurveyConsignor> selectInfo(Map map);

    /**
     * 根据Map查询数据
     * @param map
     * @return
     */
    List<SurveyConsignor> selectByMap(Map map);

    List<SurveyConsignor> selectConsignorDataByParam(Map<String,Object> map);
}