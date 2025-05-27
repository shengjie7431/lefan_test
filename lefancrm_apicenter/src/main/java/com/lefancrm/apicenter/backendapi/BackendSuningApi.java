package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.BillingApply;
import com.lefancrm.apicenter.model.CardInfoDto;
import com.lefancrm.apicenter.model.CaseCenterInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by lixianfeng on 2018/3/22.
 */
public interface BackendSuningApi {
    ApiResponse findSuningWithholdApplyList(ApiRequest request);

    ApiResponse okStartSuningWithholdApplyInfo(ApiRequest request);
    ApiResponse okSuningWithholdApplyInfo(ApiRequest request);
    /**
     * 苏宁代扣
     * @param cardInfoDto  卡信息
     * @param amount         金额
     * @param caseCenterInfo  案件信息
     * @param isAudit        是否审核
     * @param type           1现金  2代扣  3转账
     * @param currentUserId 当前用户id
     * @param applyId 代扣申请表id
     * @param resourcePath  资源路径
     * @return
     */
    boolean proxy(CardInfoDto cardInfoDto,Double amount,CaseCenterInfo caseCenterInfo,Boolean isAudit,Integer type,Long currentUserId,Long applyId,String resourcePath,Integer applyType,Double hidAmount);


    /**
     * 查询开票详情
     * @param request
     * @return
     */
    ApiResponse findBillApply(ApiRequest request);

    /**
     * 修改开票状态
     * @param request
     * @return
     */
    ApiResponse updBillApply(ApiRequest request);
    /**
     * 保存开票申请表
     * @param billingApply
     * @return
     */
    int saveBillingApply(BillingApply billingApply);

    /**
     * 查询到站详情
     * @param request
     * @return
     */
    ApiResponse findArrivalList(ApiRequest request);

    /**
     * 银行卡信息
     * @param request
     * @return
     */
    ApiResponse editBankCardInfo(ApiRequest request);

    /**
     * 查询开票详情
     * @param request
     * @return
     */
    ApiResponse findBillApplyView(ApiRequest request);

    /**
     * 查询确认到账详情
     * @param request
     * @return
     */
    ApiResponse findWithHoldApplyView(ApiRequest request);
    ApiResponse saveBankCardInfo(ApiRequest request);

}
