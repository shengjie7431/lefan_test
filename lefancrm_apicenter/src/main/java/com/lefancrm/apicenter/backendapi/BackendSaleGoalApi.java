package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by jun on 2018/1/4.
 */
public interface BackendSaleGoalApi {




    ApiResponse list(ApiRequest apiReq);

    ApiResponse saleGoalList(ApiRequest apiReq);

    ApiResponse updateSaleGoal(ApiRequest apiReq);

}
