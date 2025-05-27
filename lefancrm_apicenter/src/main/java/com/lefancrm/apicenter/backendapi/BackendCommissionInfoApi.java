package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.CommissionInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/3/26.
 */
public interface BackendCommissionInfoApi {
    ApiResponse<List<CommissionInfo>> getCommissionInfoList(ApiRequest apiReq);

    ApiResponse<List<CommissionInfo>> commissionInfoEdit(ApiRequest apiReq);

    ApiResponse commissionInfoSave(ApiRequest apiReq);

    ApiResponse commissionInfoUpdate(ApiRequest apiReq);

    ApiResponse commissionInfoDelete(ApiRequest apiReq);
}
