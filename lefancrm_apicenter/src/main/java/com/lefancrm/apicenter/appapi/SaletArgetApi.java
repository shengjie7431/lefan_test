package com.lefancrm.apicenter.appapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by DELL on 2017/12/15.
 */
public interface SaletArgetApi {
    /**
     * 销售简报查询
     * @param apiReq
     */
    ApiResponse querySaleGoalSimple(ApiRequest apiReq);

    /**
     * 查询业绩排行榜
     * @param apiReq
     * @return
     */
    ApiResponse queryAchievementRank(ApiRequest apiReq);


    /**
     * 成交客户排行榜
     * @param apiReq
     * @return
     */
    ApiResponse querySignCaseRank(ApiRequest apiReq);

    /**
     * 拜访客户排行榜
     * @param apiReq
     * @return
     */
    ApiResponse queryVisitCustomerRank(ApiRequest apiReq);

    /**
     * 销售漏斗（查询本周）
     * @param apiReq
     * @return

    ApiResponse querySaleFunnel(ApiRequest apiReq);*/
}
