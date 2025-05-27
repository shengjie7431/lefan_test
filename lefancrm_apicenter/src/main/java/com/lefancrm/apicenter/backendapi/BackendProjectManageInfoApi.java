package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.model.ProjectManageInfo;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;
import java.util.Map;

/**
 * @author EDZ
 */
public interface BackendProjectManageInfoApi {

    /**
     * 根据条件查询所有
     * @param apiReq
     * @return
     */
    ApiResponse selectByMap(ApiRequest apiReq);

    /**
     * 根据条件查询一条信息
     * @param apiReq
     * @return
     */
    ApiResponse selectByOne(ApiRequest apiReq);

    /**
     * 根据实体类修改数据
     * @param apiReq
     * @return
     */
    ApiResponse updateOne(ApiRequest apiReq);

    /**
     * 新增数据
     * @param apiReq
     * @return
     */
    ApiResponse insertOne(ApiRequest apiReq);

    /**
     * 根据条件删除数据
     * @param apiReq
     * @return
     */
    ApiResponse deleteOne(ApiRequest apiReq);

    ApiResponse dataHandle(ApiRequest apiReq);
}
