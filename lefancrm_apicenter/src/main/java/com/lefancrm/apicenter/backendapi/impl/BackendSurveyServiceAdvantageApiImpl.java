package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyLevelApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyServiceAdvantageApi;
import com.lefancrm.apicenter.dao.SurveyLevelMapper;
import com.lefancrm.apicenter.dao.SurveyServiceAdvantageMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.SurveyLevel;
import com.lefancrm.apicenter.model.SurveyServiceAdvantage;
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
 * “服务优势”数据管理
 */
@Service
@ApiService(descript = "服务优势API")
public class BackendSurveyServiceAdvantageApiImpl extends BaseServiceImpl implements BackendSurveyServiceAdvantageApi {

    @Autowired
    private SurveyServiceAdvantageMapper surveyServiceAdvantageMapper;


}
