package com.lefancrm.apicenter.dao;

import com.lefancrm.apicenter.dto.SurveyFranchiseePrice2Dto;
import com.lefancrm.apicenter.model.SurveyFranchiseePrice;
import com.lefancrm.base.dto.ApiRequest;

import java.util.List;
import java.util.Map;

public interface SurveyFranchiseePriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyFranchiseePrice record);

    int insertSelective(SurveyFranchiseePrice record);

    SurveyFranchiseePrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyFranchiseePrice record);

    int updateByPrimaryKey(SurveyFranchiseePrice record);

    //数据
    List<SurveyFranchiseePrice> list(Map map);
    int listSize(Map map);

    //单条数据
    SurveyFranchiseePrice selectInfo(Map<String,Object> map);

    //查询“调查方机构”的价格体系
    List<SurveyFranchiseePrice2Dto> selectListByFranchiseeId(Map map);

    //根据条件删除数据
    int deleteByInfo(Map map);

    List<SurveyFranchiseePrice2Dto> selectFranchiseePrice(Map map);
}