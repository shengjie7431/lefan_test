package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.CaseCenterInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * 案件中心
 * Created by wanjun on 2017-04-26.
 */
public interface BackendCaseInfoApi {

  /**
     * 添加案件中心数据
     * @param apiReq
     * @return
     */
    CaseCenterInfo addCaseCenterInfo(ApiRequest apiReq);


}
