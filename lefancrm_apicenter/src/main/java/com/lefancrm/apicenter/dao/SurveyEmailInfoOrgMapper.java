package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyEmailInfoOrg;

import java.util.List;
import java.util.Map;

public interface SurveyEmailInfoOrgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyEmailInfoOrg record);

    int insertSelective(SurveyEmailInfoOrg record);

    SurveyEmailInfoOrg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyEmailInfoOrg record);

    int updateByPrimaryKey(SurveyEmailInfoOrg record);

    //数据
    List<SurveyEmailInfoOrg> list(Map map);
    int listSize(Map map);

    //根据条件删除
    int deleteByInfo(Map map);

    //根据consignorId查询数据  limit 1
    SurveyEmailInfoOrg selectByEntrustOrgId(Long entrustOrgId);
}