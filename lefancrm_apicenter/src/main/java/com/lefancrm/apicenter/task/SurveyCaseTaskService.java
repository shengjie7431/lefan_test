package com.lefancrm.apicenter.task;

import com.lefancrm.apicenter.backendapi.impl.BackendWechatApiImpl;
import com.lefancrm.apicenter.dao.*;
import com.lefancrm.apicenter.dto.SurveyAssignOrgDto;
import com.lefancrm.apicenter.dto.SurveyInvestigatorCaseDto;
import com.lefancrm.apicenter.model.*;
import com.lefancrm.apicenter.util.GetWorkDay;
import com.lefancrm.apicenter.util.HttpClientUtils;
import com.lefancrm.apicenter.util.SendMessageUntil;
import com.lefancrm.apicenter.util.WechatTempleMsgUtil;
import com.lefancrm.base.annotations.ApiMethod;
import com.lefancrm.base.dto.ApiRequest;
import com.lefancrm.base.dto.ApiResponse;
import com.lefancrm.base.enums.ApiMsgEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Jani on 2019/1/29.
 */
@Service
public class SurveyCaseTaskService implements Serializable{
    @Autowired
    private SurveyInvestigatorCaseMapper surveyInvestigatorCaseMapper;
    @Autowired
    private SurveyAssignOrgMapper surveyAssignOrgMapper;
    @Autowired
    private BackendWechatApiImpl backendWechatApi;
    @Autowired
    private UserInfoMapper userInfoMapper;

    public ApiResponse surveyUser() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Map<String,Object> map = new HashMap<>();
        List<SurveyInvestigatorCaseDto> list = surveyInvestigatorCaseMapper.selectCaseNoCommit(map);
        for (SurveyInvestigatorCaseDto item : list) {
            System.out.println("user:" +  item.getAgingCheck() + ":" + item.getAgingReal());
            String content = "";
            if (item.getAgingOver() == 0){
                Double day = item.getAgingCheck() - item.getAgingReal();
                if (day == 1){//还有一天超期
                    content = "你有任务还有一天超时，请尽快调查并提交审核！";
                }else if (day == 0){//即将超期
                    content = "你有任务今天即将超期，请尽快调查并提交审核！";
                }
            }else if (item.getAgingOver() == 1){//超期一天
                content = "你有任务已超时一天，请尽快调查并提交审核！";
            }
            if (!"".equals(content)){
                Map<String,Object> msgMap =  new HashMap<String,Object>();
                msgMap.put("title","超时提醒");
                msgMap.put("content",content);
                msgMap.put("keyWords","案件编号：" + item.getSurveyCaseNo() + "\n" + "被调查人：" + item.getSurveyPerson() + "\n" + "调查截止日期：" + simpleDateFormat.format(item.getSurveyEndTime()));
                backendWechatApi.send(item.getSurveyUserId(),msgMap);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
    }

    public ApiResponse surveyOrg() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Map<String,Object> map =  new HashMap<String,Object>();
        map.put("statusData",0);//未提交  map.put("statusData",1);//所有
        List<SurveyAssignOrgDto> list = surveyAssignOrgMapper.list(map);
        for (SurveyAssignOrgDto item : list) {
            System.out.println( "org:" + item.getAgingCheck() + ":" + item.getAgingReal());
            String content = "";
            if (item.getAgingOver() == 0){
                Double day = item.getAgingCheck() - item.getAgingReal();
                if (day == 1){//还有一天超期
                    content = "机构有任务还有一天超时，请尽快调查并提交审核！";
                }else if (day == 0){//即将超期
                    content = "机构有任务今天即将超期，请尽快调查并提交审核！";
                }
            }else if (item.getAgingOver() == 1){//超期一天
                content = "机构有任务已超时一天，请尽快调查并提交审核！";
            }
            if (!"".equals(content)){
                Map<String,Object> msgMap =  new HashMap<String,Object>();
                msgMap.put("title","超时提醒");
                msgMap.put("content",content);
                msgMap.put("keyWords","案件编号：" + item.getSurveyCaseNo() + "\n" + "被调查人：" + item.getSurveyPerson() + "\n" + "调查截止日期：" + simpleDateFormat.format(item.getOrgEndTime()));
                //查询机构初审人员
                Map<String,Object> paramMap =  new HashMap<String,Object>();
                paramMap.put("roleId",58L);
                paramMap.put("orgId",item.getSurveyOrgId());
                List<UserInfo> toUsers = userInfoMapper.selectUserByOrgIdAndRoleId(paramMap);
                backendWechatApi.send(toUsers,msgMap);
            }
        }
        return new ApiResponse(ApiMsgEnum.SUCCESS,list.size(),list);
    }


    public Boolean http(){
        String url = "http://saas.shlefan.com/crmbackend/logintest";
        Boolean success = false;
        try {
            String http = HttpClientUtils.httpPost(url,new HashMap<String, String>(),"UTF-8");
            System.out.println(http);
            if (!StringUtils.isEmpty(http)){
                success = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }
}
