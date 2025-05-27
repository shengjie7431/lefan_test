package com.lefancrm.apicenter.appapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.io.IOException;

/**
 * Created by jun on 2017/12/15.
 */
public interface CrmCaseInfoApi {


    ApiResponse queryCaseInfoDetails(ApiRequest apiReq);

    ApiResponse editCaseInfo(ApiRequest apiReq) throws IOException;

}
