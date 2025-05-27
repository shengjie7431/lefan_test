package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendCaseEstimateInfoApi;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.CaseEstimateInfoDto;
import com.lefancrm.apicenter.model.InvalidismEstimate;
import com.lefancrm.apicenter.model.PaymentEstimateApply;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Created by wangwei on 2018/5/30.
 */
@ApiService(descript = "伤残测算列表API")
@Service
public class BackendCaseEstimateInfoApiImpl extends BaseServiceImpl implements BackendCaseEstimateInfoApi {

    @Autowired
    private CaseEstimateInfoMapper caseEstimateInfoMapper;

    @Autowired
    private InvalidismEstimateMapper invalidismEstimateMapper;

    @Autowired
    private PaymentEstimateApplyMapper paymentEstimateApplyMapper;
    /**
     * 伤残测算列表
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "伤残测算列表" ,value = "backend-case-estimate-info-list")
    @Override
    public ApiResponse<List<CaseEstimateInfoDto>> caseEstimateInfoList(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        String turnStatus = apiReq.getString("turnStatus");
        if("1".equals(turnStatus)){
            //未转办
            apiReq.put("mStatus" , 1);
        }
        if("2".equals(turnStatus)){
            //已转办
            apiReq.put("mStatus" , 2);
        }
        int count = caseEstimateInfoMapper.selectCountCaseEstimateInfo(apiReq);
        List<CaseEstimateInfoDto> list = caseEstimateInfoMapper.selectCaseEstimateInfoList(apiReq);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,count,list);
    }

    /**
     * 通过‘id’查询“伤残预估数据”
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "通过‘id’查询“伤残预估数据”" ,value = "backend-invalidism-estimate-by-id")
    @Override
    public ApiResponse<InvalidismEstimate> searchInvalidismEstimateById(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        Long id = apiReq.getLong("estimateId");
        InvalidismEstimate invalidismEstimate = invalidismEstimateMapper.selectByPrimaryKey(id);
        return  new ApiResponse(ApiMsgEnum.SUCCESS,1,invalidismEstimate);
    }


    /**
     * 通过‘贷款申请编号’查询贷款申请数据
     * @param apiReq
     * @return
     */
    @ApiMethod(descript = "通过‘贷款申请编号’查询贷款申请数据" ,value = "backend-payment-estimate-apply-by-id")
    @Override
    public ApiResponse<PaymentEstimateApply> searchPaymentEstimateApplyById(ApiRequest apiReq){
        this.setBackendPageSize(apiReq);
        Long id = apiReq.getLong("estimateId");
        PaymentEstimateApply paymentEstimateApply = paymentEstimateApplyMapper.selectByPrimaryKey(id);
        return new ApiResponse(ApiMsgEnum.SUCCESS, 1, paymentEstimateApply);
    }

}
