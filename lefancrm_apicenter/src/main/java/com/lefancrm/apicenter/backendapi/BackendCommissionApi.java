package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.OrgInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * 佣金计算
 * 
 * @author Daniel
 */
public interface BackendCommissionApi {

    /**
     * 佣金计算
     * @param apiReq
     * @return
     */
    ApiResponse commissionCalculation(ApiRequest apiReq);

}
