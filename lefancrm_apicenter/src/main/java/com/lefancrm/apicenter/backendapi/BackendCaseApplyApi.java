package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.dto.CaseApplyDto;
import com.lefancrm.apicenter.model.CaseApply;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/5/14.
 */
public interface BackendCaseApplyApi {
    /**
     * 报案中心列表
     * @param apiRequest
     * @return
     */
    ApiResponse<List<CaseApplyDto>> caseApplyList(ApiRequest apiRequest);

    /**
     * 保存新案件
     * @param apiReq
     * @return
     */
    ApiResponse caseApplySave(ApiRequest apiReq);

    ApiResponse<CaseApply> searchCaseApplyById(ApiRequest apiRequest);

    /**
     * 报案申请提交转办
     * @param apiReq
     * @return
     */
    ApiResponse caseApplyForwardSubmit(ApiRequest apiReq);

    /**
     *修改报案状态为已处理
     * @param apiReq
     * @return
     */
    ApiResponse caseApplyToAlready(ApiRequest apiReq);
}
