package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyBusinessTypeApi;
import com.lefancrm.apicenter.dao.SurveyBusinessTypeMapper;
import com.lefancrm.apicenter.dao.SurveyTaskInfoMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.SurveyBusinessType;
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
 * “领域类型”数据管理
 */
@Service
@ApiService(descript = "领域类型API")
public class BackendSurveyBusinessTypeApiImpl extends BaseServiceImpl implements BackendSurveyBusinessTypeApi {

    @Autowired
    private SurveyBusinessTypeMapper surveyBusinessTypeMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 业务类型list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "领域类型list", value = "backend-survey-business-type-list", apiParams = { })
    @Override
    public ApiResponse list(ApiRequest apiReq) {
        //非分页数据
        List<SurveyBusinessType> list = surveyBusinessTypeMapper.list(apiReq);
        return new ApiResponse<List<SurveyBusinessType>>(ApiMsgEnum.SUCCESS, null, list);

    }

}
