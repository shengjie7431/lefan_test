package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by lixianfeng on 2018/12/18.
 */
public interface SurveyRiskCaseInfoApi {
    ApiResponse list(ApiRequest apiRequest);
    ApiResponse updBatchKp(ApiRequest apiRequest);
    ApiResponse updEntrustMoney(ApiRequest apiRequest);
    ApiResponse info(ApiRequest apiRequest);
    ApiResponse operate(ApiRequest apiRequest);
    ApiResponse follows(ApiRequest apiRequest);
    ApiResponse addFollow(ApiRequest apiRequest);
    ApiResponse assignation(ApiRequest apiRequest);
    ApiResponse billingList(ApiRequest apiRequest);//批量开票list
    ApiResponse dispatcherView(ApiRequest apiRequest);
    ApiResponse listToExport(ApiRequest apiRequest);

    ApiResponse getTemplateData(ApiRequest apiRequest);

    ApiResponse selectTaskByUserId(ApiRequest apiRequest);

    ApiResponse getOprInfo(ApiRequest apiRequest);

    ApiResponse saveOprInfo(ApiRequest apiRequest);

    ApiResponse getGuideInfo(ApiRequest apiRequest);
    ApiResponse getVisitInfo(ApiRequest apiRequest);

    ApiResponse xhbBatchList(ApiRequest apiRequest);
    ApiResponse xhbBatchListOperator(ApiRequest apiRequest);

    ApiResponse getZaAgingDay(ApiRequest apiRequest);

    /**
     * 获取保司审核 邮箱等相关信息
     * @param apiRequest
     * @return
     */
    ApiResponse getEntrustEndInfo(ApiRequest apiRequest);

//    ApiResponse entrustSurveyRiskCaseApi(ApiRequest apiRequest);
//    ApiResponse assignSurveyInv(ApiRequest apiRequest);
//    ApiResponse addDirections(ApiRequest apiRequest);
    ApiResponse commitReport(ApiRequest apiRequest);

    ApiResponse asyncNwRisk(ApiRequest apiRequest);

    ApiResponse xhbCase(ApiRequest apiRequest);
}
