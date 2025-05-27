package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.PositionInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/3/21.
 */
public interface BackendPositionInfoApi {
    ApiResponse<List<PositionInfo>> getPositionInfoList(ApiRequest apiReq);

    ApiResponse<List<PositionInfo>> positionInfoEdit(ApiRequest apiReq);

    ApiResponse positionInfoSave(ApiRequest apiReq);

    ApiResponse positionInfoUpdate(ApiRequest apiReq);

    ApiResponse positionInfoDelete (ApiRequest apiReq);

    ApiResponse searchInfoByPositionId(ApiRequest apiReq);

    ApiResponse searchInfoByLevelId (ApiRequest apiReq);

}
