package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by wangwei on 2018/8/24.
 */
public interface BackendReportFormApi {
    /**
     * 索赔报表
     */
    ApiResponse getCaseClaimReportList(ApiRequest apiReq);

    /**
     * 贷款报表
     */
    ApiResponse getCaseLoanReportList(ApiRequest apiReq);

    /**
     * 个人业务台账
     */
    ApiResponse getCasePersonalInfoList(ApiRequest apiReq);

    /**
     * 个人业务台账详情页面
     */
    ApiResponse getCasePersonalInfoView(ApiRequest apiReq);

    /**
     * 个人业务案件报表(日、月)
     */
    ApiResponse getCasePersonalReportList(ApiRequest apiReq);

    /**
     * 公估开票到账月报表
     */
    ApiResponse getCaseAssessmentkpdzMonthReportList(ApiRequest apiReq);

    /**
     * 个人业务开票到账月报表
     */
    ApiResponse getCasePerkpdzMonthReportList(ApiRequest apiReq);

    /**
     * 个人业务案件财务台账
     */
    ApiResponse getCasePersonalCwInfoList(ApiRequest apiReq);

    /**
     * 个人业务案件财务台账页面详情
     */
    ApiResponse getCasePersonalCwInfoView(ApiRequest apiReq);

}
