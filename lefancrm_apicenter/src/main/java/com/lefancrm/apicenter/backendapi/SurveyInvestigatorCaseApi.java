package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.interfaces.Api;

/**
 * Created by lixianfeng on 2018/12/18.
 */
public interface SurveyInvestigatorCaseApi {
    ApiResponse list(ApiRequest apiRequest);
    ApiResponse info(ApiRequest apiRequest);
    ApiResponse accept(ApiRequest apiRequest);
    ApiResponse refuse(ApiRequest apiRequest);
    ApiResponse uploadValidReport(ApiRequest apiRequest);
    ApiResponse uploadReport(ApiRequest apiRequest);
    ApiResponse addDirection(ApiRequest apiRequest);
    ApiResponse baiduContextError(ApiRequest apiRequest);
    ApiResponse infoDirection(ApiRequest apiRequest);
    ApiResponse commitReport(ApiRequest apiRequest);
    ApiResponse operate(ApiRequest apiRequest);

    ApiResponse surveyMoney(ApiRequest apiRequest);
    ApiResponse surveyMoneyDetail(ApiRequest apiRequest);

    ApiResponse surveyScore(ApiRequest apiRequest);
    ApiResponse surveyScoreDetail(ApiRequest apiRequest);
    ApiResponse primaryVetoGetData(ApiRequest apiRequest);
}
