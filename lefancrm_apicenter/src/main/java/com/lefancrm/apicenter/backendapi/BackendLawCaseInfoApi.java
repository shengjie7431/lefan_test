package com.lefancrm.apicenter.backendapi;

import com.lefancrm.apicenter.dao.LawNumberDto;
import com.lefancrm.apicenter.dto.LawCaseInfoDto;
import com.lefancrm.apicenter.model.LawCaseInfo;
import com.lefancrm.apicenter.model.LawCaseInfoFollow;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;

import java.util.List;

/**
 * Created by lixianfeng on 2018/10/17.
 */
public interface BackendLawCaseInfoApi {
    /**
     * 添加案件信息
     * @param apiRequest
     * @return
     */
    ApiResponse<LawCaseInfo> add(ApiRequest apiRequest);


    ApiResponse<LawCaseInfoDto> info(ApiRequest apiRequest);

    /**
     * 查询案件列表
     * @param apiRequest
     * @return
     */
    ApiResponse<List<LawCaseInfoDto>> list(ApiRequest apiRequest);

    ApiResponse<LawNumberDto> number(ApiRequest apiRequest);

    /**
     * 业务处理案件
     * @param apiRequest
     * @return
     */
    ApiResponse<LawCaseInfo> operate(ApiRequest apiRequest);

    /**
     * 保存案件跟踪信息
     * @param apiRequest
     * @return
     */
    ApiResponse followSave(ApiRequest apiRequest);

    /**
     * 根据‘案件编号’查询案件跟踪信息
     * @param apiRequest
     * @return
     */
    ApiResponse<List<LawCaseInfoFollow>> searchFollowByCaseId(ApiRequest apiRequest);

    /**
     * 查看资料
     * @param apiRequest
     * @return
     */
    ApiResponse selectLawFile(ApiRequest apiRequest);

    /**
     * 查看进度
     * @param apiRequest
     * @return
     */
    ApiResponse selectLawProgress(ApiRequest apiRequest);
    ApiResponse selectFilesAddress(ApiRequest apiRequest);
}
