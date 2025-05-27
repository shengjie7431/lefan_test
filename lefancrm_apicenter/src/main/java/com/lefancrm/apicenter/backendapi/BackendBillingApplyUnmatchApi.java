package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;


/**
 * Created by wangwei on 2018/12/24.
 */
public interface BackendBillingApplyUnmatchApi {

    /**
     * 未匹配收款列表
     * @param apiRequest
     * @return
     */
    ApiResponse list(ApiRequest apiRequest);

    /**
     * 未匹配收款 -详情
     * @param apiRequest
     * @return
     */
    ApiResponse info(ApiRequest apiRequest);

    /**
     * 未匹配收款 -保存
     * @param apiRequest
     * @return
     */
    ApiResponse update(ApiRequest apiRequest);

    /**
     * 未匹配收款 -删除
     * @param apiRequest
     * @return
     */
    ApiResponse delete(ApiRequest apiRequest);

    /**
     * 未匹配收款 -生成编号
     * @param apiRequest
     * @return
     */
    ApiResponse buildNo(ApiRequest apiRequest);

    /**
     * 未匹配收款 -开票清单
     * @param apiRequest
     * @return
     */
    ApiResponse billingList(ApiRequest apiRequest);

    /**
     * 确认认领--已开票认领
     * @param apiRequest
     * @return
     */
    ApiResponse claim(ApiRequest apiRequest);


    ApiResponse annualTableList(ApiRequest apiRequest);
}
