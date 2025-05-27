package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.dto.CaseCenterInfoAllotDto;
import com.lefancrm.apicenter.model.CaseCenterInfo;
import com.lefancrm.apicenter.model.CaseCenterInfoFollow;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/5/15.
 */
public interface BackendCaseCenterInfoAllotApi {
    /**
     * 跟踪信息列表
     * @param apiRequest
     * @return
     */
    ApiResponse<List<CaseCenterInfoAllotDto>> caseCenterInfoAllotList(ApiRequest apiRequest);

    /**
     * 案件分配-选定经办人员
     * @param
     * @return
     */
    ApiResponse<List<UserInfo>> selectUserInfoForAllot(ApiRequest apiRequest);

    /**
     * 保存被分配的案件
     * @param
     * @return
     */
    ApiResponse caseCenterInfoAllotSave(ApiRequest apiRequest);

    /**
     * 案件分配--案件详情
     * @param
     * @return
     */
    ApiResponse searchCaseCenterInfoForAllotById(ApiRequest apiRequest);

    /**
     * 修改业务员-选定业务员list
     * @param
     * @return
     */
    ApiResponse<List<UserInfo>> selectUserInfoByOrgId(ApiRequest apiRequest);
    /**
     * 修改业务员
     * @param
     * @return
     */
    ApiResponse updateOperator(ApiRequest apiRequest);

}
