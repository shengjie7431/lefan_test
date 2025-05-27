package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.BankInfo;
import com.lefancrm.apicenter.model.PositionInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/4/24.
 */
public interface BackendBankInfoApi {
    ApiResponse<List<BankInfo>> getBankInfoList(ApiRequest apiReq);


}
