package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.SurveyTaskInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/12/17.
 */
public interface BackendSurveyTaskInfoApi {
    ApiResponse list(ApiRequest apiReq);
    ApiResponse listTest(ApiRequest apiReq);
}
