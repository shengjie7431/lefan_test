package com.lefancrm.apicenter.appapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * Created by ting on 2017/12/18.
 */
public interface UserInfoApi {
    /**
     * 修改用户信息
     * @param apiReq
     * @return
     */
    ApiResponse updateSettingUserInfo(ApiRequest apiReq);
    /**
     * 用户个人业绩
     * @param apiReq
     * @return
     */
    ApiResponse selectMySale(ApiRequest apiReq);
}
