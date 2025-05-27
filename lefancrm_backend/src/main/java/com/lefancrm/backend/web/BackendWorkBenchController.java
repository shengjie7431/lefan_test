package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.util.DateUtil;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/work")
public class BackendWorkBenchController extends BackendBaseController {

    @RequestMapping(value = "/queryWork")
    public ModelAndView queryWork(HttpServletRequest req,HttpServletResponse rsp){
        Object object = req.getSession().getAttribute("adminDto");
        UserInfo userInfo = (UserInfo)object;
        Map model = new HashMap();
        if (isSurveyRole(userInfo.getBusUserRoleIds())){
            if (userInfo.getBusUserRoleIds().contains(58L)) {//机构复核--机构报表
                model = returnModel("org",model,req);
                return new ModelAndView("/survey/report/orgIndex",model);
            }else if (userInfo.getBusUserRoleIds().contains(50L)) {//调查员--调查员报表
                Map findMap=new HashMap();
                findMap.put("homePage",1);
                TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyInvestigatorDto>>(){};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_INVESTIGATOR_SELECTBYUSER, findMap, req);
                SurveyInvestigatorDto surveyInvestigatorDto=(SurveyInvestigatorDto)apiFinalResponse.getResults();
                if(surveyInvestigatorDto.getBusType() != null && !surveyInvestigatorDto.getBusType().equals("")){
                    if(surveyInvestigatorDto.getBusType()==1){
                        model = returnModel("investigatorReport",model,req);
                        model.put("organizationType",surveyInvestigatorDto.getBusType());


                        Map appendMap = new HashMap<String, Object>();
                        Map params=new HashMap();
                        appendMap.put("menuType",1); //不分页
                        appendMap.put("orgAttr",2);//公司属性（1：保险公司；2、互助机构）
                        appendMap.put("surveyCode","investigator");
                        appendMap.put("btnCode",5000);
                        appendMap.put("userId",userInfo.getUserId());
                        typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {};
                        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, null);
                        List<SurveyInvestigatorDto> investigators = (List<SurveyInvestigatorDto>) apiFinalResponse.getResults();
                        model.put("investigators",investigators);
                        params.put("investigatorsJson",JsonUtil.objectToJson(investigators));
                        model.put("params",params);

                        appendMap = new HashMap<String, Object>();
                        appendMap.put("menuType",1); //不分页
                        appendMap.put("surveyCode","franchisee");
                        appendMap.put("btnCode","myHzInfo");
                        appendMap.put("scoreRole","investigators");
                        appendMap.put("level",1);//仅获取顶级的调查机构
                        appendMap.put("enable",0);//查询启用状态的调查方机构
                        typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
                        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                        List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
                        franchisees = franchisees.parallelStream().filter(e ->e.getBusType() != null && e.getBusType() != 2).collect(Collectors.toList());
                        model.put("franchisees",franchisees);
                        params.put("franchiseesJson",JsonUtil.objectToJson(franchisees));


                        return new ModelAndView("/survey/hzReport/investigatorReportList",model);
                    }
                }
                model = returnModel("survey",model,req);
                return new ModelAndView("/survey/report/surveyIndex",model);
            }else if (userInfo.getBusUserRoleIds().contains(51L)) {//委托机构--保司报表
                model = returnModel("entrust",model,req);
                return new ModelAndView("/survey/report/entrustIndex",model);
            }else{
                TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyNumberDto>>(){};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_MANAGER_SURVEY, null, req);
                SurveyNumberDto dto = (SurveyNumberDto)apiFinalResponse.getResults();
                model.put("dto",dto);
                return new ModelAndView("/survey/case/manager",model);
            }
        }else{
//            Long roleId = 0L;
//            Long userId = userInfo.getUserId();
//            //管理员机构 //cc主管    //市场总监  //业务小组长
//            if(userInfo.getBusUserRoleIds().contains(19L)){
//                roleId = 19L;
//            }else if(userInfo.getBusUserRoleIds().contains(1L)){
//                roleId = 1L;
//            }else if(userInfo.getBusUserRoleIds().contains(17L)){
//                roleId = 17L;
//            }else if(userInfo.getBusUserRoleIds().contains(20L)){
//                roleId = 20L;
//            }else if(userInfo.getBusUserRoleIds().contains(2L)){
//                roleId = 2L;
//            }
//            Map<String,Object> paramMap = new HashMap<>();
//            paramMap.put("roleId", roleId);
//            paramMap.put("userId", userId);
//            String json = this.callApi(BackendApiMethodEnum.BACKEND_QUERY_WORK_BENCH, paramMap, req);
//            Type type = new TypeToken<ApiFinalResponse<WorkBenchDto>>() {
//            }.getType();
//            ApiFinalResponse<WorkBenchDto> apiRsp = JsonUtil.jsonToObject(json, type);
//            model.put("workBench", apiRsp.getResults());
            return new ModelAndView("/work/workBench",model);
        }
    }

    private Boolean isSurveyRole(List<Long> roels){
        if (roels.contains(50L)) {
            return true;
        }
        if (roels.contains(51L)) {
            return true;
        }
        if (roels.contains(52L)) {
            return true;
        }
        if (roels.contains(53L)) {
            return true;
        }
        if (roels.contains(54L)) {
            return true;
        }
        if (roels.contains(55L)) {
            return true;
        }
        if (roels.contains(56L)) {
            return true;
        }
        if (roels.contains(57L)) {
            return true;
        }
        if (roels.contains(58L)) {
            return true;
        }
        if (roels.contains(59L)) {
            return true;
        }
        if (roels.contains(67L)) {
            return true;
        }
        if (roels.contains(58L)) {
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/queryWorkDate")
    public String queryWorkBenchByDate(HttpServletRequest req,HttpServletResponse rsp , Model model){

        Object object = req.getSession().getAttribute("adminDto");
        UserInfo userInfo = (UserInfo)object;
        Long roleId = 0L;
        Long userId = userInfo.getUserId();
        //管理员机构 //cc主管    //市场总监  //业务小组长
        if(userInfo.getBusUserRoleIds().contains(19L)){
            roleId = 19L;
        }else if(userInfo.getBusUserRoleIds().contains(1L)){
            roleId = 1L;
        }else if(userInfo.getBusUserRoleIds().contains(17L)){
            roleId = 17L;
        }else if(userInfo.getBusUserRoleIds().contains(20L)){
            roleId = 20L;
        }else if(userInfo.getBusUserRoleIds().contains(2L)){
            roleId = 2L;
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("roleId", roleId);
        paramMap.put("userId", userId);
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_QUERY_WORK_BENCH_DATE, paramMap, req, rsp);
    }

    private Map returnModel(String menuCode,Map model,HttpServletRequest req) {
        String startTime = null;
        String endTime = null;
        String searchType = req.getParameter("searchType") == null ? "upMonth" : req.getParameter("searchType");//默认上月
        if (!"date".equals(searchType)){//非时间段查询
            StringBuilder builderStart = new StringBuilder("");
            StringBuilder builderEnd = new StringBuilder("");
            DateUtil.convertTimeBySearchType(builderStart, builderEnd, searchType);
            startTime = builderStart.toString();
            endTime = builderEnd.toString();
            if ("all".equals(searchType)){
                startTime = "2019-01-01";
                endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            }
        }else{
            startTime = req.getParameter("startTime");
            endTime = req.getParameter("endTime");
        }

        model.put("searchType",searchType);
        model.put("startTime",startTime);
        model.put("endTime",endTime);
        model.put("menuCode",menuCode);

        Map<String, Object> appendMap = new HashMap<String, Object>();
        //委托方机构
        appendMap.put("menuType",1); //不分页
        appendMap.put("surveyCode","consignor");//查询“狄大人终审人员对应的委托方”，改变surveyCode值
        if ("entrust".equals(menuCode)) { //委托机构
            appendMap.put("btnCode", "myInfo");//仅获取自身的委托机构
        }
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
        List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
        model.put("consignors",consignors);
        model.put("entrustOrgId",req.getParameter("entrustOrgId"));

        //调查机构
        appendMap = new HashMap<String, Object>();
        appendMap.put("menuType",1); //不分页
        appendMap.put("surveyCode","franchisee");//查询调查机构，改变surveyCode值
        if("org".equals(menuCode)) { //调查机构
            appendMap.put("btnCode", "myInfo");//仅获取自身的调查机构
        }
        typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
        List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
        model.put("franchisees",franchisees);
        model.put("surveyOrgId",req.getParameter("surveyOrgId"));

        if ("entrust".equals(menuCode)){

        }else if("org".equals(menuCode)){
            model.put("checkType",req.getParameter("checkType"));

        }else if("survey".equals(menuCode)){
            //自身调查员
            appendMap = new HashMap<String, Object>();
            appendMap.put("menuType",1); //不分页
            appendMap.put("surveyCode","investigator");//查询调查机构，改变surveyCode值
            appendMap.put("btnCode","myInfo");//仅获取自身的调查员数据
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyInvestigatorDto> investigatorDtos = (List<SurveyInvestigatorDto>) apiFinalResponse.getResults();
            model.put("investigatorDtos",investigatorDtos);
            model.put("surveyUserId",req.getParameter("surveyUserId"));
            model.put("checkType",req.getParameter("checkType"));
        }

        return model;
    }
}
