package com.lefancrm.apicenter.dao;
import com.lefancrm.apicenter.dto.SurveyCostApplyDto;
import com.lefancrm.apicenter.model.SurveyClockReInfo;

import java.util.List;

public interface SurveyClockReInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyClockReInfo clockReInfo);

    int insertSelective(SurveyClockReInfo clockReInfo);

    SurveyClockReInfo selectByPrimaryKey(Long id);

    double selectTotalMoneyById(Long id);

    int updateByPrimaryKeySelective(SurveyClockReInfo clockReInfo);

    int updateByPrimaryKey(SurveyClockReInfo clockReInfo);

    SurveyClockReInfo selectByClockId(Long id);

    List<SurveyCostApplyDto> selectCostApplyByClockId(Long clockId);
}