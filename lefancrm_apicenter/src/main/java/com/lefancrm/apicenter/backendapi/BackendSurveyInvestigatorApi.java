package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by wangwei on 2018/12/19.
 */
public interface BackendSurveyInvestigatorApi {
    ApiResponse list(ApiRequest apiReq);
    ApiResponse operate(ApiRequest apiReq);
    ApiResponse listByOrg(ApiRequest apiReq);

    /**
     * 增加乐凡币 或 成就点
     * @param surveyUserId  调查员userId
     * @param num   增加数量
     * @param type  增加类型(1乐凡币   2成就点)
     * @param remark 增加描述
     * @param oprId     业务id
     * @param oprType   业务类型(1.认证，2.业务案件，3.论坛.4.案件评星，5.兑换)
     * @return
     */
    void addPrice(Long surveyUserId,Long num,int type,String remark,Long oprId,int oprType);
}
