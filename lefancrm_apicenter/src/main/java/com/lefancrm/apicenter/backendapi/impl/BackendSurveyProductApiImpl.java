package com.lefancrm.apicenter.backendapi.impl;

import com.alibaba.fastjson.JSONArray;
import com.lefancrm.apicenter.backendapi.BackendSurveyLevelApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyProductApi;
import com.lefancrm.apicenter.dao.SurveyLevelMapper;
import com.lefancrm.apicenter.dao.SurveyProductLevelMapper;
import com.lefancrm.apicenter.dao.SurveyProductRoleMapper;
import com.lefancrm.apicenter.model.SurveyLevel;
import com.lefancrm.apicenter.model.SurveyProductLevel;
import com.lefancrm.apicenter.model.SurveyProductRole;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2019-01-15.
 * “商品”数据管理
 */
@Service
@ApiService(descript = "商品API")
public class BackendSurveyProductApiImpl extends BaseServiceImpl implements BackendSurveyProductApi {

    @Autowired
    private SurveyProductRoleMapper surveyProductRoleMapper;
    @Autowired
    private SurveyProductLevelMapper surveyProductLevelMapper;

    /**
     * 获取商品的角色list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "获取商品的角色list", value = "backend-survey-product-role-list", apiParams = { })
    @Override
    public ApiResponse roleList(ApiRequest apiReq) {
        //(非分页数据)
        Map<String,Object> map = new HashMap<>();
        map.put("productId",apiReq.getLong("productId"));
        List<SurveyProductRole> list = surveyProductRoleMapper.selectInfo(apiReq);
        return new ApiResponse<List<SurveyProductRole>>(ApiMsgEnum.SUCCESS, null, list);

    }

    /**
     * 获取商品的调查员等级list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "获取商品的调查员等级list", value = "backend-survey-product-level-list", apiParams = { })
    @Override
    public ApiResponse levelList(ApiRequest apiReq) {
        //(非分页数据)
        Map<String,Object> map = new HashMap<>();
        map.put("productId",apiReq.getLong("productId"));
        List<SurveyProductLevel> list = surveyProductLevelMapper.selectInfo(apiReq);
        return new ApiResponse<List<SurveyProductLevel>>(ApiMsgEnum.SUCCESS, null, list);

    }
}
