package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendPromotionOutlayApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * Created by wangwei on 2018/6/21.
 */
@ApiService(descript = "推广费用记录列表API")
@Service
public class BackendPromotionOutlayApiImpl extends BaseServiceImpl implements BackendPromotionOutlayApi {


    @Autowired
    private PromotionOutlayMapper promotionOutlayMapper;

    /**
     * 根据“caseNo”查询推广费用记录表
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "根据“caseNo”查询推广费用记录表" ,value = "backend-promotion-outlay-by-caseNo")
    @Override
    public ApiResponse<List<PromotionOutlay>> searchLoanApplicationByLoanNo(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        List<PromotionOutlay> promotionOutlay = promotionOutlayMapper.selectByCaseNo(apiReq.getString("caseNo"));
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, promotionOutlay);
    }
}
