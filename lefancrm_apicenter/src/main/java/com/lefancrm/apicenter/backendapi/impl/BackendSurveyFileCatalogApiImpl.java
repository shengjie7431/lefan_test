package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyFileCatalogApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyTaskInfoApi;
import com.lefancrm.apicenter.dao.SurveyFileCatalogMapper;
import com.lefancrm.apicenter.dao.SurveyTaskInfoMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.SurveyFileCatalog;
import com.lefancrm.apicenter.model.SurveyTaskInfo;
import com.lefancrm.apicenter.model.UserInfo;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.apicenter.util.ConvertToBeanUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wangwei on 2018/12/17.
 * 材料目录
 */
@Service
@ApiService(descript = "材料目录API")
public class BackendSurveyFileCatalogApiImpl extends BaseServiceImpl implements BackendSurveyFileCatalogApi {

    @Autowired
    private SurveyFileCatalogMapper surveyFileCatalogMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 材料目录(非分页数据)list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "材料目录(非分页数据)list", value = "backend-survey-file-catalog-list", apiParams = { })
    @Override
    public ApiResponse list(ApiRequest apiReq) {
        //非分页数据
        List<SurveyFileCatalog> list = surveyFileCatalogMapper.list(apiReq);
        return new ApiResponse<List<SurveyFileCatalog>>(ApiMsgEnum.SUCCESS, null, list);

    }


}
