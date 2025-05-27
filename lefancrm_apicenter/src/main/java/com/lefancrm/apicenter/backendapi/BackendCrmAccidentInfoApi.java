package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by jun on 2017/12/15.
 */
public interface BackendCrmAccidentInfoApi {


    ApiResponse queryAccidentDetails(ApiRequest apiReq);

    ApiResponse editAccidentInfo(ApiRequest apiReq);



}
