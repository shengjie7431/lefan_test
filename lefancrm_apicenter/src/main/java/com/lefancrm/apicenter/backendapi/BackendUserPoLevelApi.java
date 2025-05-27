package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.dto.UserPoLevelDto;
import com.lefancrm.apicenter.model.UserPoLevel;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/3/21.
 */
public interface BackendUserPoLevelApi {
    ApiResponse<List<UserPoLevelDto>> getUserPoLevelList(ApiRequest apiReq);

    ApiResponse<List<UserPoLevelDto>> userPoLevelEdit(ApiRequest apiReq);

    ApiResponse userPoLevelSave(ApiRequest apiReq);

    ApiResponse userPoLevelUpdate(ApiRequest apiReq);

    ApiResponse userPoLevelDelete(ApiRequest apiReq);
}
