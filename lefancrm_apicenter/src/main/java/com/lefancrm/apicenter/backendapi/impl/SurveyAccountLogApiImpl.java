package com.lefancrm.apicenter.backendapi.impl;

import com.lefancrm.apicenter.backendapi.SurveyAccountLogApi;
import com.lefancrm.apicenter.dao.SurveyAccountLogMapper;
import com.lefancrm.apicenter.model.SurveyAccountLog;
import com.lefancrm.apicenter.service.impl.BaseServiceImpl;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.annotations.ApiService;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 申请结算价格调整表实现层
 * @author EDZ
 */
@Service
@ApiService(descript = "申请结算价格调整表实现层API")
public class SurveyAccountLogApiImpl extends BaseServiceImpl implements SurveyAccountLogApi {

    @Autowired
    private SurveyAccountLogMapper surveyAccountLogMapper;


    @ApiMethod(needLogin = false,descript = "申请结算价格调整表根据id删除" ,value = "survey-account-log-del")
    @Override
    public int deleteByPrimaryKey(Long id) {
        return surveyAccountLogMapper.deleteByPrimaryKey(id);
    }

    @ApiMethod(needLogin = false,descript = "申请结算价格调整表新增" ,value = "survey-account-log-add")
    @Override
    public int insert(SurveyAccountLog record) {
        return surveyAccountLogMapper.insert(record);
    }

    @ApiMethod(needLogin = false,descript = "申请结算价格调整表根据实体类新增" ,value = "survey-account-log-save")
    @Override
    public int insertSelective(SurveyAccountLog record) {
        return surveyAccountLogMapper.insertSelective(record);
    }

    @ApiMethod(needLogin = false,descript = "申请结算价格调整表根据id查询" ,value = "survey-account-log-info")
    @Override
    public SurveyAccountLog selectByPrimaryKey(Long id) {
        return surveyAccountLogMapper.selectByPrimaryKey(id);
    }

    @ApiMethod(needLogin = false,descript = "申请结算价格调整表根据实体类修改" ,value = "survey-account-log-upd")
    @Override
    public int updateByPrimaryKeySelective(SurveyAccountLog record) {
        return surveyAccountLogMapper.updateByPrimaryKeySelective(record);
    }

    @ApiMethod(needLogin = false,descript = "申请结算价格调整表修改" ,value = "survey-account-log-edit")
    @Override
    public int updateByPrimaryKey(SurveyAccountLog record) {
        return surveyAccountLogMapper.updateByPrimaryKey(record);
    }


    @ApiMethod(descript = "申请结算价格调整表查询数据" ,value = "survey-account-log-list",apiParams = { })
    @Override
    public ApiResponse selectByList(ApiRequest apiReq) {
        //非分页数据
        List<SurveyAccountLog> list=surveyAccountLogMapper.selectByList(apiReq);
        return new ApiResponse<List<SurveyAccountLog>>(ApiMsgEnum.SUCCESS,null,list);
    }
}
