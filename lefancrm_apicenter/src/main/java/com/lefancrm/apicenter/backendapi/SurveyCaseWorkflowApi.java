package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.SurveyCaseWorkflow;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.Date;

/**
 * Created by lixianfeng on 2018/12/20.
 */
public interface SurveyCaseWorkflowApi {
    public int addSurveyCaseWorkflow(String stepName, UserInfo userInfo, Date startTime, Date endTime, Long surveyId, Long surveyInfoId);

    ApiResponse list(ApiRequest apiRequest);
}
