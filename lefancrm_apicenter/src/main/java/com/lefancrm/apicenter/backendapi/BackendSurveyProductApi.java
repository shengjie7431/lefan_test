package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by wangwei on 2019-01-15.
 */
public interface BackendSurveyProductApi {
    ApiResponse roleList(ApiRequest apiReq);

    ApiResponse levelList(ApiRequest apiReq);

}
