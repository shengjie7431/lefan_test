package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.CaseCenterInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by lixianfeng on 2018/3/23.
 */
public interface BackendCaseCenterInfoApi {
    /**
     * 各种案件列表
     * @param apiRequest
     * @return
     */
    ApiResponse list(ApiRequest apiRequest);
    /**
     * 案件列表各种操作
     * @param apiRequest
     * @return
     */
//    ApiResponse operate(ApiRequest apiRequest);
    /**
     * 查询最新报价
     * @param apiRequest
     * @return
     */
    ApiResponse selectNewestPayInquiry(ApiRequest apiRequest);



    ApiResponse oldList(ApiRequest apiReq);
    ApiResponse selectCaseFileEnum(ApiRequest apiReq);
    ApiResponse selectCaseFile(ApiRequest apiReq);
    ApiResponse selectFilesAddress(ApiRequest apiReq);

    /**
     * 显示案件详情和案件跟踪信息
     * @param apiReq
     * @return
     */
    ApiResponse selectCaseDetails(ApiRequest apiReq);

    /**
     * 显示案件详情和案件跟踪信息
     * @param apiReq
     * @return
     */
//    ApiResponse overtimeList(ApiRequest apiReq);

    /**
     * 还款清单列表
     * @param apiReq
     * @return
     */
    ApiResponse<List<CaseCenterInfo>> repayCaseCenterInfoList(ApiRequest apiReq);

    /**
     * 还款清单详情
     * @param apiReq
     * @return
     */
    ApiResponse searchCaseCenterInfoForRepay(ApiRequest apiReq);

    /**
     * 操作还款确认
     * @param apiReq
     * @return
     */
    ApiResponse updHaldleRepayCaseCenterInfo(ApiRequest apiReq);

    /**
     * 还款确认列表
     * @param apiReq
     * @return
     */
    ApiResponse<List<CaseCenterInfo>> confirmRepayCaseCenterInfoList(ApiRequest apiReq);

    /**
     * 还款清单详情
     * @param apiReq
     * @return
     */
    ApiResponse searchCaseCenterInfoForConfirmRepay(ApiRequest apiReq);

    /**
     * 确认通过还款
     * @param apiReq
     * @return
     */
    ApiResponse updCaseCenterInfoConfirmRepay(ApiRequest apiReq);

    /**
     * 查询分配机构列表
     * @param apiReq
     * @return
     */
    ApiResponse selectOrgInfo(ApiRequest apiReq);

    /**
     * 超时清单
     * @param apiReq
     * @return
     */
    ApiResponse<List<CaseCenterInfo>> overtimeList(ApiRequest apiReq);

    /**
     * 确认服务费
     * @param apiReq
     * @return
     */
    ApiResponse okServiceFee(ApiRequest apiReq);

    /**
     * 保存上传单证图片
     * @param apiReq
     * @return
     */
    ApiResponse uploadCommonFileImg(ApiRequest apiReq);
}
