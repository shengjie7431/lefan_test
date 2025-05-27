package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyInvestigatorApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyKnowledgeBaseApi;
import com.lefancrm.apicenter.dao.SurveyInvestigatorMapper;
import com.lefancrm.apicenter.dao.SurveyKnowledgeBaseMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.SurveyInvestigator;
import com.lefancrm.apicenter.model.SurveyKnowledgeBase;
import com.lefancrm.apicenter.model.SurveyKnowledgeComment;
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
 * Created by wangwei on 2018/12/20.
 * 论坛帖子
 */
@Service
@ApiService(descript = "论坛帖子API")
public class BackendSurveyKnowledgeBaseApiImpl extends BaseServiceImpl implements BackendSurveyKnowledgeBaseApi {

    @Autowired
    private SurveyKnowledgeBaseMapper surveyKnowledgeBaseMapper;


}
