package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.QualifiedManpower;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/3/26.
 */
public interface BackendQualifiedManpowerApi {
    ApiResponse<List<QualifiedManpower>> getQualifiedManpowerList(ApiRequest apiReq);

    ApiResponse<List<QualifiedManpower>> qualifiedManpowerEdit(ApiRequest apiReq);

    ApiResponse qualifiedManpowerSave(ApiRequest apiReq);

    ApiResponse qualifiedManpowerUpdate(ApiRequest apiReq);

    ApiResponse qualifiedManpowerDelete(ApiRequest apiReq);
}
