package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by wangwei on 2018/12/17.
 */
public interface BackendSurveyConsignerApi {
//    ApiResponse list(ApiRequest apiReq);
    ApiResponse commonFileList(ApiRequest apiReq);

    //机构下的委托人list
    ApiResponse listByOrg(ApiRequest apiReq);

}
