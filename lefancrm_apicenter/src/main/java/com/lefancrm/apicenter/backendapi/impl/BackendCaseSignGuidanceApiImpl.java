package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCaseSignGuidanceApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.SerialNumberUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2019-05-08
 * 签约指导信息
 */
@Service
@ApiService(descript = "签约指导信息API")
public class BackendCaseSignGuidanceApiImpl extends BaseServiceImpl implements BackendCaseSignGuidanceApi {

    @Autowired
    private CaseSignGuidanceMapper caseSignGuidanceMapper;


    /**
     * 签约指导信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "签约指导信息", value = "backend-case-sign-guidance-by-caseid", apiParams = { })
    @Override
    public ApiResponse searchInfoByCaseId(ApiRequest apiReq) {
        this.setBackendPageSize(apiReq);
        CaseSignGuidance info = caseSignGuidanceMapper.searchInfoByCaseId(apiReq);
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, info);
    }

}
