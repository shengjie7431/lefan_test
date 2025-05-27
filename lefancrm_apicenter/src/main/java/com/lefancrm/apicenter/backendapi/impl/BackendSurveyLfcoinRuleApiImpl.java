package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.BackendSurveyLevelApi;
import com.lefancrm.apicenter.backendapi.BackendSurveyLfcoinRuleApi;
import com.lefancrm.apicenter.dao.SurveyLfcoinRuleMapper;
import com.lefancrm.apicenter.dao.UserInfoMapper;
import com.lefancrm.apicenter.model.SurveyLfcoinRule;
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
 * “乐凡币规则”数据管理
 */
@Service
@ApiService(descript = "乐凡币规则API")
public class BackendSurveyLfcoinRuleApiImpl extends BaseServiceImpl implements BackendSurveyLfcoinRuleApi {

    @Autowired
    private SurveyLfcoinRuleMapper surveyLfcoinRuleMapper;

}
