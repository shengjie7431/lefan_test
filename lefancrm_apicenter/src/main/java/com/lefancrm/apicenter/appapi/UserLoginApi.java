package com.lefancrm.apicenter.appapi;

import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.io.IOException;

/**
 * Created by ting on 2017/12/15.
 */
public interface UserLoginApi {
    /*
    * 用户登录
    * */
    @SuppressWarnings("rawtypes")
    ApiResponse login(ApiRequest apiReq) ;

    /** 用户退出 */
    @SuppressWarnings("rawtypes")
    public ApiResponse logout(ApiRequest apiReq);

    /** 根据微信openID查询用户 */
    @SuppressWarnings("rawtypes")
    public ApiResponse selectUserByOpenId(ApiRequest apiReq) throws IOException;

    /** 发送验证短信 */
    @SuppressWarnings("rawtypes")
    public ApiResponse<UserInfo> sendSMS(ApiRequest apiRequest);

    /** 忘记密码 */
    @SuppressWarnings("rawtypes")
    public ApiResponse<UserInfo> forgetPwd(ApiRequest apiRequest);

    /** 更改密码 */
    @SuppressWarnings("rawtypes")
    public ApiResponse<UserInfo> updatePwd(ApiRequest apiRequest);
}
