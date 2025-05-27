package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.ManagerComrateInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/3/26.
 */
public interface BackendManagerComrateInfoApi {
    ApiResponse<List<ManagerComrateInfo>> getManagerComrateInfoList(ApiRequest apiReq);

    ApiResponse<List<ManagerComrateInfo>> managerComrateInfoEdit(ApiRequest apiReq);

    ApiResponse managerComrateInfoSave(ApiRequest apiReq);

    ApiResponse managerComrateInfoUpdate(ApiRequest apiReq);

    ApiResponse managerComrateInfoDelete(ApiRequest apiReq);


}
