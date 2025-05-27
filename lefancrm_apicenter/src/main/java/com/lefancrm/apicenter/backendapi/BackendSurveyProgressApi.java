package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by lixianfeng on 2019/1/23.
 */
public interface BackendSurveyProgressApi {
    ApiResponse list(ApiRequest apiRequest);
    public ApiResponse addprogress(ApiRequest apiRequest);
}
