package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyKnowledgeBaseApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyKnowledgeCommentApi;
import com.lefancrm.apicenter.dao.SurveyKnowledgeBaseMapper;
import com.lefancrm.apicenter.dao.SurveyKnowledgeCommentMapper;
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
public class BackendSurveyKnowledgeCommentApiImpl extends BaseServiceImpl implements BackendSurveyKnowledgeCommentApi {

    @Autowired
    private SurveyKnowledgeCommentMapper surveyKnowledgeCommentMapper;

    /**
     * 论坛帖子评论详情
     * @param apiReq
     * @return
     */
    @SuppressWarnings("rawtypes")
    @ApiMethod(descript = "论坛帖子评论详情", value = "backend-survey-knowledge-comment-info", apiParams = { })
    @Override
    public ApiResponse info(ApiRequest apiReq) {
        //单条帖子的所有评论
        List<SurveyKnowledgeComment> surveyKnowledgeComment = surveyKnowledgeCommentMapper.selectListByBaseId(apiReq.getLong("id"));
        return new ApiResponse(ApiMsgEnum.SUCCESS,1,surveyKnowledgeComment);
    }

}
