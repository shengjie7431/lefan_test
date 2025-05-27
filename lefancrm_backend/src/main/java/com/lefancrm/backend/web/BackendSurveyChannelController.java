package com.lefancrm.backend.web;


import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.dto.channel.SurveyChannelCost;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/survey/channel/")
public class BackendSurveyChannelController extends BackendBaseController{
    @RequestMapping(value = "list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        model.put("pageSize",req.getParameter("pageSize"));
        Map<String,Object> appendMap =  new HashMap<String,Object>();
        if (req.getParameter("states") == null) {
            model.put("states","1,2,4,5,6");
            appendMap.put("states","1,2,4,5,6");
        }else{
            model.put("states",req.getParameter("states"));
            appendMap.put("states","".equals(req.getParameter("states")) ? null : req.getParameter("states"));
        }
        model.put("startTime",req.getParameter("startTime"));
        model.put("endTime",req.getParameter("endTime"));
        model.put("orgUserName",req.getParameter("orgUserName"));
        model.put("isProPay",req.getParameter("isProPay") == null || "-1".equals(req.getParameter("isProPay")) ? null : req.getParameter("isProPay"));
        appendMap.put("isProPay",model.get("isProPay"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyChannelCost>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.SURVEY_CHANNEL_LIST, appendMap, req);
        model.put("apiRsp",apiFinalResponse);
        //
        typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CURRENT_USER_ROLE_LIST, null, req);
        List<BusUserRoleDto> userRoles = (List<BusUserRoleDto>)apiFinalResponse.getResults();
        Boolean orgRole = isRoleUser(userRoles,58L);
        Boolean orgRole2 = isRoleUser(userRoles,114L);
        if (orgRole2){//如果是副机构负责人。 也可发起渠道审核
            orgRole = true;
        }
        Boolean oprRole = isRoleUser(userRoles,112L);
        model.put("orgRole",orgRole);
        model.put("oprRole",oprRole);
        return new ModelAndView("/survey/channel/list",model);
    }



    @RequestMapping(value = "info")
    public ModelAndView info(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyChannelCost>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.SURVEY_CHANNEL_INFO, null, req);
        SurveyChannelCost surveyChannelCost = (SurveyChannelCost)apiFinalResponse.getResults();
        if (surveyChannelCost != null) {
            surveyChannelCost.setSurveyChannelCasesJson(JSONArray.toJSONString(surveyChannelCost.getSurveyChannelCases()));
        }
        model.put("dto",surveyChannelCost);
        return new ModelAndView("/survey/channel/info",model);
    }



    @RequestMapping(value = "operate")
    public String operate(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.SURVEY_CHANNEL_OPERATE, null, req, rsp);
    }

    @RequestMapping(value = "listNew")
    public ModelAndView listNew(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        model.put("pageSize",req.getParameter("pageSize"));
        Map<String,Object> appendMap =  new HashMap<String,Object>();
        if (req.getParameter("states") == null) {
            model.put("states","5,6");
            appendMap.put("states","5,6");
        }else{
            model.put("states",req.getParameter("states"));
            appendMap.put("states","".equals(req.getParameter("states")) ? null : req.getParameter("states"));
        }
        model.put("appStartTime",req.getParameter("appStartTime"));model.put("appEndTime",req.getParameter("appEndTime"));
        model.put("oprStartTime",req.getParameter("oprStartTime"));model.put("oprEndTime",req.getParameter("oprEndTime"));
        model.put("payStartTime",req.getParameter("payStartTime"));model.put("payEndTime",req.getParameter("payEndTime"));

        model.put("orgUserName",req.getParameter("orgUserName"));
        model.put("surveyCaseNo",req.getParameter("surveyCaseNo"));
        model.put("orgUserIds",req.getParameter("orgUserIds"));
        model.put("entrustOrgIds",req.getParameter("entrustOrgIds"));
        model.put("isProPay",req.getParameter("isProPay") == null || "-1".equals(req.getParameter("isProPay")) ? null : req.getParameter("isProPay"));
        appendMap.put("isProPay",model.get("isProPay"));

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyChannelCostNew>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.SURVEY_CHANNEL_NEW_LIST, appendMap, req);
        model.put("apiRsp",apiFinalResponse);

        typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {};
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CONSIGNOR_LIST_FOR_FINAL_USER, appendMap, req);
        List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
        model.put("consignors",consignors);

        //获取所有机构负责人列表
        typeToken = new TypeToken<ApiFinalResponse<List<UserInfo>>>(){};
        appendMap =  new HashMap<String,Object>();
        appendMap.put("roleId",58L);
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_USER_INFO_LIST_BY_ROLEID, appendMap, req);
        List<UserInfo> orgUsers =(List<UserInfo>)apiFinalResponse.getResults();
        model.put("orgUsers",orgUsers);
        //
        typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CURRENT_USER_ROLE_LIST, null, req);
        List<BusUserRoleDto> userRoles = (List<BusUserRoleDto>)apiFinalResponse.getResults();
        Boolean orgRole = isRoleUser(userRoles,58L);
        Boolean orgRole2 = isRoleUser(userRoles,114L);
        if (orgRole2){//如果是副机构负责人。 也可发起渠道审核
            orgRole = true;
        }
        Boolean oprRole = isRoleUser(userRoles,112L);
        model.put("orgRole",orgRole);
        model.put("oprRole",oprRole);
        return new ModelAndView("/survey/channel/listNew",model);
    }

    @RequestMapping(value = "infoNew")
    public ModelAndView infoNew(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyChannelCostNew>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.SURVEY_CHANNEL_NEW_INFO, null, req);
        SurveyChannelCostNew surveyChannelCost = (SurveyChannelCostNew)apiFinalResponse.getResults();
        model.put("dto",surveyChannelCost);
        if (StringUtils.isEmpty(req.getParameter("id"))){
            return new ModelAndView("/survey/channel/app",model);
        }
        return new ModelAndView("/survey/channel/infoNew",model);
    }

    @RequestMapping(value = "operateNew")
    public String operateNew(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.SURVEY_CHANNEL_NEW_OPERATE, null, req, rsp);
    }
    @RequestMapping(value = "ajaxData")
    public String ajaxData(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.SURVEY_CHANNEL_AJAX_DATA, null, req, rsp);
    }

    @RequestMapping(value = "ajaxDataNew")
    public String ajaxDataNew(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.SURVEY_CHANNEL_AJAX_DATA_NEW, null, req, rsp);
    }
}
