package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyKnowledgeTypeApi;
import com.lefancrm.apicenter.dao.SurveyKnowledgeTypeMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.SurveyKnowledgeType;
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
 * Created by wangwei on 2018/12/19.
 * “帖子类别”数据管理
 */
@Service
@ApiService(descript = "帖子类别API")
public class BackendSurveyKnowledgeTypeApiImpl extends BaseServiceImpl implements BackendSurveyKnowledgeTypeApi {

    @Autowired
    private SurveyKnowledgeTypeMapper surveyKnowledgeTypeMapper;

    /**
     * 帖子类别(非分页数据)list
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "帖子类别(非分页数据)list", value = "backend-survey-know-ledge-type-list", apiParams = { })
    @Override
    public ApiResponse list(ApiRequest apiReq) {
        //(非分页数据)
        List<SurveyKnowledgeType> list = surveyKnowledgeTypeMapper.list(apiReq);
        return new ApiResponse<List<SurveyKnowledgeType>>(ApiMsgEnum.SUCCESS, 1, list);

    }

}
