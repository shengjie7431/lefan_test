package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.SurveyTeachRewardList;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 *带教奖励清单
 * @author EDZ
 */
public interface SurveyTeachRewardListApi {
    /**
     * 查询所有数据
     * @param apiRequest
     * @return
     */
    public ApiResponse selectByMap(ApiRequest apiRequest);
}
