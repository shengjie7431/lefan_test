package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.SurveyConsignor;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2018/12/17.
 */
public interface BackendSurveyConsignorApi {

    //非分页分页数据
    ApiResponse list(ApiRequest apiReq);

    ApiResponse info(ApiRequest apiReq);
    /**
     * 获取符合条件的委托人
     * @param apiReq
     * @return
     */
    ApiResponse selectConsigner(ApiRequest apiReq);

    ApiResponse selectConsignorListForFinalUser(ApiRequest apiReq);

    /**
     * 根据Map查询数据
     * @param map
     * @return
     */
    ApiResponse selectByMap(ApiRequest apiReq);

    ApiResponse selectSameFinalUser(ApiRequest apiReq);

    ApiResponse selectConsignorData(ApiRequest apiReq);

    ApiResponse userListByRole(ApiRequest apiReq);
}
