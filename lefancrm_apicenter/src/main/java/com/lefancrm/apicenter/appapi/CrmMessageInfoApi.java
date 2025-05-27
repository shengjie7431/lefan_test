package com.lefancrm.apicenter.appapi;

import com.lefancrm.apicenter.dto.CrmMsgDto;
import com.lefancrm.apicenter.model.CrmMessageInfo;
import com.lefancrm.apicenter.model.MessageInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by ting on 2017/12/18.
 */
public interface CrmMessageInfoApi {
    ApiResponse<List<CrmMsgDto>> getMessageInfoList(ApiRequest apiReq);

    ApiResponse<CrmMessageInfo> selectMessageInfoById(ApiRequest apiReq);

    /**
     * 统计所有消息提示数量
     * @param apiReq
     * @return
     */
    ApiResponse<Map<String ,Object>> selectMessagePrompt(ApiRequest apiReq);

    /**
     * 消息阅读状态变更
     * @param apiReq
     * @return
     */
    ApiResponse updateMessageRead(ApiRequest apiReq);
}
