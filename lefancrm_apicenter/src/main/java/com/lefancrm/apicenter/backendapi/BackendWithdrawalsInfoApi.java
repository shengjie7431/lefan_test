package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.dto.PromotionOutlayDto;
import com.lefancrm.apicenter.dto.UserAccountDto;
import com.lefancrm.apicenter.model.PromotionOutlay;
import com.lefancrm.apicenter.model.WithdrawalsInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by wangwei on 2018/5/17.
 */
public interface BackendWithdrawalsInfoApi {
    /**
     * 提现审核列表
     * @param apiRequest
     * @return
     */
    ApiResponse<List<WithdrawalsInfo>> withdrawalsInfoList(ApiRequest apiRequest);

    /**
     * 查询提现明细信息
     * @param apiReq
     * @return
     */
    ApiResponse<List<PromotionOutlayDto>> selectBkPromotionOutlayDtoListByParam(ApiRequest apiReq);

    /**
     * 用户推广账户信息
     * @param apiReq
     * @return
     */
    ApiResponse<UserAccountDto> selectBkUserAccountDto(ApiRequest apiReq);

    /**
     * 审核提现申请
     * @param apiRequest
     * @return
     */
    ApiResponse<WithdrawalsInfo> editwithdrawalsState(ApiRequest apiRequest);

    /**
     * 减掉此笔费用
     * @param apiReq
     * @return
     */
    ApiResponse<PromotionOutlay> cutOffPromotionOutlayInfo(ApiRequest apiReq);

    /**
     * 在线提现
     * @param apiReq
     * @return
     */
    ApiResponse<String> withdrawalsOnline(ApiRequest apiReq);

    /**
     * 保存线下提现凭证
     * @param apiReq
     * @return
     */
    ApiResponse<WithdrawalsInfo> withdrawalsUnlineSave(ApiRequest apiReq);

    /**
     * 根据id查询单条提现详情
     * @param apiRequest
     * @return
     */
    ApiResponse<WithdrawalsInfo> searchwithdrawalsInfoById(ApiRequest apiRequest);
}
