package com.lefancrm.apicenter.appapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by Jani on 2017/12/15.
 */
public interface SignInfoApi {
    /**
     * 签到
     */
    ApiResponse signIn(ApiRequest apiReq) throws Exception;

    /**
     * 根据机构ID查询最新签到信息表
     * @param apiReq
     * @return
     */
    ApiResponse orgSignInfoList(ApiRequest apiReq);

    /**
     * 根据用户ID查询用户签到信息
     * @param apiReq
     * @return
     */
    ApiResponse userSignInfoList(ApiRequest apiReq);

    /**
     * 签到详情查询
     * @param apiReq
     * @return
     */
    ApiResponse signInfoDetails(ApiRequest apiReq);

    /**
     * CC业务员未签到人员列表
     * @param apiReq
     * @return
     */
    ApiResponse notSignList(ApiRequest apiReq);
}
