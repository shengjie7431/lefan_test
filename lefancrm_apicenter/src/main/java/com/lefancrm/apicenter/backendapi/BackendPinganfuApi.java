package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by lixianfeng on 2018/6/26.
 */
public interface BackendPinganfuApi {
    ApiResponse getRegionAccount(ApiRequest request);
    ApiResponse regionAccount(ApiRequest request);
    ApiResponse getBindCard(ApiRequest request);
    ApiResponse bindCard(ApiRequest request);
    ApiResponse bindCardResult(ApiRequest request);
    ApiResponse getAuthCard(ApiRequest request);
    ApiResponse authCard(ApiRequest request);
    ApiResponse buildAuthCard(ApiRequest request);
}
