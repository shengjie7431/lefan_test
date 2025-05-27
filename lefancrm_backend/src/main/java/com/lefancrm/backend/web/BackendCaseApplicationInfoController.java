package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by wangwei on 2018/05/14.
 */
@Controller
@RequestMapping(value = "/caseApplication")
public class BackendCaseApplicationInfoController extends BackendBaseController {

    @RequestMapping(value = "/caseApplicationInfoList")
    public ModelAndView caseApplicationInfoList(HttpServletRequest req , HttpServletResponse rsp){
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        String type = req.getParameter("type");
        String state = req.getParameter("state");
        String caseNo = req.getParameter("caseNo");
        String userName = req.getParameter("userName");
        String isFined = req.getParameter("isFined");
        String promoterName = req.getParameter("promoterName");
        String promoterPhone = req.getParameter("promoterPhone");
        String isTestcase = req.getParameter("isTestcase");

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseApplicationInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_APPLICATION_INFO_LIST, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("type",type==null?"":type);
        model.put("state",state==null?"":state);
        model.put("caseNo",caseNo==null?"":caseNo);
        model.put("userName",userName==null?"":userName);
        if(isFined == ""){
            model.put("isFined",-1);
        }else{
            model.put("isFined",isFined==null?-1:isFined);
        }
        model.put("promoterName",promoterName==null?"":promoterName);
        model.put("promoterPhone",promoterPhone==null?"":promoterPhone);

        //判断登录人是不是测试人员
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<UserInfo>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_USER_INFO, null, req);
        UserInfo userInfoDto = (UserInfo)apiFinalResponse1.getResults();
        if(isTestcase == null){
            if(userInfoDto.getIsTester() ==1){
                //测试人员
                model.put("isTestcase",1);
            }else{
                model.put("isTestcase",0);
            }
        }else if(isTestcase == ""){
            model.put("isTestcase",-1);
        }else {
            model.put("isTestcase", isTestcase == null ? "" : isTestcase);
        }

        model.put("page", page);
        return new ModelAndView("/caseApplication/caseApplicationInfoList",model);
    }

    @RequestMapping(value = "/caseApplicationView")
    public ModelAndView caseApplicationView(HttpServletRequest req , HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();

        //代理申请案件
        if ("1".equals(req.getParameter("type"))) {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<AgentApply>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_AGENT_APPLY_INFO_BY_AGENTNO, null, req);
            AgentApply agentApplyInfo = (AgentApply) apiFinalResponse.getResults();
            model.put("agentApplyInfo", agentApplyInfo);
            return new ModelAndView("/caseApplication/agentApplyInfoView",model);
        }
        //贷款申请案件
        if ("2".equals(req.getParameter("type"))) {
            TypeToken typeToken1 = new TypeToken<ApiFinalResponse<LoanApplication>>() {};
            ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_LOAN_APPLICATION_BY_LOANNO, null, req);
            LoanApplication loanApplication = (LoanApplication) apiFinalResponse1.getResults();
            model.put("loanApplication", loanApplication);
            return new ModelAndView("/caseApplication/loanApplicationView",model);
        }

        return new ModelAndView("/caseApplication/agentApplyInfoView",model);
    }

    /**
     * 代理申请案件--审核通过
     *
     */
    @RequestMapping(value = "/editAgentApplyInfoState")
    public String editAgentApplyInfoState(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_AGENT_APPLY_STATE, null, req, rsp);
    }
    /**
     * 代理申请案件 --驳回页面
     * @param req
     */
    @RequestMapping(value = "/editAgentApplyInfoReson")
    public ModelAndView editAgentApplyInfoReson(HttpServletRequest req, HttpServletResponse rsp){
        String id = req.getParameter("id");
        Map model = new HashMap();
        model.put("id",id);
        return new ModelAndView("/caseApplication/agentApplyInfoReson",model);
    }

    /**
     * 贷款申请案件 --审核通过
     *
     */
    @RequestMapping(value = "/editLoanApplicationInfoState")
    public String editLoanApplicationInfoState(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_LOAN_APPLICATION_EDIT_STATE, null, req, rsp);
    }
    /**
     * 贷款申请案件 --驳回页面
     * @param req
     */
    @RequestMapping(value = "/editLoanApplicationInfoReson")
    public ModelAndView editLoanApplicationInfoReson(HttpServletRequest req, HttpServletResponse rsp){
        String id = req.getParameter("id");
        Map model = new HashMap();
        model.put("id",id);
        return new ModelAndView("/caseApplication/loanApplicationReson",model);
    }

    /**
     * 根据“caseNo”查询推广费用记录表
     * @param req
     * @return
     */
    @RequestMapping(value = "/promotionOutlayByCaseNo")
    public ModelAndView promotionOutlayByCaseNo(HttpServletRequest req , HttpServletResponse rsp){
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<PromotionOutlayDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_PROMOTION_OUTLAY_DTO_LIST, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);

        model.put("page", page);
        return new ModelAndView("/caseCenterInfoFined/caseCenterInfoFinedList",model);
    }
}
