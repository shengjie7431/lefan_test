package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import com.lefancrm.base.web.WebHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2019/01/04.
 * 调查调查方
 */
@Controller
@RequestMapping(value = "/surveyFranchisee")
public class BackendSurveyFranchiseeController extends BackendBaseController{

    /**
     * 选择调查方
     * @param req
     * @param rsp
     * 应用场景：1、新增调查机构，选择父级  2、调查方机构：名下人员：更改机构
     * @return
     */
    @RequestMapping("/selectFranchisee")
    public ModelAndView selectFranchisee(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();


        model.put("pageSize",req.getParameter("pageSize"));

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
        model.put("apiRsp",apiFinalResponse);
        String name = req.getParameter("name");
        model.put("name",name==null?"":name);
        model.put("surveyCode",req.getParameter("surveyCode"));
        model.put("btnCode",req.getParameter("btnCode"));
        model.put("btnCode",req.getParameter("btnCode"));
        model.put("userId",req.getParameter("userId"));
        return new ModelAndView("/survey/surveyFranchisee/franchisee",model);
    }

    /**
     * 确定调查方
     *
     */
    @RequestMapping(value = "/choiceFranchisee")
    public String choiceFranchisee(HttpServletRequest req, HttpServletResponse rsp) {
        Map param = new HashMap();
        param.put("surveyCode",req.getParameter("surveyCode"));
        param.put("type",req.getParameter("type"));
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_SURVEY_INFO, param, req);
        req.getSession().setAttribute("json",json);
        return WebHelper.outputJson(json, rsp);
    }

    /**
     * 获取 符合条件的调查方
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/selectInvestigator")
    public ModelAndView selectInvestigator(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();

        model.put("pageSize",req.getParameter("pageSize"));

        String btnCode = req.getParameter("btnCode");

        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("btnCode", btnCode);

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INVESTIGATOR, appendMap, req);
        model.put("apiRsp",apiFinalResponse);
        String realName = req.getParameter("realName");
        String tel = req.getParameter("tel");
        model.put("realName",realName==null?"":realName);
        model.put("tel",tel==null?"":tel);
        model.put("franchiseeId",req.getParameter("id"));
        model.put("btnCode",btnCode);
        return new ModelAndView("/survey/surveyFranchisee/investigatorList",model);
    }


    /**
     * 认证页面
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/entrust")
    public ModelAndView entrust(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();

        model.put("userId",req.getParameter("userId"));
        model.put("userName",req.getParameter("userName"));
        model.put("btnCode",req.getParameter("btnCode"));
        model.put("surveyCode",req.getParameter("surveyCode"));
        model.put("franchiseeId",req.getParameter("consignorId"));
        //调查调查方信息
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode","franchisee");
        appendMap.put("id", req.getParameter("consignorId"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyFranchiseeDto>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
        model.put("surveyFranchisee",apiFinalResponse.getResults());

        //调查员等级信息
        typeToken = new TypeToken<ApiFinalResponse<List<SurveyLevelDto>>>() {};
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LEVEL_LIST, null, null);
        model.put("surveyLevels", apiFinalResponse.getResults());

        //地区信息
        appendMap = new HashMap<String, Object>();
        appendMap.put("parentId",0);
        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
        Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
        }.getType();
        ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
        req.setAttribute("apiRsp", apiRsp);

        //调查员信息（如二次认证，需将之前的数据带出）
        appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode","investigatorUser");
        appendMap.put("userId", req.getParameter("userId"));
        typeToken = new TypeToken<ApiFinalResponse<SurveyInvestigatorDto>>() {};
        apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
        model.put("surveyInvestigator",apiFinalResponse.getResults());
        String surveyCode= req.getParameter("surveyCode");
        if(surveyCode.equals("investigator")){
            //查询出所有符合带教老师的人
            appendMap=new HashMap<>();
            appendMap.put("isNewPeople","1");
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {};
            apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INVESTIGATOR_ALLLIST, appendMap, null);
            List<SurveyInvestigatorDto> allInvestigatorsList=(List<SurveyInvestigatorDto>)apiFinalResponse.getResults();
            model.put("allInvestigatorsList",allInvestigatorsList);
        }
        return new ModelAndView("/survey/surveyFranchisee/entrust",model);
    }


    /**
     * 领域list（使用场景：1、调查员认证页面）
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/selectBusinessType")
    public ModelAndView selectBusinessType(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();

        //获取领域集合
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyBusinessTypeDto>>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_BUSINESS_TYPE_LIST, null, req);
        model.put("buses",apiFinalResponse.getResults());
        return new ModelAndView("/survey/surveyFranchisee/businessType",model);
    }

    /**
     * 确定领域（使用场景：1、调查员认证页面）
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/choiceBusinessType")
    public String choiceBusinessType(HttpServletRequest req, HttpServletResponse rsp,String buss){
        Map param = new HashMap();
        param.put("surveyCode",req.getParameter("surveyCode"));
        param.put("buss",buss);
        String retJson = "";
//        String json = this.callApi(BackendApiMethodEnum.BACKEND_SURVEY_LEVEL_NAMES, param, req);
        String json = buss;
        req.getSession().setAttribute("json",json);
        return WebHelper.outputJson(json, rsp);
    }


    /**
     * 选择覆盖区域（使用场景：1、调查员认证页面）
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/selectIncludeArea")
    public ModelAndView selectIncludeArea(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        String selectionType=req.getParameter("selectionType");
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap = new HashMap<String, Object>();
        appendMap.put("parentId",0);
        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
        Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
        }.getType();
        ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
        req.setAttribute("apiRsp", apiRsp);
        model.put("selectionType", selectionType);

        return new ModelAndView("/survey/surveyFranchisee/area",model);
    }

    /**
     * 确定覆盖区域
     *
     */
    @RequestMapping(value = "/choiceIncludeArea")
    public String choiceIncludeArea(HttpServletRequest req, HttpServletResponse rsp,String areas) {
        Map param = new HashMap();
        param.put("surveyCode",req.getParameter("surveyCode"));
        String retJson = "";
        String json = areas;
        req.getSession().setAttribute("json",json);
        return WebHelper.outputJson(json, rsp);
    }

    /**
     * 任务类型list（使用场景：1、调查员认证页面）
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/selectIncludeTask")
    public ModelAndView selectIncludeTask(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();

        //获取任务类型集合
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode","taskInfo");
        appendMap.put("menuType",1);//不分页
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
        model.put("tasks",apiFinalResponse.getResults());
        return new ModelAndView("/survey/surveyFranchisee/taskInfo",model);
    }

    /**
     * 确定任务类型（使用场景：1、调查员认证页面）
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/choiceTaskInfo")
    public String choiceTaskInfo(HttpServletRequest req, HttpServletResponse rsp,String buss){
        Map param = new HashMap();
        param.put("surveyCode",req.getParameter("surveyCode"));
        param.put("buss",buss);
        String retJson = "";
        String json = buss;
        req.getSession().setAttribute("json",json);
        return WebHelper.outputJson(json, rsp);
    }
}
