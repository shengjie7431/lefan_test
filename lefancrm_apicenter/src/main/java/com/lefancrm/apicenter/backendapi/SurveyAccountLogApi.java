package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.SurveyAccountLog;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;
import java.util.Map;

/**
 * 申请结算价格调整表
 * @author EDZ
 */
public interface SurveyAccountLogApi {
    int deleteByPrimaryKey(Long id);

    int insert(SurveyAccountLog record);

    int insertSelective(SurveyAccountLog record);

    SurveyAccountLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SurveyAccountLog record);

    int updateByPrimaryKey(SurveyAccountLog record);

    ApiResponse selectByList(ApiRequest apiReq);
}
