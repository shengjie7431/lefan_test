package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by lixianfeng on 2018/11/22.
 */
public interface BackendInfoPublishsApi {
    ApiResponse list(ApiRequest apiRequest);
    ApiResponse save(ApiRequest apiRequest);
    ApiResponse saveForum(ApiRequest apiRequest);
    ApiResponse get(ApiRequest apiRequest);
    ApiResponse info(ApiRequest apiRequest);
    ApiResponse delete(ApiRequest apiRequest);
    ApiResponse fileShow(ApiRequest apiRequest);
    ApiResponse selectCompanyOK(ApiRequest apiRequest);
    ApiResponse companyListed(ApiRequest apiRequest);
    ApiResponse companyListing(ApiRequest apiRequest);
}
