package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.interfaces.Api;

import java.io.IOException;

/**
 * Created by jun on 2017/12/29.
 */
public interface BackendCrmCustomerInfoApi {



    ApiResponse list(ApiRequest apiReq);

    ApiResponse customerPanorama(ApiRequest apiReq);

    ApiResponse caseInformation(ApiRequest apiReq);

    ApiResponse injuredInformation(ApiRequest apiReq);

    ApiResponse injuryInformation(ApiRequest apiReq);

    ApiResponse accidentInformation(ApiRequest apiReq);

    ApiResponse caseInformationSave(ApiRequest apiReq);

    ApiResponse editCustomerInfo(ApiRequest apiReq);

    ApiResponse addCustomerInfo(ApiRequest apiReq);

    ApiResponse readExcel(ApiRequest apiReq) throws IOException;
}
