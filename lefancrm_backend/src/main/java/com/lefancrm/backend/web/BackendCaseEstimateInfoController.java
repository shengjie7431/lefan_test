package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.CaseEstimateInfoDto;
import com.lefancrm.backend.dto.InvalidismEstimate;
import com.lefancrm.backend.dto.PaymentEstimateApplyDto;
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

/**
 * Created by wangwei on 2018/05/30.
 */
@Controller
@RequestMapping(value = "/caseEstimate")
public class BackendCaseEstimateInfoController extends BackendBaseController {

    @RequestMapping(value = "/caseEstimateInfoList")
    public ModelAndView caseEstimateInfoList(HttpServletRequest req , HttpServletResponse rsp){
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        String type = req.getParameter("type");
        String userName = req.getParameter("userName");
        String userPhone = req.getParameter("userPhone");
        String turnStatus = req.getParameter("turnStatus");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseEstimateInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_ESTIMATE_INFO_LIST, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("type",type==null?"":type);
        model.put("userName",userName==null?"":userName);
        model.put("userPhone",userPhone==null?"":userPhone);
        model.put("turnStatus",turnStatus==null?"":turnStatus);

        model.put("page", page);
        return new ModelAndView("/caseEstimate/caseEstimateInfoList",model);
    }

    @RequestMapping(value = "/caseEstimateView")
    public ModelAndView caseEstimateView(HttpServletRequest req , HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();

        //伤残预估案件
        if ("1".equals(req.getParameter("type"))) {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<InvalidismEstimate>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INVALIDISM_ESTIMATE_BY_ID, null, req);
            InvalidismEstimate invalidismEstimate = (InvalidismEstimate) apiFinalResponse.getResults();
            model.put("invalidismEstimate", invalidismEstimate);
            return new ModelAndView("/caseEstimate/invalidismEstimateView",model);
        }
        //赔付测算案件
        if ("2".equals(req.getParameter("type"))) {
            TypeToken typeToken1 = new TypeToken<ApiFinalResponse<PaymentEstimateApplyDto>>() {};
            ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_PAYMENT_ESTIMATE_APPLY_BY_ID, null, req);
            PaymentEstimateApplyDto paymentEstimateApply = (PaymentEstimateApplyDto) apiFinalResponse1.getResults();
            model.put("paymentEstimateApply", paymentEstimateApply);
            return new ModelAndView("/caseEstimate/paymentEstimateApplyView",model);
        }

        return new ModelAndView("/caseEstimate/invalidismEstimateView",model);
    }

//    /**
//     * 代理申请案件--审核通过
//     *
//     */
//    @RequestMapping(value = "/editAgentApplyInfoState")
//    public String editAgentApplyInfoState(HttpServletRequest req, HttpServletResponse rsp) {
//        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_AGENT_APPLY_STATE, null, req, rsp);
//    }
//    /**
//     * 代理申请案件 --驳回页面
//     * @param req
//     */
//    @RequestMapping(value = "/editAgentApplyInfoReson")
//    public ModelAndView editAgentApplyInfoReson(HttpServletRequest req, HttpServletResponse rsp){
//        String id = req.getParameter("id");
//        Map model = new HashMap();
//        model.put("id",id);
//        return new ModelAndView("/caseApplication/agentApplyInfoReson",model);
//    }
//
//    /**
//     * 贷款申请案件 --审核通过
//     *
//     */
//    @RequestMapping(value = "/editLoanApplicationInfoState")
//    public String editLoanApplicationInfoState(HttpServletRequest req, HttpServletResponse rsp) {
//        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_LOAN_APPLICATION_EDIT_STATE, null, req, rsp);
//    }
//    /**
//     * 贷款申请案件 --驳回页面
//     * @param req
//     */
//    @RequestMapping(value = "/editLoanApplicationInfoReson")
//    public ModelAndView editLoanApplicationInfoReson(HttpServletRequest req, HttpServletResponse rsp){
//        String id = req.getParameter("id");
//        Map model = new HashMap();
//        model.put("id",id);
//        return new ModelAndView("/caseApplication/loanApplicationReson",model);
//    }

}
