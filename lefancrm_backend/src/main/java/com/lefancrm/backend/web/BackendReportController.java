package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
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
@RequestMapping(value = "/case/report")
public class BackendReportController extends BackendBaseController{
    /**
     * 编辑评估报告
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editRiskControl")
    public ModelAndView editRiskControl(HttpServletRequest req,HttpServletResponse rsp) throws Exception{
        Map<String,Object> model = new HashMap<>();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<HashMap<String,CaseRiskControlDto>>>(){};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_CASE_RISK_CONTROL,null,req);
        model.put("apiRsp",apiFinalResponse.getResults());
        String op = req.getParameter("op");
        model.put("op",op);
        return new ModelAndView("/case/report/riskControl",model);
    }

    /**
     * 保存评估报告  新增 add  修改 upd
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveRiskControl")
    public String saveRiskControl(HttpServletRequest req,HttpServletResponse rsp) throws Exception{
        String id = req.getParameter("id");
        if (StringUtils.isEmpty(id)){
            this.callApi(BackendApiMethodEnum.BACKEND_ADD_CASE_RISK_CONTROL,null,req);
        }else{
            this.callApi(BackendApiMethodEnum.BACKEND_UPD_CASE_RISK_CONTROL,null,req);
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pdfName",req.getParameter("caseNo"));
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CREATE_CASE_RISK_CONTROL_PDF,map,req,rsp);
    }

    /**
     * 编辑公估报告
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editAssessmentReport")
    public ModelAndView editAssessmentReport(HttpServletRequest req,HttpServletResponse rsp) throws Exception{
        Map<String,Object> model = new HashMap<>();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<HashMap<String,CaseAssessmentReportDto>>>(){};
        this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_CASE_ASSESSMENT_REPORT,null,req);
        //理赔费用信息  backend-get-case-assessment-obj-report
        typeToken = new TypeToken<ApiFinalResponse<List<CaseAssessmentObjReportDto>>>(){};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_CASE_ASSESSMENT_OBJ_REPORT,null,req);
        model.put("dtos",apiFinalResponse.getResults());

        typeToken = new TypeToken<ApiFinalResponse<HashMap<String,CaseAssessmentReportDto>>>(){};
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_CASE_ASSESSMENT_REPORT,null,req);
        model.put("apiRsp",apiFinalResponse.getResults());
        String op = req.getParameter("op");
        model.put("op",op);
        return new ModelAndView("/case/report/assessmentReport",model);
    }

    /**
     * 保存公估报告
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveAssessmentReport")
    public String saveAssessmentReport(HttpServletRequest req,HttpServletResponse rsp) throws Exception{
        String id = req.getParameter("id");
        if (StringUtils.isEmpty(id)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADD_CASE_ASSESSMENT_REPORT,null,req,rsp);
        }else{
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPD_CASE_ASSESSMENT_REPORT,null,req,rsp);
        }
    }

    /**
     * 修改案件赔偿及保险理赔方案
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updCaseAssessmentObjReport")
    public String updCaseAssessmentObjReport(HttpServletRequest req,HttpServletResponse rsp) throws Exception{
        //保存赔偿方案 及 生成PDF
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPDATE_CASE_ASSESSMENT_OBJ_REPORT,null,req,rsp);
    }



    /**
     * 编辑结案报告
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editCloseReport")
    public ModelAndView editCloseReport(HttpServletRequest req,HttpServletResponse rsp) throws Exception{
        Map<String,Object> model = new HashMap<>();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<HashMap<String,CaseClosedReportDto>>>(){};
        this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_CASE_CLOSE_REPORT,null,req);
        //理赔费用信息  backend-get-case-close-obj-report
        typeToken = new TypeToken<ApiFinalResponse<List<CaseClosedObjReportDto>>>(){};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_CASE_CLOSE_OBJ_REPORT,null,req);
        model.put("dtos",apiFinalResponse.getResults());

        typeToken = new TypeToken<ApiFinalResponse<HashMap<String,CaseClosedReportDto>>>(){};
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_CASE_CLOSE_REPORT,null,req);
        model.put("apiRsp",apiFinalResponse.getResults());

        model.put("caseNo", req.getParameter("caseNo"));
        model.put("caseId", req.getParameter("caseId"));
        model.put("stepCode",req.getParameter("stepCode"));
        String op = req.getParameter("op");
        model.put("op",op);

        String type = req.getParameter("type");
        if("4".equals(type)){
            return new ModelAndView("/case/report/closeReport",model);
        }else if("44".equals(type)){
            return new ModelAndView("/case/report/closeReportNew",model);
        }
        return new ModelAndView("/case/report/closeReport",model);
    }

    /**
     * 保存结案报告
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveCloseReport")
    public String saveCloseReport(HttpServletRequest req,HttpServletResponse rsp) throws Exception{
        String id = req.getParameter("id");
        if (StringUtils.isEmpty(id)){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADD_CASE_CLOSE_REPORT,null,req,rsp);
        }else{
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPD_CASE_CLOSE_REPORT,null,req,rsp);
        }
    }

    /**
     * 修改结案报告赔偿方案
     * @param req
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updCaseCloseObjReport")
    public String updCaseCloseObjReport(HttpServletRequest req,HttpServletResponse rsp) throws Exception{
        //保存赔偿方案
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPDATE_CASE_CLOSE_OBJ_REPORT,null,req,rsp);
    }



    /**
     * 诉讼预案
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "/caseMediationLegal")
    public ModelAndView caseMediationLegal(HttpServletRequest req, HttpServletResponse rsp) throws Exception{
        Map<String,Object> model = new HashMap<>();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<HashMap<String,CaseMediationClaimLegalDto>>>() {};
        this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_MEDIATION_CLAIM_LEGAL, null, req);
        //诉讼信息费用
        typeToken = new TypeToken<ApiFinalResponse<List<CaseMediationClaimReportLegalDto>>>(){};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_CASE_MEDIATION_CLAIM_REPORT_LEGAL,null,req);
        model.put("dtos",apiFinalResponse.getResults());
        //计算完之后重新获取
        typeToken = new TypeToken<ApiFinalResponse<HashMap<String,CaseMediationClaimLegalDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_MEDIATION_CLAIM_LEGAL, null, req);
        model.put("apiRsp",apiFinalResponse.getResults());

        model.put("caseNo", req.getParameter("caseNo"));
        model.put("caseId", req.getParameter("caseId"));
        model.put("stepCode",req.getParameter("stepCode"));
        String op = req.getParameter("op");
        model.put("op", op);
        String type = req.getParameter("type");
        if("5".equals(type)){
            return new ModelAndView("/case/report/caseMediationLegal",model);
        }else if("55".equals(type)){
            return new ModelAndView("/case/report/caseMediationLegalNew",model);
        }
        return new ModelAndView("/case/report/caseMediationLegal",model);
    }

    //诉讼方案保存
    @RequestMapping(value = "/saveCaseMediationLegal")
    public String saveCaseMediationLegal(HttpServletRequest req,HttpServletResponse rsp) throws Exception {
        String id = req.getParameter("id");
        if (StringUtils.isEmpty(id)) {
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ADD_CASE_MEDIATION_CLAIM_LEGAL, null, req, rsp);
        } else {
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPD_CASE_MEDIATION_CLAIM_LEGAL, null, req, rsp);
        }
    }

    //保存诉讼方案详情
    @RequestMapping(value = "/updMediationClaimReportLegal")
    public String updMediationClaimReportLegal(HttpServletRequest req,HttpServletResponse rsp) throws Exception {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPD_CASE_MEDIATION_CLAIM_REPORT_LEGAL, null, req, rsp);
    }
}
