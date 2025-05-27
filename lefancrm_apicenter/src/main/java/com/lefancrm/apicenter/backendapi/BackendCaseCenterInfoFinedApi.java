package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.CaseCenterInfo;
import com.lefancrm.apicenter.model.CaseCenterInfoFined;
import com.lefancrm.apicenter.model.CaseCenterInfoFollow;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/5/17.
 */
public interface BackendCaseCenterInfoFinedApi {
    /**
     * 扣罚案件清单列表
     * @param apiRequest
     * @return
     */
    ApiResponse<List<CaseCenterInfoFined>> caseCenterInfoFinedList(ApiRequest apiRequest);

    /**
     * 保存扣罚案件
     * @param apiRequest
     * @return
     */
    ApiResponse<CaseCenterInfoFined> caseCenterInfoFinedSave(ApiRequest apiRequest);

    /**
     * 根据“caseNo”查询扣罚记录详情
     * @param apiRequest
     * @return
     */
    ApiResponse<CaseCenterInfoFined> selectCaseCenterInfoFinedByCaseNo(ApiRequest apiRequest);

}
