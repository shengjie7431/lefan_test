package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by lixianfeng on 2018/5/2.
 */
public interface BackendCaseMediationClaimApi {
    ApiResponse addCaseMediationClaim(ApiRequest apiReq);
    ApiResponse updCaseMediationClaim(ApiRequest apiReq);
    ApiResponse getCaseMediationClaim(ApiRequest apiReq);

    ApiResponse updCaseMediationClaimReport(ApiRequest apiReq);
    ApiResponse getCaseMediationClaimReport(ApiRequest apiReq);
}
