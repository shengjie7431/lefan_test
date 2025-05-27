package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.dto.LawFeeDetailDto;
import com.lefancrm.apicenter.model.LawFeeDetail;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/10/22.
 */
public interface BackendLawFeeDetailApi {

    /**
     * 查询案件列表
     * @param apiRequest
     * @return
     */
    ApiResponse<List<LawFeeDetailDto>> list(ApiRequest apiRequest);

    /**
     * 根据信息查询退费费用
     * @param apiRequest
     * @return
     */
    ApiResponse<LawFeeDetailDto> selectByInfo(ApiRequest apiRequest);

    /**
     * 退费信息修改
     * @param apiRequest
     * @return
     */
    ApiResponse<LawFeeDetail> upd(ApiRequest apiRequest);
}
