package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.StudioCommissionInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/3/26.
 */
public interface BackendStudioCommissionInfoApi {
    ApiResponse<List<StudioCommissionInfo>> getStudioCommissionInfoList(ApiRequest apiReq);

    ApiResponse<List<StudioCommissionInfo>> studioCommissionInfoEdit(ApiRequest apiReq);

    ApiResponse studioCommissionInfoSave(ApiRequest apiReq);

    ApiResponse studioCommissionInfoUpdate(ApiRequest apiReq);

    ApiResponse studioCommissionInfoDelete(ApiRequest apiReq);
}
