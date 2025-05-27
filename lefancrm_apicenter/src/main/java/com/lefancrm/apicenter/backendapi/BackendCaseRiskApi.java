package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.PaymentEstimateReport;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * 风控案件中心
 * Created by wanjun on 2017-04-26.
 */
public interface BackendCaseRiskApi {

    @SuppressWarnings("rawtypes")
    ApiResponse caseRiskList(ApiRequest apiReq);

    @SuppressWarnings("rawtypes")
    ApiResponse editRiskState(ApiRequest apiReq);

    @SuppressWarnings("rawtypes")
    ApiResponse queryCaseClaim(ApiRequest apiReq);

    @SuppressWarnings("rawtypes")
    ApiResponse queryCaseClaimReport(ApiRequest apiReq);

    @SuppressWarnings("rawtypes")
    ApiResponse paymentEstimateReportEdit(ApiRequest apiReq);

    @SuppressWarnings("rawtypes")
    ApiResponse queryCaseNegotiateState(ApiRequest apiReq);

    ApiResponse addCaseRiskControl(ApiRequest apiReq);

    ApiResponse updCaseRiskControl(ApiRequest apiReq);

    ApiResponse getCaseRiskControl(ApiRequest apiReq);

    ApiResponse selectPaymentEstimateApplysHisory(ApiRequest apiReq);

    ApiResponse<List<PaymentEstimateReport>> getPaymentEstimateReportList(ApiRequest apiReq);

    ApiResponse caseNegotiateState(ApiRequest apiReq);

    ApiResponse issuanceState(ApiRequest apiReq);

    ApiResponse createCaseRiskPDF(ApiRequest apiReq);

    ApiResponse selectPaymentEstimateApplyNew(ApiRequest apiReq);

    ApiResponse surveyRiskRepetition(ApiRequest apiRequest);
}
