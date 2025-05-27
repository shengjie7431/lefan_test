package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by wangwei on 2019/01/12.
 */
public interface BackendSurveyFranchiseeApi {

    /**
     * 获取符合条件的调查人
     * @param apiReq
     * @return
     */
    ApiResponse selectInvestigator(ApiRequest apiReq);

    /**
     * 所有的狄大人角色
     * @param apiReq
     * @return
     */
    ApiResponse selectInvestigatorRoleList (ApiRequest apiReq);

    /**
     * “平台终审人员”对应的“调查方机构”
     * @param apiReq
     * @return
     */
    ApiResponse selectFranchiseeListForFinalUser(ApiRequest apiReq);

    /**
     * 根据条件查询调查方机构
     * @param apiReq
     * @return
     */
    ApiResponse selectMechanism(ApiRequest apiReq);

    /**
     * 查询调查机构数据  调查中  初审中
     * @param apiReq
     * @return
     */
    ApiResponse selectFranchiseeData(ApiRequest apiReq);
}
