package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2018/1/29.
 */
@Controller
public class BackendCaseRiskController extends  BackendBaseController{

    @RequestMapping(value = "/case/risk/caseRiskList")
    public String caseRiskList(HttpServletRequest req, Model model) {
        String riskType = req.getParameter("riskType");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoDto>>>() {};
        ApiFinalResponse apiResult= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_RISK_LIST, null, req);
        model.addAttribute("riskType",riskType);
        model.addAttribute("apiRsp",apiResult);
        return "/case/risk/caseRiskList";
    }

    @RequestMapping(value = "/case/risk/editRiskState")
    public String editRiskState(HttpServletRequest req,HttpServletResponse resp) {
        String riskType = req.getParameter("riskType");
       req.setAttribute("riskType",riskType);
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_EDIT_RISK_STATE,null,req,resp);
    }

    @RequestMapping(value = "/case/risk/caseRiskReson")
    public String caseRiskReson(HttpServletRequest req, Model model) {
        String riskType = req.getParameter("riskType");
        String id = req.getParameter("id");
        String state = req.getParameter("state");
        model.addAttribute("riskType",riskType);
        model.addAttribute("id",id);
        model.addAttribute("state",state);
        return "/case/risk/caseRiskReson";
    }

    /**
     * 案件理赔测算历史记录
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/case/risk/selectPaymentEstimateApply")
    public ModelAndView selectPaymentEstimateApply(HttpServletRequest req, HttpServletResponse rsp) {
        /*String orgName = req.getParameter("orgName");
        String orgTel = req.getParameter("orgTel");*/
        String caseId = req.getParameter("caseId");
        String caseType = req.getParameter("type");
        String userName = req.getParameter("userName");
        String userPhone = req.getParameter("userPhone");
        String json = this.callApi(BackendApiMethodEnum.BACKEND_SELECT_PAYMENTESTIMATE,null, req);
        Type type = new TypeToken<ApiFinalResponse<List<PaymentEstimateApplyDto>>>() {
        }.getType();
        ApiFinalResponse<List<PaymentEstimateApplyDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        Map<String, Object> model = new HashMap<>();
        model.put("apiRsp", apiRsp);
        model.put("caseId",caseId==null?null:caseId);
        model.put("caseType",caseType==null?null:caseType);
        model.put("userName",userName==null?null:userName);
        model.put("userPhone",userPhone==null?null:userPhone);

        return new ModelAndView("/case/risk/paymentEstimateApplyDetail",model);
    }

    @RequestMapping(value = "/case/risk/paymentEstimateReportList")
    public ModelAndView paymentEstimateReportList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("caseNo",req.getParameter("caseNo"));
        appendMap.put("caseId",req.getParameter("caseId"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<PaymentEstimateReportDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_PAYMENT_ESTIMATE_REPORT_LIST, appendMap, req);
        List<PaymentEstimateReportDto> paymentEstimateReportDtos = (List<PaymentEstimateReportDto>) apiFinalResponse.getResults();
        Map model = new HashMap();
        model.put("paymentEstimateReportDtos",paymentEstimateReportDtos);
        model.put("caseNo",req.getParameter("caseNo"));
        model.put("id",req.getParameter("id"));
        model.put("caseId",req.getParameter("caseId"));
        model.put("negotiateState",req.getParameter("negotiateState"));

        Map map = new HashMap();
        if(req.getParameter("id") !=null){
            map.put("id",req.getParameter("id"));
        }else {
            if (paymentEstimateReportDtos.size() > 0){
                map.put("id", paymentEstimateReportDtos.get(0).getPaymentEstimateId());
            }
        }
        typeToken = new TypeToken<ApiFinalResponse<PaymentEstimateApplyDto>>(){};
        apiFinalResponse  = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SELECT_PAYMENTESTIMATE_NEW,map, req);
        model.put("apply",apiFinalResponse.getResults());
        //伤残测算列表中- 赔付测算案件- 查看测算报告（控制按钮）
        model.put("mType",req.getParameter("mType"));
        return new ModelAndView("/case/risk/paymentEstimateReportList",model);
    }

    @RequestMapping(value = "/case/risk/caseClaim")
    public ModelAndView caseClaim(HttpServletRequest req, HttpServletResponse rsp) {
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseMediationClaimDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CLAIM, null, req);
        Map<String,Object> model = new HashMap<>();
        model.put("apiRes",apiFinalResponse);
        return new ModelAndView("/case/risk/caseClaim",model);
    }
    @RequestMapping(value = "/case/risk/caseClaimReport")
    public ModelAndView caseClaimReport(HttpServletRequest req, HttpServletResponse rsp) {
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<PaymentEstimateReportDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CLAIM_REPORT, null, req);
        List<PaymentEstimateReportDto> paymentEstimateReportDtos = (List<PaymentEstimateReportDto>) apiFinalResponse.getResults();
        Map model = new HashMap();
        model.put("paymentEstimateReportDtos",paymentEstimateReportDtos);
        return new ModelAndView("/case/risk/paymentEstimateReportList",model);
    }

    @RequestMapping(value = "/case/risk/paymentEstimateReportEdit")
    public String paymentEstimateReportEdit(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_PAYMENT_ESTIMATE_REPORT_EDIT,null,req,rsp);
    }

    @RequestMapping(value = "/case/risk/queryCaseNegotiateState")
    public ModelAndView queryCaseNegotiateState(HttpServletRequest req, HttpServletResponse rsp) {
        String type = req.getParameter("type");
        String caseState = req.getParameter("caseState");
        String orgId = req.getParameter("orgId");
        String caseNo = req.getParameter("caseNo");
        String caseName = req.getParameter("caseName");
        String caseTel = req.getParameter("caseTel");
        String isTestcase = req.getParameter("isTestcase");
        String negotiateState = req.getParameter("negotiateState");

        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoDto>>>() {};
        ApiFinalResponse apiResult= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_NEGOTIATE, null, req);
        model.put("apiRsp",apiResult);

        //查询该风控人员下的机构list（不包含小组）
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_ORG_INFO_LIST_BY_RISKUSER, null, req);
        List<OrgInfoDto> orgInfo = (List<OrgInfoDto>) apiFinalResponse1.getResults();

        model.put("orgInfo",orgInfo);
        if(negotiateState == ""){
            model.put("negotiateState",-1);
        }else{
            model.put("negotiateState",negotiateState==null?-1:negotiateState);
        }
        model.put("type",type==null?"":type);
        model.put("caseState",caseState==null?"":caseState);
        model.put("orgId",orgId==null?"":orgId);
        model.put("caseNo",caseNo==null?"":caseNo);
        model.put("caseName",caseName==null?"":caseName);
        model.put("caseTel",caseTel==null?"":caseTel);
        //判断登录人是不是测试人员
        TypeToken typeToken2 = new TypeToken<ApiFinalResponse<UserInfo>>() {};
        ApiFinalResponse apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_USER_INFO, null, req);
        UserInfo userInfoDto = (UserInfo)apiFinalResponse2.getResults();
        if(isTestcase == null){
            if(userInfoDto.getIsTester() ==1){
                //测试人员
                model.put("isTestcase",1);
            }else{
                model.put("isTestcase",0);
            }
        }else if(isTestcase ==""){
            model.put("isTestcase",-1);
        }else{
            model.put("isTestcase",isTestcase);
        }
        return new ModelAndView("/case/risk/caseNegotiateState",model);
    }

    @RequestMapping(value = "/case/risk/queryCaseNegotiateStateInfo")
    public ModelAndView queryCaseNegotiateStateInfo(HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        model.put("menuType",req.getParameter("menuType"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseCenterInfoNew>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_NEW_INFO, null, req);
        CaseCenterInfoNew caseCenterInfoNewDto = (CaseCenterInfoNew)apiFinalResponse.getResults();
        model.put("dto",caseCenterInfoNewDto);

        typeToken = new TypeToken<ApiFinalResponse<PaymentEstimateInquiryDto>>(){};
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_PAY_INQUIRY_INFO,null,req);
        model.put("payEstimateInquiry",apiFinalResponse.getResults());


        //获取跟踪记录
        Map paramMap = new HashMap();
        paramMap.put("caseId",req.getParameter("id"));
        typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoFollowDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_FOLLOW_BY_CASEID, paramMap, req);
        model.put("caseFollows",apiFinalResponse.getResults());
        return new ModelAndView("/case/risk/queryCaseNegotiateStateInfo",model);
    }

    @RequestMapping(value = "/case/risk/negotiateState")
    public String negotiateState(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_NEGOTIATE_STATE,null,req,rsp);
    }

    @RequestMapping(value = "/case/risk/negotiateReason")
    public String negotiateReason(HttpServletRequest req, Model model) {
        String id = req.getParameter("id");
        String state = req.getParameter("state");
        model.addAttribute("id",id);
        model.addAttribute("state",state);
        return "/case/risk/negotiateReason";
    }

    @RequestMapping(value = "/case/risk/issuanceReason")
    public String issuanceReason(HttpServletRequest req, Model model) {
        String id = req.getParameter("id");
        String state = req.getParameter("state");
        model.addAttribute("id",id);
        model.addAttribute("state",state);
        return "/case/risk/issuanceReason";
    }

    @RequestMapping(value = "/case/risk/issuanceState")
    public String issuanceState(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_ISSUANCE_STATE,null,req,rsp);
    }
}
