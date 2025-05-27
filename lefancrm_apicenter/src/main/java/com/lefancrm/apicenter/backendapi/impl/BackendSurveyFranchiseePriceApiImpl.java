package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyFranchiseePriceApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyFranchiseePriceApi;
import com.lefancrm.apicenter.dao.SurveyFranchiseePriceMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.SurveyFranchiseePrice;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2018/12/17.
 * 调查调查方价格
 */
@Service
@ApiService(descript = "调查调查方价格API")
public class BackendSurveyFranchiseePriceApiImpl extends BaseServiceImpl implements BackendSurveyFranchiseePriceApi {

    @Autowired
    private SurveyFranchiseePriceMapper surveyFranchiseePriceMapper;

    /**
     * 调查调查方价格(单条数据)
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "调查调查方价格(单条数据)", value = "backend-survey-franchisee-price-info", apiParams = { })
    @Override
    public ApiResponse info(ApiRequest apiReq) {

        Map<String,Object> map = new HashMap<>();
        SurveyFranchiseePrice info = surveyFranchiseePriceMapper.selectInfo(map);
        return new ApiResponse<SurveyFranchiseePrice>(ApiMsgEnum.SUCCESS, null, info);

    }
}
