package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by jun on 2017/12/15.
 */
public interface BackendCrmInjuryInfoApi {


    ApiResponse queryInjuryInfo(ApiRequest apiReq);

    ApiResponse editInjuryInfo(ApiRequest apiReq);

}
