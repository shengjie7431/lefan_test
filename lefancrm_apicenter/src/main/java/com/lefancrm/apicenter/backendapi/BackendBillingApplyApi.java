package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/7/24.
 */
public interface BackendBillingApplyApi {

    /**
     * 开票列表
     * @param apiRequest
     * @return
     */
    ApiResponse billingApplyList(ApiRequest apiRequest);

    /**
     * 根据“id”查询开票信息
     * @param apiRequest
     * @return
     */
    ApiResponse searchBillingApplyById(ApiRequest apiRequest);

    /**
     * 根据billingId，查询开票材料的list
     * @param apiRequest
     * @return
     */
    ApiResponse searchBillingApplyMaterialById(ApiRequest apiRequest);

    /**
     * 根据billingId，查询发票的list
     * @param request
     * @return
     */
    ApiResponse findBillApplyById(ApiRequest request);
    ApiResponse findBillApplyByIdNew(ApiRequest request);

    /**
     * 根据选择的类目生成案件编号
     * @param apiRequest
     * @return
     */
    ApiResponse toBuildCaseNo(ApiRequest apiRequest);

    /**
     * 保存开票信息
     * @param apiRequest
     * @return
     */
    ApiResponse billingApplySave(ApiRequest apiRequest);

    /**
     * 开票信息--提交申请
     * @param apiRequest
     * @return
     */
    ApiResponse billApplyStateUp(ApiRequest apiRequest);

    /**
     * 保存开票材料
     * @param apiRequest
     * @return
     */
    ApiResponse saveMaterial(ApiRequest apiRequest);

    /**
     * 删除开票信息
     * @param apiRequest
     * @return
     */
    ApiResponse billingApplyDelete(ApiRequest apiRequest);

    /**
     * 删除单条发票或材料记录
     * @param apiRequest
     * @return
     */
    ApiResponse billApplyImgsDelete(ApiRequest apiRequest);

    /**
     * 公估确认到账
     * @param apiRequest
     * @return
     */
    ApiResponse confirmAccountSubmit(ApiRequest apiRequest);

    /**
     * 发票作废或红冲
     * @param apiRequest
     * @return
     */
    ApiResponse billApplyImgsUpd(ApiRequest apiRequest);

    /**
     * 根据“id”查询开票信息及附属信息
     * @param apiRequest
     * @return
     */
    ApiResponse searchBillingApplyImgsForExport(ApiRequest apiRequest);
    ApiResponse searchBillingApplyMaterialForExport(ApiRequest apiRequest);

    /**
     * 需要合并的开票list
     * @param apiRequest
     * @return
     */
    ApiResponse billingApplyMergeList(ApiRequest apiRequest);

    /**
     * 合并开票
     * @param apiRequest
     * @return
     */
    ApiResponse billingApplyMeger(ApiRequest apiRequest);

    /**
     * 合并开票（角色是不是机构经理）
     * @param apiRequest
     * @return
     */
    ApiResponse billingApplyMergeRole(ApiRequest apiRequest);

    /**
     * 财务中心管理数量
     * @param apiRequest
     * @return
     */
    ApiResponse financeNumber(ApiRequest apiRequest);

    /**
     * 客服中心管理数量
     * @param apiRequest
     * @return
     */
    ApiResponse customerNumber(ApiRequest apiRequest);

    /**
     * 开票类目 -- 枚举查询
     * @param apiRequest
     * @return
     */
    ApiResponse searchBillingEnum(ApiRequest apiRequest);

    /**
     * 根据billingId，查询公估确认到账的list
     * @param request
     * @return
     */
    ApiResponse findBillApplyAccountsById(ApiRequest request);

    /**
     * 开票列表导出
     * @param apiRequest
     * @return
     */
    ApiResponse billingApplyListForExport(ApiRequest apiRequest);

    /**
     * 根据id，查询关联的子表数据
     * @param apiRequest
     * @return
     */
    ApiResponse selectInfoByRelationId(ApiRequest apiRequest);
}
