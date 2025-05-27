package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by lixianfeng on 2019/3/13.
 */
public interface SurveyFileApi {
    ApiResponse convertTempZip(ApiRequest apiRequest);
    ApiResponse convertTempZipEntrust(ApiRequest apiRequest);
    ApiResponse convertTempZipExtensionTime(ApiRequest apiRequest);
    ApiResponse convertTempZipDirectionFile(ApiRequest apiRequest);

    ApiResponse downFile(ApiRequest apiRequest);
}
