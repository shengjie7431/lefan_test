package com.lefancrm.apicenter.appapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by jun on 2017/12/15.
 */
public interface CrmAccidentInfoApi {


    ApiResponse queryAccidentDetails(ApiRequest apiReq);

    ApiResponse editAccidentInfo(ApiRequest apiReq);



}
