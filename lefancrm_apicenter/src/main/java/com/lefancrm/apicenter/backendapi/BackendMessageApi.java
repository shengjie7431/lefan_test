package com.lefancrm.apicenter.backendapi;

import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

/**
 * 通知消息API
 * 
 * @author Daniel
 */
public interface BackendMessageApi {
    /**
     * 查询通知消息列表
     * @param apiReq
     * @return
     */
    ApiResponse list(ApiRequest apiReq);
    /**
     * 查询接受者
     * @param apiReq
     * @return
     */


    ApiResponse toAddMessage(ApiRequest apiReq);

    public ApiResponse addMessage(ApiRequest apiReq);

}
