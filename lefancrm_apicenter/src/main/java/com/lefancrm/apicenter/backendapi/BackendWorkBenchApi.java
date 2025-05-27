package com.lefancrm.apicenter.backendapi;

/**
 * Created by DELL on 2017/12/28.
 */

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * 工作台
 */
public interface BackendWorkBenchApi {

    ApiResponse queryWorkBench(ApiRequest apiReq);

    ApiResponse queryWorkBenchByDate(ApiRequest apiReq);
}
