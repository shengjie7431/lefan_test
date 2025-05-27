package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.dto.SurveyCaseDirectionDto;
import com.lefancrm.apicenter.dto.SurveyRegionalDistributionCasesDto;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2019/04/04.
 */
public interface BackendSurveyCaseDirectionApi {
    ApiResponse list(ApiRequest apiRequest);
    ApiResponse directionInfo(ApiRequest apiRequest);

    ApiResponse info(ApiRequest apiRequest);
    ApiResponse listToExport(ApiRequest apiRequest);


    /**
     * 互助案件区域分布报表详情查询接口
     * @param apiRequest
     * @return
     */
    ApiResponse detailsList(ApiRequest apiRequest);
}
