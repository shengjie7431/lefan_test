package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.BillingApplyCompany;
import com.lefancrm.apicenter.model.BillingApplyCorporation;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/10/9.
 */
public interface BackendBillingApplyCompanyApi {

    /**
     * 开票对象列表
     * @param apiRequest
     * @return
     */
    ApiResponse<List<BillingApplyCompany>> billingApplyCompanyList(ApiRequest apiRequest);

    /**
     * 开票公司列表
     * @param apiRequest
     * @return
     */
    ApiResponse<List<BillingApplyCorporation>> billingApplyCorporationList(ApiRequest apiRequest);

    /**
     * 公司对应类目list
     * @param apiRequest
     * @return
     */
    ApiResponse billingApplyCorporationEnumList(ApiRequest apiRequest);

    /**
     * 类目对应项目list
     * @param apiRequest
     * @return
     */
    ApiResponse billingApplyEnumItemList(ApiRequest apiRequest);

    /**
     * 开票公司详情
     * @param apiReq
     * @return
     */
    ApiResponse<BillingApplyCorporation> billingApplyCorporationDetails(ApiRequest apiReq);

}
