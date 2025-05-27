package com.lefancrm.backend.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.BusUserRoleDto;
import com.lefancrm.backend.dto.SurveyFranchiseeDto;
import com.lefancrm.backend.dto.SurveyInvestigatorDto;
import com.lefancrm.backend.dto.SurveyUserClockDto;
import com.lefancrm.backend.dto.feere.SurveyInvestigatorReInfoDto;
import com.lefancrm.backend.dto.feere.SurveyReInfoDto;
import com.lefancrm.backend.util.DateUtil;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping(value = "/fee")
public class BackendReInfoController extends BackendBaseController {
    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp) {
        String menuCode = req.getParameter("menuCode");
        Map model = new HashMap();
        model.put("menuCode", menuCode);
        model.put("reName",req.getParameter("reName"));
        model.put("reState",req.getParameter("reState"));
        model.put("sortField",req.getParameter("sortField"));//快捷查询
        model.put("sortType",req.getParameter("sortType"));//快捷查询
        model.put("createTime",req.getParameter("createTime"));//快捷查询
        model.put("pageSize",req.getParameter("pageSize"));
        TypeToken typeToken = null;
        ApiFinalResponse apiFinalResponse = null;
        if ("manager".equals(menuCode)){//费用报销管理
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyReInfoDto>>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FEE_RE_MANAGER_NEW, null, req);
            req.setAttribute("apiRsp", apiFinalResponse);
            return new ModelAndView("/fee/feeReManagerNew",model);
        }else if ("list".equals(menuCode)){//费用报销清单
            String searchCode = req.getParameter("searchCode");
            String reState = req.getParameter("reState");
            String reStates = req.getParameter("reStates");
            model.put("searchCode",searchCode);
            model.put("show",false);
            model.put("rId",req.getParameter("rId"));
            model.put("reState", "".equals(reState) ? null : reState);
            model.put("reStates", "".equals(reStates) ? null : reStates);
            model.put("sortField",req.getParameter("sortField"));
            model.put("sortType",req.getParameter("sortType"));
            model.put("reName",req.getParameter("reName"));

            typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_CUR_USER_ROLES, null, req);
            List<BusUserRoleDto> userRoles = (List<BusUserRoleDto>)apiFinalResponse.getResults();
            Boolean orgRole = isRoleUser(userRoles,104L),finance = isRoleUser(userRoles,23L), reSupervisor = isRoleUser(userRoles, 110L);
            model.put("orgRole",orgRole);
            model.put("finance",finance);
            model.put("reSupervisor",reSupervisor);
            if ("reimbursement-manager-list".equals(searchCode)){//不显示查询条件
                model.put("show",true);
                //多选
                model.put("investigators",req.getParameter("investigators")==null?"":req.getParameter("investigators"));
                model.put("surveyOrgIds",req.getParameter("surveyOrgIds")==null?"":req.getParameter("surveyOrgIds"));
                //获取机构
                Map<String, Object> appendMap = new HashMap<String, Object>();
                appendMap.put("surveyCode", "franchisee");
                appendMap.put("menuType", 1);
                if (!finance && !reSupervisor){
                    appendMap.put("btnCode","myInfo");
                }
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
                List<SurveyFranchiseeDto> franseList =  (List<SurveyFranchiseeDto>)apiFinalResponse.getResults();
                JSONObject result = new JSONObject();
                result.put("franseList",franseList);
                //获取调查员
                Long curOrgId = null;
                if (!finance && !reSupervisor){
                    List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>)apiFinalResponse.getResults();
                    if (franchisees.size() > 0) {
                        curOrgId = franchisees.get(0).getId();
                    }
                }
                appendMap = new HashMap<String, Object>();
                appendMap.put("surveyCode","investigator");
                appendMap.put("btnCode",3000);
                appendMap.put("surveyOrgIds",curOrgId);
                typeToken = new TypeToken<ApiFinalResponse<List<SurveyInvestigatorDto>>>() {};
                apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, null);
                List<SurveyInvestigatorDto> investigator =  (List<SurveyInvestigatorDto>)apiFinalResponse.getResults();
                result.put("investigator",investigator);
                req.setAttribute("list",result.toJSONString());
            }
            model.put("currentUserId",getSessionAdminId(req));
            return new ModelAndView("/fee/feeReListNew",model);
        }else if ("preList".equals(menuCode)){

            //多选
            model.put("investigators", req.getParameter("investigators") == null ? "" : req.getParameter("investigators"));
            model.put("surveyOrgIds", req.getParameter("surveyOrgIds") == null ? "" : req.getParameter("surveyOrgIds"));
            model.put("preState", req.getParameter("preState") == null ? "" : req.getParameter("preState"));

            typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_CUR_USER_ROLES, null, req);
            List<BusUserRoleDto> userRoles = (List<BusUserRoleDto>) apiFinalResponse.getResults();
            Boolean finance = isRoleUser(userRoles, 23L);//是否是财务角色
            //获取机构
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode", "franchisee");
            appendMap.put("menuType", 1);
            if (!finance) {
                appendMap.put("btnCode", "myInfo");
            }
            typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            model.put("franchisee", apiFinalResponse.getResults());
            model.put("finance",finance);
//            typeToken = new TypeToken<ApiFinalResponse<String>>() {};
//            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_PRE_FEE_RE_LIST, null, req);
//            JSONObject jsonObject = JSON.parseObject(apiFinalResponse.getResults().toString());
//            req.setAttribute("apiRsp", jsonObject);
            return new ModelAndView("/fee/preFeeReList",model);
        }else if ("re_clock_list".equals(menuCode)){
            typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_CUR_USER_ROLES, null, req);
            List<BusUserRoleDto> userRoles = (List<BusUserRoleDto>)apiFinalResponse.getResults();
            Boolean orgRole = isRoleUser(userRoles,104L),invRole = isRoleUser(userRoles,50L);
            model.put("orgRole",orgRole);
            model.put("invRole",invRole);
            model.put("reInfoId",req.getParameter("reInfoId"));
            model.put("clockIds",req.getParameter("clockIds"));
            model.put("type",req.getParameter("type"));
            model.put("reState",req.getParameter("reState"));
            model.put("preId",req.getParameter("preId"));
            model.put("userId",req.getParameter("userId"));
            return new ModelAndView("/fee/reClockList",model);
        }else if("caseClockDetails".equals(menuCode)){
            String createTime =req.getParameter("createTime");
            String formatStr = DateUtil.getCSTStr(createTime);
            String dateTime=DateUtil.getFirstMonth(formatStr,-1);
            model.put("userId",req.getParameter("userId"));
            model.put("dateTime",dateTime);
            return new ModelAndView("/survey/clock/list",model);
        }else if("userClockDetails".equals(menuCode)){
            String startTime =req.getParameter("startTime");
            if (StringUtils.isNotBlank(startTime)){
                startTime = DateUtil.getFirstMonth(startTime,-1);
            }
            model.put("userId",req.getParameter("userId"));
            model.put("orgId",req.getParameter("orgId"));
            model.put("dateTime",startTime);
            return new ModelAndView("/survey/clock/list",model);
        }else if ("preClockDetails".equals(menuCode)){
            String dateTime =req.getParameter("createTime");
            model.put("userId",req.getParameter("userId"));
            model.put("dateTime",dateTime);
            model.put("orgId",req.getParameter("orgId"));
            return new ModelAndView("/survey/clock/list",model);
        }else if ("preClockList".equals(menuCode)){
            String dateTime =req.getParameter("createTime");
            model.put("userId",req.getParameter("userId"));
//            model.put("dateTime",dateTime);
            model.put("orgId",req.getParameter("orgId"));

            String startTime =req.getParameter("startTime");
            String endTime = req.getParameter("endTime");
            model.put("startTime",startTime);
            model.put("endTime",endTime);

            return new ModelAndView("/survey/clock/clockInfoList",model);
        }
        return null;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(HttpServletRequest req, HttpServletResponse rsp){
        if (StringUtils.isNotBlank(req.getParameter("btnCode"))){
            Map model = new HashMap();
            model.put("id",req.getParameter("id"));
            model.put("btnCode",req.getParameter("btnCode"));
            model.put("cityinDrivingMoney",req.getParameter("cityinDrivingMoney"));
            model.put("medicalHistoryMoney",req.getParameter("medicalHistoryMoney"));
            model.put("accommodatioMoney",req.getParameter("accommodatioMoney"));
            return new ModelAndView("/fee/feeAddBill",model);
        }
        return new ModelAndView("/fee/feeReAdd");
    }

    @RequestMapping(value = "/operate")
    public String operate(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String btnCode = req.getParameter("btnCode");
        String operateType = req.getParameter("operateType");
        if ("sued".equals(btnCode)){//下发报销清单操作提交
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FEE_RE_SUED, appendMap, req, rsp);
        }
        else if ("addPre".equals(btnCode)){//新增予报销清单提交
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_PRE_FEE_RE_SUED, appendMap, req, rsp);
        }
        if (StringUtils.isNotBlank(operateType) && "pre".equals(operateType)){//予报销操作
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_PRE_FEE_RE_OPERATE, appendMap, req, rsp);
        }
        if ("reNew".equals(operateType)){ //新的报销操作
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FEE_RE_OPERATE_NEW, appendMap, req, rsp);
        }else if ("userClockRe".equals(operateType)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FEE_RE_CLOCK_OPERATE_NEW, appendMap, req, rsp);
        }
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FEE_RE_OPERATE, appendMap, req, rsp);
    }

    @RequestMapping(value = "/getData")
    public String getData(HttpServletRequest req, HttpServletResponse rsp) {
        String btnCode = req.getParameter("btnCode");
        Map<String, Object> appendMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(btnCode)){
            if ("preSelectUser".equals(btnCode)){
                return this.callApiAndOutput( BackendApiMethodEnum.BACKEND_PRE_FEE_ORG_USRE_LIST, appendMap, req,rsp);
            }else if ("reClockList".equals(btnCode)){
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_RE_CLOCK_LIST, appendMap, req, rsp);
            }else if ("reInfoList".equals(btnCode)){
               return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FEE_RE_LIST_NEW, appendMap, req,rsp);
            }else if ("preInfoList".equals(btnCode)){
                return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_PRE_FEE_RE_LIST, appendMap, req,rsp);
            }
        }
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CAN_BE_SUED_LIST, appendMap, req, rsp);
    }
}
