package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.io.IOException;

/**
 * Created by jun on 2018/1/4.
 */
public interface BackendCrmCaseInfoApi {

    ApiResponse queryCaseInfoDetails(ApiRequest apiReq);

    ApiResponse editCaseInfo(ApiRequest apiReq) throws IOException;
}
