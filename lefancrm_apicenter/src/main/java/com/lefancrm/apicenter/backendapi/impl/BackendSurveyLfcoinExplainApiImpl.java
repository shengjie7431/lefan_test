package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyLevelApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyLfcoinExplainApi;
import com.lefancrm.apicenter.dao.SurveyLevelMapper;
import com.lefancrm.apicenter.dao.SurveyLfcoinExplainMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.SurveyLevel;
import com.lefancrm.apicenter.model.SurveyLfcoinExplain;
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
 * “乐凡币介绍”数据管理
 */
@Service
@ApiService(descript = "乐凡币介绍API")
public class BackendSurveyLfcoinExplainApiImpl extends BaseServiceImpl implements BackendSurveyLfcoinExplainApi {

    @Autowired
    private SurveyLfcoinExplainMapper surveyLfcoinExplainMapper;

}
