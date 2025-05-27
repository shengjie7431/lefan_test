package com.lefancrm.backend.web;


import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.SurveyFwCaseDTO;
import com.lefancrm.backend.dto.SurveyFwProgressDTO;
import com.lefancrm.backend.dto.SurveyProgressDto;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/fw/")
public class BackendFwController extends BackendBaseController{

    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        return new ModelAndView("/fw/list",model);
    }

    @RequestMapping(value = "operate")
    public String operate(HttpServletRequest req, HttpServletResponse rsp){
        Map<String,Object> appendMap = new HashMap<String, Object>();
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_OPERATE_FW_CASE, appendMap, req, rsp);
    }

    @RequestMapping(value = "/info")
    public ModelAndView info(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        Map paramMap = new HashMap();
        paramMap.put("fwId",req.getParameter("fwId"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyFwCaseDTO>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INFO_FW_CASE, paramMap, req);
        SurveyFwCaseDTO surveyFwCase = (SurveyFwCaseDTO) apiFinalResponse.getResults();
        model.put("surveyFwCase",surveyFwCase);
        return new ModelAndView("/fw/info",model);
    }

    @RequestMapping(value = "getDetail")
    public String getDetail(HttpServletRequest req, HttpServletResponse rsp){
        Map<String,Object> appendMap = new HashMap<String, Object>();
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_LIST_FW_CASE, appendMap, req, rsp);
    }


    @RequestMapping(value = "/progress")
    public ModelAndView progress(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        TypeToken<ApiFinalResponse<List<SurveyFwProgressDTO>>> typeToken = new TypeToken<ApiFinalResponse<List<SurveyFwProgressDTO>>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_AJAX_FW_CASE, null, req);
        model.put("progressList",apiFinalResponse.getResults());
        model.put("fwId",req.getParameter("fwId"));
        return new ModelAndView("/fw/progress",model);
    }
}
