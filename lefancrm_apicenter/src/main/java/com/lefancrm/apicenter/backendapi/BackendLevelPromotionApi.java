package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.LevelPromotion;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/3/24.
 */
public interface BackendLevelPromotionApi {
    ApiResponse<List<LevelPromotion>> getLevelPromotionList(ApiRequest apiReq);

    ApiResponse<List<LevelPromotion>> levelPromotionEdit(ApiRequest apiReq);

    ApiResponse levelPromotionSave(ApiRequest apiReq);

    ApiResponse levelPromotionUpdate(ApiRequest apiReq);

    ApiResponse levelPromotionDelete(ApiRequest apiReq);
}
