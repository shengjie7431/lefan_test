package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.CaseCenterInfo;
import com.lefancrm.apicenter.model.CommissionLog;
import com.lefancrm.apicenter.model.UserCommissionInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/3/22.
 */
public interface BackendUserCommissionInfoApi {
    ApiResponse<List<UserCommissionInfo>> getUserCommissionInfoList(ApiRequest apiReq);

    ApiResponse<List<CommissionLog>> searchCommissionLogList(ApiRequest apiReq);

    ApiResponse<List<CaseCenterInfo>> searchMonthNewSignList(ApiRequest apiReq);
}
