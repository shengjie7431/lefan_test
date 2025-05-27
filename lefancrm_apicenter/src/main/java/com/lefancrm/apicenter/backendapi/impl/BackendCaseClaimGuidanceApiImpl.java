package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCaseClaimGuidanceApi;
import com.lefancrm.apicenter.dao.CaseClaimGuidanceMapper;
import com.lefancrm.apicenter.model.CaseClaimGuidance;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangwei on 2019-05-08
 * 索赔指导信息
 */
@Service
@ApiService(descript = "索赔指导信息API")
public class BackendCaseClaimGuidanceApiImpl extends BaseServiceImpl implements BackendCaseClaimGuidanceApi {

    @Autowired
    private CaseClaimGuidanceMapper caseClaimGuidanceMapper;


    /**
     * 索赔指导信息
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "索赔指导信息", value = "backend-case-claim-guidance-by-caseid", apiParams = { })
    @Override
    public ApiResponse searchInfoByCaseId(ApiRequest apiReq) {
        Long caseCenterId = apiReq.getLong("caseCenterId");
        CaseClaimGuidance info = caseClaimGuidanceMapper.selectByCaseCenterId(caseCenterId);
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, info);
    }

}
