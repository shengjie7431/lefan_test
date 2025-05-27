package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by wangwei on 2020年10月28日14:08:06
 */
public interface BackendSurveyCaseArchivesApi {

    ApiResponse list(ApiRequest apiReq);
    ApiResponse operate(ApiRequest apiReq);
}
