package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.dto.CrmCustomerFollowsDto;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by ting on 2017/12/19.
 */
public interface BackendCrmCustomerFollowsApi {


    ApiResponse queryCustomerFollows(ApiRequest apiReq);

    ApiResponse addCustomerFollows(ApiRequest apiReq);

    /**待办事项数量统计*/
    ApiResponse<Map<String, Object>> getCrmCustomerFollowsCount(ApiRequest apiReq);
    /**今日待跟进客户列表*/
    ApiResponse<List<CrmCustomerFollowsDto>> getWaitCrmCustomerFollowsList(ApiRequest apiReq);
    /**超时未跟进客户列表*/
    ApiResponse<List<CrmCustomerFollowsDto>> getNoCrmCustomerFollowsList(ApiRequest apiReq);
    /**已经搁置和已放弃客户列表*/
    ApiResponse<List<CrmCustomerFollowsDto>> getAlreadyCrmCustomerFollowsList(ApiRequest apiReq);
    /**已经放弃客户列表*/
  /*  ApiResponse<List<CrmCustomerFollowsDto>> getGiveupCrmCustomerFollowsList(ApiRequest apiReq);*/
}
