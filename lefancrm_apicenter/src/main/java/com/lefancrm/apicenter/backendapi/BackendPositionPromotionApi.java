package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.PositionPromotion;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/3/24.
 */
public interface BackendPositionPromotionApi {
    ApiResponse<List<PositionPromotion>> getPositionPromotionList(ApiRequest apiReq);

    ApiResponse<List<PositionPromotion>> positionPromotionEdit(ApiRequest apiReq);

    ApiResponse positionPromotionSave(ApiRequest apiReq);

    ApiResponse positionPromotionUpdate(ApiRequest apiReq);

    ApiResponse positionPromotionDelete(ApiRequest apiReq);
}
