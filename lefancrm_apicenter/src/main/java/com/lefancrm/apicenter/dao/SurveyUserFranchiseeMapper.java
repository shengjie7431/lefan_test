package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.model.SurveyUserFranchisee;

import java.util.List;
import java.util.Map;

public interface SurveyUserFranchiseeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyUserFranchisee record);

    int insertSelective(SurveyUserFranchisee record);

    SurveyUserFranchisee selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyUserFranchisee record);

    int updateByPrimaryKey(SurveyUserFranchisee record);

    //数据
    List<SurveyUserFranchisee> list(Map map);
    int listSize(Map map);

    //根据人员条件删除
    int deleteByUserId(Map map);
    //根据机构条件删除
    int deleteByFranchiseeId(Map map);
}