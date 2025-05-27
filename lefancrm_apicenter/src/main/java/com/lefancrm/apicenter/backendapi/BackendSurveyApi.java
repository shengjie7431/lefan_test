package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.interfaces.Api;

/**
 * Created by wangwei on 2018/12/19.
 */
public interface BackendSurveyApi {
    ApiResponse list(ApiRequest apiReq);
    ApiResponse edit(ApiRequest apiReq);
    ApiResponse update(ApiRequest apiReq);
    ApiResponse operate(ApiRequest apiReq);
    ApiResponse selectInfoByRelationId(ApiRequest apiReq);
    ApiResponse selectByOne(ApiRequest apiReq);
    ApiResponse selectArea(ApiRequest apiRequest);
    ApiResponse modelAreaDel(ApiRequest apiRequest);
}
