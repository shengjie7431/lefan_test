package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyBusinessTypeApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyServiceTypeApi;
import com.lefancrm.apicenter.dao.SurveyBusinessTypeMapper;
import com.lefancrm.apicenter.dao.SurveyServiceSubTypeMapper;
import com.lefancrm.apicenter.dao.SurveyServiceTypeMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.SurveyBusinessType;
import com.lefancrm.apicenter.model.SurveyServiceSubType;
import com.lefancrm.apicenter.model.SurveyServiceType;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangwei on 2019/01/08.
 * “业务类型”数据管理
 */
@Service
@ApiService(descript = "业务类型API")
public class BackendSurveyServiceTypeApiImpl extends BaseServiceImpl implements BackendSurveyServiceTypeApi {

    @Autowired
    private SurveyServiceTypeMapper surveyServiceTypeMapper;
    @Autowired
    private SurveyServiceSubTypeMapper surveyServiceSubTypeMapper;

    /**
     * 业务类型list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "业务类型list(非分页数据)", value = "backend-survey-service-type-list", apiParams = { })
    @Override
    public ApiResponse list(ApiRequest apiReq) {
        List<SurveyServiceType> list = surveyServiceTypeMapper.list(apiReq);
        for (SurveyServiceType surveyServiceType : list) {
            List<SurveyServiceSubType> surveyServiceSubTypes = surveyServiceSubTypeMapper.selectByServiceId(surveyServiceType.getId());
            surveyServiceType.setSurveyServiceSubTypes(surveyServiceSubTypes);
        }
        int count = surveyServiceTypeMapper.listSize(apiReq);
        return new ApiResponse<List<SurveyServiceType>>(ApiMsgEnum.SUCCESS, count, list);
    }

}
