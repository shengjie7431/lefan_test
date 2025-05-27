package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.dto.CaseApplicationInfoDto;
import com.lefancrm.apicenter.model.AgentApply;
import com.lefancrm.apicenter.model.LoanApplication;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/5/14.
 */
public interface BackendCaseApplicationInfoApi {
    /**
     * 各种案件列表
     * @param apiRequest
     * @return
     */
    ApiResponse<List<CaseApplicationInfoDto>> caseApplicationInfoList(ApiRequest apiRequest);

    /**
     * 通过‘案件编号’查询代理申请数据
     * @param apiRequest
     * @return
     */
    ApiResponse<AgentApply> searchAgentApplyByAgentNo(ApiRequest apiRequest);

    /**
     * 通过‘贷款申请编号’查询贷款申请数据
     * @param apiRequest
     * @return
     */
    ApiResponse<LoanApplication> searchLoanApplicationByLoanNo(ApiRequest apiRequest);

    /**
     * 代理申请案件变更状态
     * @param apiRequest
     * @return
     */
    ApiResponse editAgentApplyInfoState(ApiRequest apiRequest);

    /**
     * 贷款申请案件变更状态
     * @param apiRequest
     * @return
     */
    ApiResponse editLoanApplicationInfoState(ApiRequest apiRequest);
}
