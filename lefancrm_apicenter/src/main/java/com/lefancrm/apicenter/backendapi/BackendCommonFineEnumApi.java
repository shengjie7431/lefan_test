package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.CaseCenterInfoFined;
import com.lefancrm.apicenter.model.CommonFineEnum;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/5/30.
 */
public interface BackendCommonFineEnumApi {
    /**
     * 扣罚清单列表
     * @param apiRequest
     * @return
     */
    ApiResponse<List<CommonFineEnum>> commonFineEnumList(ApiRequest apiRequest);

    /**
     * 根据id查询扣罚类目
     * @param
     * @return
     */
    ApiResponse<CommonFineEnum> seachInfoByFinedType(ApiRequest apiRequest);
}
