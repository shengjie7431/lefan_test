package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.dto.PositionLevelDto;
import com.lefancrm.apicenter.model.PositionLevel;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/3/21.
 */
public interface BackendPositionLevelApi {
    ApiResponse<List<PositionLevelDto>> getPositionLevelList(ApiRequest apiReq);

    ApiResponse<List<PositionLevelDto>> positionLevelEdit(ApiRequest apiReq);

    ApiResponse positionLevelSave(ApiRequest apiReq);

    ApiResponse positionLevelUpdate(ApiRequest apiReq);

    ApiResponse positionLevelDelete(ApiRequest apiReq);
}
