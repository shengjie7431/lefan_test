package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import com.lefancrm.base.web.WebHelper;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.Number;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by lixianfeng on 2018/5/14.
 */
@Controller
@RequestMapping(value = "/case/center/")
public class BackendCaseCenterInfoController  extends BackendBaseController {

    @RequestMapping(value = "list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp){
        String type = req.getParameter("type");
        String gradationState = req.getParameter("gradationState");
        String caseState = req.getParameter("caseState");
        String caseNo = req.getParameter("caseNo");
        String caseName = req.getParameter("caseName");
        String caseTel = req.getParameter("caseTel");
        String caseTitle = req.getParameter("caseTitle");
        String isTestcase = req.getParameter("isTestcase");

        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoNewDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_NEW_LIST, null, req);
        model.put("apiRsp",apiFinalResponse);

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
        }else if(isTestcase ==""){
            model.put("isTestcase",-1);
        }
        else{
            model.put("isTestcase",isTestcase);
        }

        model.put("menuType",req.getParameter("menuType"));
        model.put("gradationState",gradationState==null?"":gradationState);
        model.put("type",type==null?"":type);
        model.put("caseState",caseState==null?"":caseState);
        model.put("caseNo",caseNo==null?"":caseNo);
        model.put("caseName",caseName==null?"":caseName);
        model.put("caseTel",caseTel==null?"":caseTel);
        model.put("caseTitle",caseTitle==null?"":caseTitle);


        model.put("pgChoose",req.getParameter("pgChoose"));
        model.put("spChoose",req.getParameter("spChoose"));
        model.put("ssChoose",req.getParameter("ssChoose"));
        model.put("pageSize",req.getParameter("pageSize"));
        return new ModelAndView("/case/center/list",model);
    }

    /**
     * 工作台
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "manager")
    public ModelAndView manager(HttpServletRequest req, HttpServletResponse rsp){
        Long choose = Long.parseLong(req.getParameter("choose"));
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseCenterNumberDto>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_NEW_MANAGER, null, req);
        CaseCenterNumberDto numberDto = (CaseCenterNumberDto)apiFinalResponse.getResults();
        model.put("numberDto",numberDto);
        //获取角色
        typeToken = new TypeToken<ApiFinalResponse<CaseUserRoleDto>>(){};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_USER_ROLE, null, req);
        CaseUserRoleDto caseUserRoleDto = (CaseUserRoleDto)apiFinalResponse.getResults();
        model.put("caseUserRole",caseUserRoleDto);
        model.put("choose",choose);
        return new ModelAndView("/case/center/manager",model);
    }

//    @RequestMapping(value = "info")
//    public ModelAndView info(HttpServletRequest req, HttpServletResponse rsp){
//        Map model = new HashMap();
//        model.put("menuType",req.getParameter("menuType"));
//        TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseCenterInfoNew>>(){};
//        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_NEW_INFO, null, req);
//        CaseCenterInfoNew caseCenterInfoNewDto = (CaseCenterInfoNew)apiFinalResponse.getResults();
//        model.put("dto",caseCenterInfoNewDto);
//        //获取角色
//        typeToken = new TypeToken<ApiFinalResponse<CaseUserRoleDto>>(){};
//        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_USER_ROLE, null, req);
//        CaseUserRoleDto caseUserRoleDto = (CaseUserRoleDto)apiFinalResponse.getResults();
//        model.put("caseUserRole",caseUserRoleDto);
//        //获取跟踪记录
//        Map paramMap = new HashMap();
//        paramMap.put("caseId",req.getParameter("id"));
//        typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoFollowDto>>>() {};
//        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_FOLLOW_BY_CASEID, paramMap, req);
//        model.put("caseFollows",apiFinalResponse.getResults());
//
//        return new ModelAndView("/case/center/info",model);
//    }

    @RequestMapping(value = "operate")
    public String operate(HttpServletRequest req,HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_CENTER_NEW_OPERATE,null,req,rsp);
    }


    /**
     * 分配机构
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "selectOrgInfo")
    public ModelAndView selectOrgInfo(HttpServletRequest req, HttpServletResponse rsp) {
        String orgName = req.getParameter("orgName");
        String orgTel = req.getParameter("orgTel");
        String caseId = req.getParameter("caseId");
        String btnCode = req.getParameter("btnCode");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_ORG_LIST_BY_ORGPARENTID, null, req);
        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("orgName",orgName==null?"":orgName);
        model.put("orgTel",orgTel==null?"":orgTel);
        model.put("caseId",caseId==null?null:caseId);
        model.put("btnCode",btnCode);
        return new ModelAndView("/case/caseOrgInfoList",model);
    }

    /**
     * 分配机构
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "allotCaseOrg")
    public String allotCaseOrg(HttpServletRequest req, HttpServletResponse rsp) {
        return  this.callApiAndOutput(BackendApiMethodEnum.BACKEND_ALLOT_CASEORG, null, req,rsp);
    }

    /**
     * 退回
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "back")
    public ModelAndView back(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        Long id = Long.valueOf(req.getParameter("id"));
        String btnCode = req.getParameter("btnCode");
        model.put("id",id);
        model.put("btnCode",btnCode);
        return new ModelAndView("/case/caseCenterInfoListNewBack",model);
    }

    /**
     * 投保
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "insured")
    public ModelAndView insured(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        Long id = Long.valueOf(req.getParameter("id"));
        String btnCode = req.getParameter("btnCode");
        model.put("id",id);
        model.put("btnCode",btnCode);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<PaymentEstimateInquiryDto>>(){};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_OK_SERVICE_FEE,null,req);
        model.put("dto",apiFinalResponse.getResults());
        return new ModelAndView("/case/insured",model);
    }

    /**
     * 放款确认/紧急代扣 界面
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "pass")
    public ModelAndView pass(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        Long id = Long.valueOf(req.getParameter("id"));
        String btnCode = req.getParameter("btnCode");
        model.put("btnCode",btnCode);
        model.put("id",id);
        model.put("applyId",req.getParameter("applyId"));
        //根据caseNo查询最新报价信息
        TypeToken typeToken = new TypeToken<ApiFinalResponse<PaymentEstimateInquiryDto>>(){};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_PAY_INQUIRY_INFO,null,req);
        model.put("payEstimateInquiry",apiFinalResponse.getResults());
        model.put("handOutTime",new Date());
        return new ModelAndView("/case/caseCenterInfoListNewPass",model);
    }

    /**
     * 发起代扣  界面
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "start")
    public ModelAndView start(HttpServletRequest req,HttpServletResponse rsp){
        Long id = Long.valueOf(req.getParameter("id"));
        String passType = req.getParameter("passType");
        String btnCode = req.getParameter("btnCode");
        Map model = new HashMap();
        model.put("id",id);
        model.put("btnCode",btnCode);
        if ("passSuning".equals(passType)){
            //根据caseNo查询最新报价信息
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("withholdApply","withholdApply");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<PaymentEstimateInquiryDto>>(){};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_PAY_INQUIRY_INFO,map,req);
            model.put("payEstimateInquiry",apiFinalResponse.getResults());
            model.put("handInTime",new Date());
            return new ModelAndView("/suning/withholdApply/pass",model);
        }else if ("passCommission".equals(passType)){
            return new ModelAndView("/suning/withholdApply/okCommission",model);
        }
        return null;
    }


    /**
     * 添加或编辑银行卡信息
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "editBankCardInfo")
    public ModelAndView editBankCardInfo(HttpServletRequest req,HttpServletResponse rsp){
        Long caseId = Long.valueOf(req.getParameter("caseId"));
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("caseId", caseId);
        Map model = new HashMap();
        //所有银行信息
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<BankInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1 = this.callApi(typeToken1,BackendApiMethodEnum.BACKEND_BANK_INFO_DTO, null, req);
        List<BankInfoDto> bankInfoDtos = (List<BankInfoDto>) apiFinalResponse1.getResults();
        model.put("bankInfos",bankInfoDtos);

        TypeToken typeToken = new TypeToken<ApiFinalResponse<CardInfoDto>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_CARD_INFO, appendMap, null);
        if(apiFinalResponse.getResults() !=null){
            model.put("bankCardInfo",apiFinalResponse.getResults());
        }

        model.put("caseId",caseId);

        return new ModelAndView("/case/bankCardInfo",model);
    }

    /**
     * 保存银行卡
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "saveBankCardInfo")
    public String saveBankCardInfo(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CARD_INFO_SAVE,null,req,rsp);
    }

    @RequestMapping(value = "viewEstimateInquiry")
    public ModelAndView viewEstimateInquiry(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<PaymentEstimateInquiryDto>>(){};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_PAY_INQUIRY_INFO,null,req);
        model.put("payEstimateInquiry",apiFinalResponse.getResults());
        return new ModelAndView("/case/viewEstimateInquiry",model);
    }

    /**
     * 确认服务费金额
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "okServiceFee")
    public ModelAndView okServiceFee(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        Long id = Long.valueOf(req.getParameter("id"));
        String btnCode = req.getParameter("btnCode");
        model.put("id",id);
        model.put("btnCode",btnCode);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<PaymentEstimateInquiryDto>>(){};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_OK_SERVICE_FEE,null,req);
        model.put("dto",apiFinalResponse.getResults());
        return new ModelAndView("/case/okServiceFee",model);
    }

    /**
     * 提交案件状态
     * @return
     */
    @RequestMapping(value = "commitState")
    public ModelAndView commitState(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        Long id = Long.valueOf(req.getParameter("id"));
        String btnCode = req.getParameter("btnCode");
        model.put("id",id);
        model.put("btnCode",btnCode);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>(){};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_CASE_ENUM_STATE,null,req);
        List<CommonEnumDto> enums = (List<CommonEnumDto>)apiFinalResponse.getResults();
        model.put("enums",enums);
        return new ModelAndView("/case/commitState",model);
    }

    /**
     * 提交案件 选择类型(鉴定类型 调解类型 结案方式)
     * @return
     */
    @RequestMapping(value = "listStateSelect")
    public ModelAndView listStateSelect(HttpServletRequest req,HttpServletResponse rsp){
        Map model = new HashMap();
        Long id = Long.valueOf(req.getParameter("id"));
        String btnCode = req.getParameter("btnCode");
        model.put("id",id);
        model.put("btnCode",btnCode);
        return new ModelAndView("/case/listStateSelect",model);
    }

    @RequestMapping(value = "info")
    public ModelAndView info(HttpServletRequest req, HttpServletResponse rsp){
        Map<String, Object> appendMap = new HashMap<String, Object>();
        Map model = new HashMap();
        model.put("menuType",req.getParameter("menuType"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseCenterInfoNew>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_NEW_INFO, null, req);
        CaseCenterInfoNew caseCenterInfoNewDto = (CaseCenterInfoNew)apiFinalResponse.getResults();
        model.put("caseInfo",caseCenterInfoNewDto);
        //获取角色
        typeToken = new TypeToken<ApiFinalResponse<CaseUserRoleDto>>(){};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_USER_ROLE, null, req);
        CaseUserRoleDto caseUserRoleDto = (CaseUserRoleDto)apiFinalResponse.getResults();
        model.put("caseUserRole",caseUserRoleDto);

        //单证信息
        appendMap = new HashMap<>();
        appendMap.put("caseNo",caseCenterInfoNewDto.getCaseNo());
        TypeToken token = new TypeToken<ApiFinalResponse<List<CaseFileMidDto>>>(){};
        ApiFinalResponse response = this.callApi(token, BackendApiMethodEnum.BACKEND_CASE_FILE,appendMap,req);
        List<CaseFileMidDto> caseFileMidDtos = (List<CaseFileMidDto>)(response == null ? null : response.getResults());
        model.put("caseFileMidSize",caseFileMidDtos==null?0:caseFileMidDtos.size());
        //获取跟踪记录
        appendMap = new HashMap<String, Object>();
        appendMap.put("caseId",req.getParameter("id"));
        typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoFollowDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_FOLLOW_BY_CASEID, appendMap, req);
        List<CaseCenterInfoFollowDto> caseFollows = (List<CaseCenterInfoFollowDto>) apiFinalResponse.getResults();
        if(caseFollows.size()>0){
            model.put("caseFollows",caseFollows.get(0));
        }
        model.put("caseFollowSize",caseFollows.size());

        //新增案件信息
        appendMap = new HashMap<String, Object>();
        appendMap.put("id",caseCenterInfoNewDto.getCaseId());
        typeToken = new TypeToken<ApiFinalResponse<CaseEntrustInputDto>>() {};
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_ENTRUST_INPUT_INFO, appendMap, null);
        model.put("caseEntrustInput", apiFinalResponse == null ? null : apiFinalResponse.getResults());

        if(caseCenterInfoNewDto.getExtend2().getProcessState()!=null && caseCenterInfoNewDto.getExtend2().getProcessState()>=2){
            //签约指导信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("caseId",caseCenterInfoNewDto.getCaseId());
            typeToken = new TypeToken<ApiFinalResponse<CaseSignGuidanceDto>>() {};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_SIGN_GUIDANCE_BY_CASEID, appendMap, req);
            model.put("caseSignGuidance",apiFinalResponse==null?null:apiFinalResponse.getResults());
        }

        if(caseCenterInfoNewDto.getExtend2().getProcessState()!=null && caseCenterInfoNewDto.getExtend2().getProcessState()>=3) {
            //伤残报告
            appendMap = new HashMap<String, Object>();
            appendMap.put("caseNo", caseCenterInfoNewDto.getCaseNo());
            appendMap.put("caseId", req.getParameter("id"));
            typeToken = new TypeToken<ApiFinalResponse<InvalidismEstimateReportDto>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INVALIDISM_TO_REPORT, appendMap, null);
            model.put("estimateReport", apiFinalResponse == null ? null : apiFinalResponse.getResults());
        }

        if(caseCenterInfoNewDto.getExtend2().getProcessState()!=null && caseCenterInfoNewDto.getExtend2().getProcessState()>=4) {
            //测算报告
            appendMap = new HashMap<String, Object>();
            appendMap.put("caseNo", caseCenterInfoNewDto.getCaseNo());
            appendMap.put("caseId", req.getParameter("id"));
            typeToken = new TypeToken<ApiFinalResponse<List<PaymentEstimateReportDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_PAYMENT_ESTIMATE_REPORT_LIST, appendMap, null);
            List<PaymentEstimateReportDto> paymentEstimateReportDtos = (List<PaymentEstimateReportDto>) apiFinalResponse.getResults();
            model.put("paymentEstimateReportDtos", paymentEstimateReportDtos);
            appendMap = new HashMap<String, Object>();
            if (paymentEstimateReportDtos.size() > 0) {
                appendMap.put("id", paymentEstimateReportDtos.get(0).getPaymentEstimateId());
            }
            typeToken = new TypeToken<ApiFinalResponse<PaymentEstimateApplyDto>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_PAYMENTESTIMATE_NEW, appendMap, null);
            model.put("apply", apiFinalResponse == null ? null : apiFinalResponse.getResults());
            //最新精准报价
            appendMap = new HashMap<String, Object>();
            appendMap.put("id", req.getParameter("id"));
            typeToken = new TypeToken<ApiFinalResponse<PaymentEstimateInquiryDto>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_PAY_INQUIRY_INFO, appendMap, null);
            model.put("payEstimateInquiry", apiFinalResponse == null ? null : apiFinalResponse.getResults());
        }

        if(caseCenterInfoNewDto.getExtend2().getProcessState()!=null && caseCenterInfoNewDto.getExtend2().getProcessState()>=7) {
            //索赔指导信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("caseCenterId", req.getParameter("id"));
            typeToken = new TypeToken<ApiFinalResponse<CaseClaimGuidanceDto>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CLAIM_GUIDANCE_BY_CASEID, appendMap, req);
            model.put("caseClaimGuidance", apiFinalResponse == null ? null : apiFinalResponse.getResults());
            //索赔预案
            appendMap = new HashMap<String, Object>();
            appendMap.put("caseId", req.getParameter("id"));
            typeToken = new TypeToken<ApiFinalResponse<List<CaseMediationClaimReportDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_CASE_MEDIATION_CLAIM_REPORT, appendMap, req);
            model.put("claimReports", apiFinalResponse == null ? null : apiFinalResponse.getResults());
            //计算后的值
            appendMap = new HashMap<String, Object>();
            appendMap.put("caseId", req.getParameter("id"));
            appendMap.put("op", "view");//仅为查看，不予初始化数据
            typeToken = new TypeToken<ApiFinalResponse<HashMap<String, CaseMediationClaimDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_MEDIATION_CLAIM, appendMap, null);
            model.put("claim", apiFinalResponse == null ? null : apiFinalResponse.getResults());

            if(caseCenterInfoNewDto.getLegalUserId() !=null) {
                //诉讼预案
                appendMap = new HashMap<String, Object>();
                appendMap.put("caseId", req.getParameter("id"));
                typeToken = new TypeToken<ApiFinalResponse<List<CaseMediationClaimReportLegalDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_CASE_MEDIATION_CLAIM_REPORT_LEGAL, appendMap, req);
                model.put("claimReportLegals", apiFinalResponse == null ? null : apiFinalResponse.getResults());
                //计算后的值
                appendMap = new HashMap<String, Object>();
                appendMap.put("caseId", req.getParameter("id"));
                typeToken = new TypeToken<ApiFinalResponse<HashMap<String, CaseMediationClaimLegalDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_MEDIATION_CLAIM_LEGAL, appendMap, null);
                model.put("claimLegal", apiFinalResponse == null ? null : apiFinalResponse.getResults());
            }
        }
        return new ModelAndView("/case/center/infoNew",model);
    }

    @RequestMapping(value = "infoNewUpdate")
    public String infoNewUpdate(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_INFO_NEW_UPDATE,null,req,rsp);
    }

    /**
     * 导出弹窗
     * @param req
     * @return
     */
    @RequestMapping(value = "popup")
    public ModelAndView popup (HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String id = req.getParameter("id");
        model.put("id", id);
        String code = req.getParameter("code");
        model.put("code", code);
        //导出页面
        if("export".equals(code)){
            //案件信息
            TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseCenterInfoNew>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_NEW_INFO, null, req);
            CaseCenterInfoNew caseCenterInfoNewDto = (CaseCenterInfoNew)apiFinalResponse.getResults();
            model.put("caseInfo",caseCenterInfoNewDto);
            return new ModelAndView("/case/center/exportView",model);
        }
        //获取跟踪记录
        else if("follow".equals(code)){
            appendMap.put("caseId",req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoFollowDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_FOLLOW_BY_CASEID, appendMap, req);
            List<CaseCenterInfoFollowDto> caseFollows = (List<CaseCenterInfoFollowDto>) apiFinalResponse.getResults();
            model.put("caseFollows",caseFollows);
            return new ModelAndView("/case/center/followView",model);
        }
        //案件基础信息
        else if("caseInfo".equals(code)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseCenterInfoNew>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_NEW_INFO, null, req);
            CaseCenterInfoNew caseCenterInfoNewDto = (CaseCenterInfoNew)apiFinalResponse.getResults();
            model.put("caseInfo",caseCenterInfoNewDto);
            //新增案件信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("id",caseCenterInfoNewDto.getCaseId());
            typeToken = new TypeToken<ApiFinalResponse<CaseEntrustInputDto>>() {};
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_ENTRUST_INPUT_INFO, appendMap, null);
            CaseEntrustInputDto caseEntrustInput = (CaseEntrustInputDto)apiFinalResponse.getResults();
            model.put("caseEntrustInput", caseEntrustInput);
            //地区信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",0);
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            model.put("apiRsp", apiRsp);

            //事故地址：市信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",caseEntrustInput.getAccidentProvinceId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> cityApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("cityApiRsp", cityApiRsp);
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",caseEntrustInput.getAccidentCityId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> districtApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("districtApiRsp", districtApiRsp);

            //单位地址：市信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",caseCenterInfoNewDto.getExtend2().getUnitProvinceId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> unitCityApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("unitCityApiRsp", unitCityApiRsp);
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",caseCenterInfoNewDto.getExtend2().getUnitCityId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> unitDistrictApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("unitDistrictApiRsp", unitDistrictApiRsp);

            //居住地址：市信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",caseCenterInfoNewDto.getExtend2().getLiveProvinceId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> liveCityApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("liveCityApiRsp", liveCityApiRsp);
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",caseCenterInfoNewDto.getExtend2().getLiveCityId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> liveDistrictApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("liveDistrictApiRsp", liveDistrictApiRsp);

            //户籍地址：市信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",caseCenterInfoNewDto.getExtend2().getDomicileProvinceId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> domicileCityApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("domicileCityApiRsp", domicileCityApiRsp);
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",caseCenterInfoNewDto.getExtend2().getDomicileCityId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> domicileDistrictApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("domicileDistrictApiRsp", domicileDistrictApiRsp);

            if(caseCenterInfoNewDto.getExtend2().getProcessState()!=null && caseCenterInfoNewDto.getExtend2().getProcessState()>=4) {
                //测算报告
                appendMap = new HashMap<String, Object>();
                appendMap.put("caseNo", caseCenterInfoNewDto.getCaseNo());
                appendMap.put("caseId", req.getParameter("id"));
                typeToken = new TypeToken<ApiFinalResponse<List<PaymentEstimateReportDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_PAYMENT_ESTIMATE_REPORT_LIST, appendMap, null);
                List<PaymentEstimateReportDto> paymentEstimateReportDtos = (List<PaymentEstimateReportDto>) apiFinalResponse.getResults();
                model.put("paymentEstimateReportDtos", paymentEstimateReportDtos);
                appendMap = new HashMap<String, Object>();
                if (paymentEstimateReportDtos.size() > 0) {
                    appendMap.put("id", paymentEstimateReportDtos.get(0).getPaymentEstimateId());
                }
                typeToken = new TypeToken<ApiFinalResponse<PaymentEstimateApplyDto>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_PAYMENTESTIMATE_NEW, appendMap, req);
                model.put("apply", apiFinalResponse.getResults());
            }
            if(caseCenterInfoNewDto.getExtend2().getProcessState()!=null && caseCenterInfoNewDto.getExtend2().getProcessState()>=3) {
                //伤残报告
                appendMap = new HashMap<String, Object>();
                appendMap.put("caseNo", caseCenterInfoNewDto.getCaseNo());
                appendMap.put("caseId", req.getParameter("id"));
                typeToken = new TypeToken<ApiFinalResponse<InvalidismEstimateReportDto>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INVALIDISM_TO_REPORT, appendMap, null);
                model.put("estimateReport", apiFinalResponse == null ? null : apiFinalResponse.getResults());
            }
            if(caseCenterInfoNewDto.getExtend2().getProcessState()!=null && caseCenterInfoNewDto.getExtend2().getProcessState()>=7) {
                //索赔预案
                appendMap = new HashMap<String, Object>();
                appendMap.put("caseId", req.getParameter("id"));
                appendMap.put("op", "view");//仅为查看，不予初始化数据
                typeToken = new TypeToken<ApiFinalResponse<HashMap<String, CaseMediationClaimDto>>>() {
                };
                this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_MEDIATION_CLAIM, appendMap, req);
                appendMap = new HashMap<String, Object>();
                appendMap.put("caseId", req.getParameter("id"));
                typeToken = new TypeToken<ApiFinalResponse<List<CaseMediationClaimReportDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_CASE_MEDIATION_CLAIM_REPORT, appendMap, req);
                List<CaseMediationClaimReportDto> claimReports = (List<CaseMediationClaimReportDto>) apiFinalResponse.getResults();
                //计算后的值
                appendMap = new HashMap<String, Object>();
                appendMap.put("caseId", req.getParameter("id"));
                appendMap.put("op", "view");//仅为查看，不予初始化数据
                typeToken = new TypeToken<ApiFinalResponse<HashMap<String, CaseMediationClaimDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_MEDIATION_CLAIM, appendMap, req);
                model.put("claim", apiFinalResponse.getResults());
            }
            return new ModelAndView("/case/center/infoNewEdit",model);
        }
        //单证移动页面
        else if("caseFileMidRemove".equals(code)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>(){};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_CASE_FILE_ENUM,null,req);
            List<CommonEnumDto> commonEnumDtos = (List<CommonEnumDto>)apiFinalResponse.getResults();
            List<CommonEnumDto> commonEnumDtoNew = new ArrayList<>();
            //移除掉“全部资料”
            for(CommonEnumDto commonEnumDto :commonEnumDtos){
                if(!"1".equals(commonEnumDto.getEnumCode())){
                    commonEnumDtoNew.add(commonEnumDto);
                }
            }
            model.put("commonEnumDtos", commonEnumDtoNew);
            model.put("caseId", req.getParameter("caseId"));
            model.put("code", code);
            model.put("files", req.getParameter("files"));
            return new ModelAndView("/case/caseFileMidRemove",model);
        }
        //查询保险公司
        else if("insuranceCompany".equals(code)){
            appendMap = new HashMap<String, Object>();
            appendMap.put("noPage",1);//不分页
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonOrgInfoDto>>>(){};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_COMMON_ORG_INFO,appendMap,req);
            List<CommonOrgInfoDto> commonOrgInfoDtos = (List<CommonOrgInfoDto>)apiFinalResponse.getResults();
            model.put("commonOrgInfoDtos", commonOrgInfoDtos);
            model.put("type", req.getParameter("type"));
            model.put("code", code);
            model.put("cOrgName", req.getParameter("cOrgName"));
            return new ModelAndView("/case/center/commonOrgInfo",model);
        }
        //受伤部位
        else if("injuredPart".equals(code)){
            //测算报告
            appendMap = new HashMap<String, Object>();
            appendMap.put("caseId", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<PaymentEstimateReportDto>>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_PAYMENT_ESTIMATE_REPORT_LIST, appendMap, null);
            List<PaymentEstimateReportDto> paymentEstimateReportDtos = (List<PaymentEstimateReportDto>) apiFinalResponse.getResults();
            model.put("paymentEstimateReportDtos", paymentEstimateReportDtos);
            appendMap = new HashMap<String, Object>();
            if (paymentEstimateReportDtos.size() > 0) {
                appendMap.put("id", paymentEstimateReportDtos.get(0).getPaymentEstimateId());
            }
            typeToken = new TypeToken<ApiFinalResponse<PaymentEstimateApplyDto>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_PAYMENTESTIMATE_NEW, appendMap, req);
            model.put("apply", apiFinalResponse.getResults());
            model.put("code", code);
            return new ModelAndView("/case/center/injuredPartView",model);
        }
        //伤残等级信息
        else if("invalidismGrade".equals(code)){
            //伤残等级信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("caseNo", req.getParameter("caseNo"));
            appendMap.put("caseId", req.getParameter("id"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<InvalidismEstimateReportDto>>() {
            };
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INVALIDISM_TO_REPORT, appendMap, null);
            model.put("estimateReport", apiFinalResponse == null ? null : apiFinalResponse.getResults());
            model.put("code", code);
            return new ModelAndView("/case/center/invalidismGradeView",model);
        }
        return null;
    }

    /**
     * 导出
     *
     */
    @RequestMapping(value = "exportNew")
    public void exportNew(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        Map model = new HashMap();

        TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseCenterInfoNew>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_NEW_INFO, null, req);
        CaseCenterInfoNew caseInfo = (CaseCenterInfoNew)apiFinalResponse.getResults();
        //获取角色
        typeToken = new TypeToken<ApiFinalResponse<CaseUserRoleDto>>(){};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_USER_ROLE, null, req);
        CaseUserRoleDto caseUserRoleDto = (CaseUserRoleDto)apiFinalResponse.getResults();
        model.put("caseUserRole",caseUserRoleDto);
        //获取跟踪记录
        appendMap = new HashMap<String, Object>();
        appendMap.put("caseId",req.getParameter("id"));
        typeToken = new TypeToken<ApiFinalResponse<List<CaseCenterInfoFollowDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_FOLLOW_BY_CASEID, appendMap, req);
        List<CaseCenterInfoFollowDto> caseFollows = (List<CaseCenterInfoFollowDto>) apiFinalResponse.getResults();
        if(caseFollows.size()>0){
            model.put("caseFollows",caseFollows.get(0));
        }
        model.put("caseFollowSize",caseFollows.size());

        //新增案件信息
        appendMap = new HashMap<String, Object>();
        appendMap.put("id",caseInfo.getCaseId());
        typeToken = new TypeToken<ApiFinalResponse<CaseEntrustInputDto>>() {};
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_ENTRUST_INPUT_INFO, appendMap, null);
        CaseEntrustInputDto caseEntrustInput = (CaseEntrustInputDto) apiFinalResponse.getResults();

        CaseSignGuidanceDto caseSignGuidance = new CaseSignGuidanceDto();
        if(caseInfo.getExtend2().getProcessState()!=null && caseInfo.getExtend2().getProcessState()>=2) {
            //签约指导信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("caseId", caseInfo.getCaseId());
            typeToken = new TypeToken<ApiFinalResponse<CaseSignGuidanceDto>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_SIGN_GUIDANCE_BY_CASEID, appendMap, req);
            caseSignGuidance = (CaseSignGuidanceDto) apiFinalResponse.getResults();
            if(caseSignGuidance==null){
                caseSignGuidance = new CaseSignGuidanceDto();
            }
        }

        InvalidismEstimateReportDto estimateReport = new InvalidismEstimateReportDto();
        if(caseInfo.getExtend2().getProcessState()!=null && caseInfo.getExtend2().getProcessState()>=3) {
            //伤残报告
            appendMap = new HashMap<String, Object>();
            appendMap.put("caseNo", caseInfo.getCaseNo());
            appendMap.put("caseId", req.getParameter("id"));
            typeToken = new TypeToken<ApiFinalResponse<InvalidismEstimateReportDto>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INVALIDISM_TO_REPORT, appendMap, null);
            estimateReport = (InvalidismEstimateReportDto) apiFinalResponse.getResults();
            if(estimateReport==null){
                estimateReport = new InvalidismEstimateReportDto();
            }
        }

        PaymentEstimateApplyDto apply = new PaymentEstimateApplyDto();
        PaymentEstimateInquiryDto payEstimateInquiry = new PaymentEstimateInquiryDto();
        List<PaymentEstimateReportDto> paymentEstimateReportDtos = new ArrayList<>();
        if(caseInfo.getExtend2().getProcessState()!=null && caseInfo.getExtend2().getProcessState()>=4) {
            //测算报告
            appendMap = new HashMap<String, Object>();
            appendMap.put("caseNo", caseInfo.getCaseNo());
            appendMap.put("caseId", req.getParameter("id"));
            typeToken = new TypeToken<ApiFinalResponse<List<PaymentEstimateReportDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_PAYMENT_ESTIMATE_REPORT_LIST, appendMap, null);
            paymentEstimateReportDtos = (List<PaymentEstimateReportDto>) apiFinalResponse.getResults();
            if(paymentEstimateReportDtos==null){
                paymentEstimateReportDtos = new ArrayList<>();
            }
            appendMap = new HashMap<String, Object>();
            if (paymentEstimateReportDtos.size() > 0) {
                appendMap.put("id", paymentEstimateReportDtos.get(0).getPaymentEstimateId());
            }
            typeToken = new TypeToken<ApiFinalResponse<PaymentEstimateApplyDto>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_PAYMENTESTIMATE_NEW, appendMap, req);
            apply = (PaymentEstimateApplyDto) apiFinalResponse.getResults();
            if(apply==null){
                apply = new PaymentEstimateApplyDto();
            }
            //最新精准报价
            appendMap = new HashMap<String, Object>();
            appendMap.put("id", req.getParameter("id"));
            typeToken = new TypeToken<ApiFinalResponse<PaymentEstimateInquiryDto>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_PAY_INQUIRY_INFO, appendMap, null);
            payEstimateInquiry = (PaymentEstimateInquiryDto) apiFinalResponse.getResults();
            if(payEstimateInquiry==null){
                payEstimateInquiry = new PaymentEstimateInquiryDto();
            }
        }

        CaseClaimGuidanceDto caseClaimGuidance = new CaseClaimGuidanceDto();
        List<CaseMediationClaimReportDto> claimReports = new ArrayList<>();
        CaseMediationClaimDto claim = new CaseMediationClaimDto();
        List<CaseMediationClaimReportLegalDto> claimReportLegals = new ArrayList<>();
        CaseMediationClaimLegalDto claimLegal = new CaseMediationClaimLegalDto();
        if(caseInfo.getExtend2().getProcessState()!=null && caseInfo.getExtend2().getProcessState()>=7) {
            //索赔指导信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("caseCenterId", req.getParameter("id"));
            typeToken = new TypeToken<ApiFinalResponse<CaseClaimGuidanceDto>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CLAIM_GUIDANCE_BY_CASEID, appendMap, req);
            caseClaimGuidance = (CaseClaimGuidanceDto) apiFinalResponse.getResults();
            if(caseClaimGuidance==null){
                caseClaimGuidance = new CaseClaimGuidanceDto();
            }
            //索赔预案
            appendMap = new HashMap<String, Object>();
            appendMap.put("caseId", req.getParameter("id"));
            typeToken = new TypeToken<ApiFinalResponse<List<CaseMediationClaimReportDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_CASE_MEDIATION_CLAIM_REPORT, appendMap, req);
            model.put("claimReports", apiFinalResponse.getResults());
            claimReports = (List<CaseMediationClaimReportDto>) apiFinalResponse.getResults();
            if(claimReports==null){
                claimReports = new ArrayList<>();
            }
            //计算后的值
            appendMap = new HashMap<String, Object>();
            appendMap.put("caseId", req.getParameter("id"));
            appendMap.put("op", "view");//仅为查看，不予初始化数据
            typeToken = new TypeToken<ApiFinalResponse<HashMap<String, CaseMediationClaimDto>>>() {
            };
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_MEDIATION_CLAIM, appendMap, null);
            model.put("claim", apiFinalResponse.getResults());
            Map map = (Map) apiFinalResponse.getResults();
            claim = (CaseMediationClaimDto) map.get("caseMediationClaim");
            if(claim==null){
                claim = new CaseMediationClaimDto();
            }

            if(caseInfo.getLegalUserId() !=null) {
                //诉讼预案
                appendMap = new HashMap<String, Object>();
                appendMap.put("caseId", req.getParameter("id"));
                typeToken = new TypeToken<ApiFinalResponse<List<CaseMediationClaimReportLegalDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_GET_CASE_MEDIATION_CLAIM_REPORT_LEGAL, appendMap, req);
                claimReportLegals = (List<CaseMediationClaimReportLegalDto>) apiFinalResponse.getResults();
                if(claimReportLegals==null){
                    claimReportLegals = new ArrayList<>();
                }
                //计算后的值
                appendMap = new HashMap<String, Object>();
                appendMap.put("caseId", req.getParameter("id"));
                typeToken = new TypeToken<ApiFinalResponse<HashMap<String, CaseMediationClaimLegalDto>>>() {
                };
                apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_MEDIATION_CLAIM_LEGAL, appendMap, null);
                model.put("claimLegal", apiFinalResponse.getResults());
                map = (Map) apiFinalResponse.getResults();
                claimLegal = (CaseMediationClaimLegalDto) map.get("caseMediationClaim");
                if(claimLegal==null){
                    claimLegal = new CaseMediationClaimLegalDto();
                }
            }
        }
        // 创建excel
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename = caseInfo.getCaseTitle()+".xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet(caseInfo.getCaseTitle(), 0);

            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf.setWrap(true);

            WritableCellFormat wcfColour1 = new WritableCellFormat();
            wcfColour1.setAlignment(Alignment.CENTRE);

            //设置字体格式

            WritableFont wf = new WritableFont(WritableFont.TIMES);
            wf.setColour(Colour.BLACK);
            wf.setPointSize(20);
            //表头
            WritableCellFormat wcf1 = new WritableCellFormat(wf);
            wcf1.setAlignment(Alignment.CENTRE);//把水平对齐方式指定为居中
            wcf1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中
            wcf1.setWrap(true);

            //菜单表名
            WritableFont wf2 = new WritableFont(WritableFont.ARIAL,15,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
            WritableCellFormat wcf2 = new WritableCellFormat(wf2);
            wcf2.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            //wcf3：具体内容
            wf = new WritableFont(WritableFont.TIMES);
            WritableCellFormat wcf3 = new WritableCellFormat(wf);
            wcf3.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

            //wcf4：具体内容的菜单名
            WritableFont wf4 = new WritableFont(WritableFont.ARIAL,11,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
            WritableCellFormat wcf4 = new WritableCellFormat(wf4);
            wcf4.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);


            sheet.setColumnView(0,25);
            sheet.setColumnView(1,25);
            sheet.setColumnView(2,25);
            sheet.setColumnView(3,25);
            sheet.setColumnView(4,25);
            sheet.setColumnView(5,25);

            // 设置表头
            sheet.mergeCells(0, 0, 5, 1);//
            sheet.addCell(new Label(0,0, caseInfo.getCaseTitle(),wcf1));

            int num = 2;
            String strIds = req.getParameter("ids");
            strIds = strIds == null ? "" : strIds;
            String[] ids = strIds.split(",");
            String type1 = req.getParameter("type");
            if(type1!=null){
                ids = new String[]{"1"};
            }

            for (String type : ids) {
                if ("1".equals(type)) {
                    //案件基本信息
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "案件基本信息", wcf2));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "案件编号", wcf4));
                    sheet.addCell(new Label(1, num, caseInfo.getCaseNo(), wcf3));
                    sheet.addCell(new Label(2, num, "事故发生地", wcf4));
                    sheet.addCell(new Label(3, num, (caseEntrustInput.getAccidentProvince() == null ? "" : caseEntrustInput.getAccidentProvince()) + (caseEntrustInput.getAccidentCity() == null ? "" : caseEntrustInput.getAccidentCity()) + (caseEntrustInput.getAccidentDistrict() == null ? "" : caseEntrustInput.getAccidentDistrict()) + (caseEntrustInput.getAccidentAddress() == null ? "" : caseEntrustInput.getAccidentAddress()), wcf3));
                    sheet.addCell(new Label(4, num, "处理交警队", wcf4));
                    sheet.addCell(new Label(5, num, caseInfo.getExtend2().getPoliceTeam() == null ? "" : caseInfo.getExtend2().getPoliceTeam(), wcf3));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "委托人", wcf4));
                    sheet.addCell(new Label(1, num, caseEntrustInput.getInjuredPerson() == null ? "" : caseEntrustInput.getInjuredPerson(), wcf3));
                    sheet.addCell(new Label(2, num, "联系电话", wcf4));
                    sheet.addCell(new Label(3, num, caseEntrustInput.getInjuredTel() == null ? "" : caseEntrustInput.getInjuredTel(), wcf3));
                    sheet.addCell(new Label(4, num, "身份证号码", wcf4));
                    sheet.addCell(new Label(5, num, caseInfo.getExtend2().getIdCard() == null ? "" : caseInfo.getExtend2().getIdCard(), wcf3));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "性别", wcf4));
                    if (caseInfo.getExtend2().getSex() != null) {
                        if (caseInfo.getExtend2().getSex() == 1) {
                            sheet.addCell(new Label(1, num, "男", wcf3));
                        } else if (caseInfo.getExtend2().getSex() == 2) {
                            sheet.addCell(new Label(1, num, "女", wcf3));
                        }
                    } else {
                        sheet.addCell(new Label(1, num, "", wcf3));
                    }
                    sheet.addCell(new Label(2, num, "年龄", wcf4));
                    if (caseInfo.getExtend2().getAge() != null) {
                        sheet.addCell(new Number(3, num, caseInfo.getExtend2().getAge(), wcf3));
                    } else {
                        sheet.addCell(new Label(3, num, "", wcf3));
                    }

                    sheet.addCell(new Label(4, num, "婚姻情况", wcf4));
                    if (caseInfo.getExtend2().getMaritalStatus() != null) {
                        if (caseInfo.getExtend2().getMaritalStatus() == 1) {
                            sheet.addCell(new Label(5, num, "已婚", wcf3));
                        } else if (caseInfo.getExtend2().getMaritalStatus() == 2) {
                            sheet.addCell(new Label(5, num, "离异", wcf3));
                        } else if (caseInfo.getExtend2().getMaritalStatus() == 3) {
                            sheet.addCell(new Label(5, num, "丧偶", wcf3));
                        } else if (caseInfo.getExtend2().getMaritalStatus() == 4) {
                            sheet.addCell(new Label(5, num, "未婚", wcf3));
                        }
                    } else {
                        sheet.addCell(new Label(5, num, "", wcf3));
                    }

                    num = num + 1;
                    sheet.addCell(new Label(0, num, "工作单位", wcf4));
                    sheet.addCell(new Label(1, num, caseInfo.getExtend2().getWorkUnit() == null ? "" : caseInfo.getExtend2().getWorkUnit(), wcf3));
                    sheet.addCell(new Label(2, num, "入职时间", wcf4));
                    if (caseInfo.getExtend2().getEntryTime() != null) {
                        sheet.addCell(new DateTime(3, num, caseInfo.getExtend2().getEntryTime()));
                    } else {
                        sheet.addCell(new Label(3, num, "", wcf3));
                    }
                    sheet.addCell(new Label(4, num, "收入情况", wcf4));
                    sheet.addCell(new Number(5, num, caseInfo.getExtend2().getWages() == null ? 0D : caseInfo.getExtend2().getWages(), wcf3));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "是否缴税", wcf4));
                    if (caseInfo.getExtend2().getTaxCertificate() == null || caseInfo.getExtend2().getTaxCertificate() == 2) {
                        sheet.addCell(new Label(1, num, "无", wcf3));
                    } else if (caseInfo.getExtend2().getTaxCertificate() == 1) {
                        sheet.addCell(new Label(1, num, "有", wcf3));
                    }

                    sheet.addCell(new Label(2, num, "是否缴纳社保", wcf4));
                    if (caseInfo.getExtend2().getPaySocialSecurity() == null || caseInfo.getExtend2().getPaySocialSecurity() == 1) {
                        sheet.addCell(new Label(3, num, "无", wcf3));
                    } else if (caseInfo.getExtend2().getPaySocialSecurity() == 0) {
                        sheet.addCell(new Label(3, num, "有", wcf3));
                    }
                    sheet.addCell(new Label(4, num, "工资发放形式", wcf4));
                    if (caseInfo.getExtend2().getBankInfo() == null || caseInfo.getExtend2().getBankInfo() == 2) {
                        sheet.addCell(new Label(5, num, "无", wcf3));
                    } else if (caseInfo.getExtend2().getBankInfo() == 1) {
                        sheet.addCell(new Label(5, num, "有", wcf3));
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "单位地址", wcf4));
                    sheet.mergeCells(1, num, 5, 0);//
                    sheet.addCell(new Label(1, num, caseInfo.getExtend2().getUnitProvince() == null ? "" : caseInfo.getExtend2().getUnitProvince() + caseInfo.getExtend2().getUnitCity() == null ? "" : caseInfo.getExtend2().getUnitCity() + caseInfo.getExtend2().getUnitDistrict() == null ? "" : caseInfo.getExtend2().getUnitDistrict() + caseInfo.getExtend2().getUnitAddress() == null ? "" : caseInfo.getExtend2().getUnitAddress(), wcf3));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "居住地址", wcf4));
                    sheet.mergeCells(1, num, 3, 0);//
                    sheet.addCell(new Label(1, num, caseInfo.getExtend2().getLiveProvince() == null ? "" : caseInfo.getExtend2().getLiveProvince() + caseInfo.getExtend2().getLiveCity() == null ? "" : caseInfo.getExtend2().getLiveCity() + caseInfo.getExtend2().getLiveDistrict() == null ? "" : caseInfo.getExtend2().getLiveDistrict() + caseInfo.getExtend2().getLiveAddress() == null ? "" : caseInfo.getExtend2().getLiveAddress(), wcf3));
                    sheet.addCell(new Label(4, num, "居住时间", wcf4));
                    if (caseInfo.getExtend2().getLiveTime() != null) {
                        if (caseInfo.getExtend2().getLiveTimeType() != null) {
                            if (caseInfo.getExtend2().getLiveTimeType() == 1) {
                                sheet.addCell(new Label(5, num, caseInfo.getExtend2().getLiveTime()+"年",wcf3));
                            }else if(caseInfo.getExtend2().getLiveTimeType() == 2){
                                sheet.addCell(new Label(5, num, caseInfo.getExtend2().getLiveTime()+"个月",wcf3));
                            }
                        }
                    } else {
                        sheet.addCell(new Label(5, num, "", wcf3));
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "户籍地", wcf4));
                    sheet.mergeCells(1, num, 3, 0);//
                    sheet.addCell(new Label(1, num, caseInfo.getExtend2().getDomicileProvince() == null ? "" : caseInfo.getExtend2().getDomicileProvince() + caseInfo.getExtend2().getDomicileCity() == null ? "" : caseInfo.getExtend2().getDomicileCity() + caseInfo.getExtend2().getDomicileDistrict() == null ? "" : caseInfo.getExtend2().getDomicileDistrict() + caseInfo.getExtend2().getDomicileAddress() == null ? "" : caseInfo.getExtend2().getDomicileAddress(), wcf3));
                    sheet.addCell(new Label(4, num, "户籍性质", wcf4));
                    if (caseInfo.getExtend2().getDomicile() != null) {
                        if ("1".equals(caseInfo.getExtend2().getDomicile())) {
                            sheet.addCell(new Label(5, num, "农业", wcf3));
                        }else if ("2".equals(caseInfo.getExtend2().getDomicile())) {
                            sheet.addCell(new Label(5, num, "非农业", wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(1, num, "", wcf3));
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "拆迁/征地等情况", wcf4));
                    sheet.mergeCells(1, num, 5, 0);//
                    if (caseInfo.getExtend2().getLandExpropriation() != null) {
                        if (caseInfo.getExtend2().getLandExpropriation() == 2) {
                            sheet.addCell(new Label(1, num, "无", wcf3));
                        }else if (caseInfo.getExtend2().getLandExpropriation() == 1) {
                            sheet.addCell(new Label(1, num, "有", wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(1, num, "", wcf3));
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "就诊医院",wcf4));
                    sheet.addCell(new Label(1, num, caseInfo.getExtend2().getVisitingHospital()==null?"":caseInfo.getExtend2().getVisitingHospital(),wcf3));
                    sheet.addCell(new Label(2, num, "受伤部位",wcf4));
//                    String partName = findPartName(apply.getInjuredPart());
                    sheet.addCell(new Label(3, num, apply.getInjuredPartStr(),wcf3));
                    sheet.addCell(new Label(4, num, "治疗方式",wcf4));
                    if(apply!=null && apply.getTreatmentMethod()!=null){
                        if(apply.getTreatmentMethod() == 1 ){
                            sheet.addCell(new Label(5, num, "门诊",wcf3));
                        }else if(apply.getTreatmentMethod() == 2 ){
                            sheet.addCell(new Label(5, num, "急诊留观",wcf3));
                        }else if(apply.getTreatmentMethod() == 3 ){
                            sheet.addCell(new Label(5, num, "住院",wcf3));
                        }else if(apply.getTreatmentMethod() == 4 ){
                            sheet.addCell(new Label(5, num, "未就诊",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(5, num, "未就诊",wcf3));
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "伤情诊断",wcf4));
                    sheet.mergeCells(1, num, 5, 0);//
                    if(estimateReport!=null){
                        sheet.addCell(new Label(1, num, estimateReport.getInjuryDiagnose()==null?"":estimateReport.getInjuryDiagnose(),wcf3));
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "是否已做鉴定",wcf4));
                    if(claim!=null && claim.getDetermineType()!=null) {
                        if ("2".equals(claim.getDetermineType())) {
                            sheet.addCell(new Label(1, num, "无", wcf3));
                        } else if ("1".equals(claim.getDetermineType())) {
                            sheet.addCell(new Label(1, num, "有", wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(1, num, "", wcf3));
                    }
                    sheet.addCell(new Label(2, num, "伤残等级",wcf4));
                    sheet.mergeCells(3, num, 5, 0);//
                    if(estimateReport!=null){
                        sheet.addCell(new Label(3, num, estimateReport.getInvalidismGradeStr()==null?"":estimateReport.getInvalidismGradeStr(),wcf3));
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "肇事方",wcf4));
                    if(claim!=null){
                        sheet.addCell(new Label(1, num, claim.getPartyName()==null?"":claim.getPartyName(),wcf3));
                    }
                    sheet.addCell(new Label(2, num, "肇事方电话",wcf4));
                    if(claim!=null){
                        sheet.addCell(new Label(3, num, claim.getPartyTel()==null?"":claim.getPartyTel(),wcf3));
                    }
                    sheet.addCell(new Label(4, num, "车牌号码",wcf4));
                    if(claim!=null){
                        sheet.addCell(new Label(5, num, claim.getPartyTel()==null?"":claim.getPartyTel(),wcf3));
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "出险时间",wcf4));
                    if(caseEntrustInput.getAccidentTime()!=null){
                        sheet.addCell(new DateTime(1, num, caseEntrustInput.getAccidentTime()));
                    }else{
                        sheet.addCell(new Label(1, num, "",wcf3));
                    }
                    sheet.addCell(new Label(2, num, "交强险保险公司",wcf4));
                    sheet.addCell(new Label(3, num, caseInfo.getExtend2().getInsuranceCompany()==null?"":caseInfo.getExtend2().getInsuranceCompany(),wcf3));
                    sheet.addCell(new Label(4, num, "商业险保险公司",wcf4));
                    sheet.addCell(new Label(5, num, caseInfo.getExtend2().getInsuranceCompany2()==null?"":caseInfo.getExtend2().getInsuranceCompany2(),wcf3));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "是否多车事故",wcf4));
                    if(caseInfo.getExtend2().getAccidentType()!=null){
                        if(caseInfo.getExtend2().getAccidentType() ==1){
                            sheet.addCell(new Label(1, num, "单方事故",wcf3));
                        }else if(caseInfo.getExtend2().getAccidentType() ==2){
                            sheet.addCell(new Label(1, num, "双方事故",wcf3));
                        }else if(caseInfo.getExtend2().getAccidentType() ==3){
                            sheet.addCell(new Label(1, num, "多方事故",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(1, num, "",wcf3));
                    }

                    if(apply!=null && apply.getMyStatus()!=null) {
                        if (apply.getMyStatus() == 1) {//我方身份(1:肇事方,2:受害方)
                            sheet.addCell(new Label(2, num, "伤者交通状态",wcf4));
                            if (apply.getOtherTarfficStatus() == 1) {
                                sheet.addCell(new Label(3, num, "机动车",wcf3));
                            } else if (apply.getOtherTarfficStatus() == 2) {
                                sheet.addCell(new Label(3, num, "非机动车",wcf3));
                            } else if (apply.getOtherTarfficStatus() == 3) {
                                sheet.addCell(new Label(3, num, "行人",wcf3));
                            }
                            sheet.addCell(new Label(4, num, "肇事方交通状态",wcf4));
                            if (apply.getMyTarfficStatus() == 1) {
                                sheet.addCell(new Label(5, num, "机动车",wcf3));
                            } else if (apply.getMyTarfficStatus() == 2) {
                                sheet.addCell(new Label(5, num, "非机动车",wcf3));
                            } else if (apply.getMyTarfficStatus() == 3) {
                                sheet.addCell(new Label(5, num, "行人",wcf3));
                            }
                        } else if (apply.getMyStatus() == 2) {
                            sheet.addCell(new Label(2, num, "伤者交通状态",wcf4));
                            if (apply.getMyTarfficStatus() == 1) {
                                sheet.addCell(new Label(3, num, "机动车",wcf3));
                            } else if (apply.getMyTarfficStatus() == 2) {
                                sheet.addCell(new Label(3, num, "非机动车",wcf3));
                            } else if (apply.getMyTarfficStatus() == 3) {
                                sheet.addCell(new Label(3, num, "行人",wcf3));
                            }
                            sheet.addCell(new Label(4, num, "肇事方交通状态",wcf4));
                            if (apply.getOtherTarfficStatus() == 1) {
                                sheet.addCell(new Label(5, num, "机动车",wcf3));
                            } else if (apply.getOtherTarfficStatus() == 2) {
                                sheet.addCell(new Label(5, num, "非机动车",wcf3));
                            } else if (apply.getOtherTarfficStatus() == 3) {
                                sheet.addCell(new Label(5, num, "行人",wcf3));
                            }
                        }
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "委托人事故责任",wcf4));
                    if(caseInfo.getExtend2().getOurResponsibilities() !=null){
                        if(caseInfo.getExtend2().getOurResponsibilities() == 1){
                            sheet.addCell(new Label(1, num, "全责",wcf3));
                        }else if(caseInfo.getExtend2().getOurResponsibilities() == 2){
                            sheet.addCell(new Label(1, num, "主责",wcf3));
                        }else if(caseInfo.getExtend2().getOurResponsibilities() == 3){
                            sheet.addCell(new Label(1, num, "同责",wcf3));
                        }else if(caseInfo.getExtend2().getOurResponsibilities() == 4){
                            sheet.addCell(new Label(1, num, "次责",wcf3));
                        }else if(caseInfo.getExtend2().getOurResponsibilities() == 5){
                            sheet.addCell(new Label(1, num, "无责",wcf3));
                        }else if(caseInfo.getExtend2().getOurResponsibilities() == 6){
                            sheet.addCell(new Label(1, num, "责任无法认定",wcf3));
                        }
                    }
                    sheet.addCell(new Label(2, num, "肇事方赔偿比例",wcf4));
                    sheet.addCell(new Number(3, num, caseInfo.getExtend2().getResponsiblePartyPayRatio()==null?0D:caseInfo.getExtend2().getResponsiblePartyPayRatio(),wcf3));
                    if(caseInfo.getExtend2().getResponsiblePartyPayRatio()!=null){
                        sheet.addCell(new Number(3, num, caseInfo.getExtend2().getResponsiblePartyPayRatio(),wcf3));
                    }else{
                        sheet.addCell(new Label(3, num, "",wcf3));
                    }
                    sheet.addCell(new Label(4, num, "肇事方有无免责情形",wcf4));
                    if(claim.getDisclaimerType()!=null){
                        if("0".equals(claim.getDisclaimerType())){
                            sheet.addCell(new Label(5, num, "无",wcf3));
                        }else if("1".equals(claim.getDisclaimerType())){
                            sheet.addCell(new Label(5, num, "有",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(5, num, "",wcf3));
                    }
                    num = num + 1;//空一行
                    sheet.mergeCells(0, num, 5, 1);//
                    sheet.addCell(new Label(0, num, "",wcf3));
                }
                if ("2".equals(type)) {
                    //产品类型
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "产品类型",wcf2));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "签约产品",wcf4));
                    sheet.mergeCells(1, num, 5, 0);//
                    if(caseInfo.getType() !=null) {
                        if (caseInfo.getType() == 2) {
                            sheet.addCell(new Label(1, num, "索赔通",wcf3));
                        } else if (caseInfo.getType() == 1) {
                            sheet.addCell(new Label(1, num, "乐赔宝",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(1, num, "",wcf3));
                    }
                    num = num + 1;
                    sheet.mergeCells(0, num, 5, 1);//
                    sheet.addCell(new Label(0, num, "",wcf3));
                }

                if ("4".equals(type)) {
                    //收费方式
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "收费方式",wcf2));
                    num = num + 1;
                    if(caseSignGuidance!=null && caseSignGuidance.getSignProduct()!=null){
                        if(caseSignGuidance.getSignProduct() ==1){ //乐赔宝
                            sheet.addCell(new Label(0, num, "产品价格",wcf4));
                            if(payEstimateInquiry.getAgentServiceFee()!=null){
                                sheet.addCell(new Number(1, num, payEstimateInquiry.getAgentServiceFee(),wcf3));
                            }else{
                                sheet.addCell(new Label(1, num, "",wcf3));
                            }
                            sheet.addCell(new Label(2, num, "服务费金额",wcf4));
                            sheet.mergeCells(3, num, 5, 0);//
                            if(payEstimateInquiry.getAgentServiceFee()!=null){
                                sheet.addCell(new Number(3, num, payEstimateInquiry.getAgentServiceFee(),wcf3));
                            }else{
                                sheet.addCell(new Label(3, num, "",wcf3));
                            }
                            num = num + 1;
                        }else if(caseSignGuidance.getSignProduct() ==2){//索赔通
                            if(payEstimateInquiry.getCalculationType()==1){
                                sheet.addCell(new Label(0, num, "服务费方式",wcf4));
                                sheet.addCell(new Label(1, num, "固定收费",wcf3));
                                sheet.addCell(new Label(2, num, "服务费金额",wcf4));
                                if(payEstimateInquiry.getAgentServiceFee()!=null){
                                    sheet.addCell(new Number(3, num, payEstimateInquiry.getAgentServiceFee(),wcf3));
                                }else{
                                    sheet.addCell(new Label(3, num, "",wcf3));
                                }
                                sheet.addCell(new Label(4, num, "预收金额",wcf4));
                                if(payEstimateInquiry.getRealDeFee()!=null){
                                    sheet.addCell(new Number(5, num, payEstimateInquiry.getRealDeFee(),wcf3));
                                }else{
                                    sheet.addCell(new Label(5, num, "",wcf3));
                                }
                                num = num + 1;
                            }else if(payEstimateInquiry.getCalculationType()==2){
                                sheet.addCell(new Label(0, num, "服务费方式",wcf4));
                                sheet.addCell(new Label(1, num, "比例收费",wcf3));
                                sheet.addCell(new Label(2, num, "比例收费基数",wcf4));
                                if(payEstimateInquiry.getRateBaseType()!=null){
                                    if(payEstimateInquiry.getRateBaseType()==1){
                                        sheet.addCell(new Label(3, num, "赔偿金额",wcf3));
                                    }else if(payEstimateInquiry.getRateBaseType()==2){
                                        sheet.addCell(new Label(3, num, "赔偿款-医疗费",wcf3));
                                    }else if(payEstimateInquiry.getRateBaseType()==2){
                                        sheet.addCell(new Label(3, num, "伤残赔偿金+精神损失费",wcf3));
                                    }
                                }else{
                                    sheet.addCell(new Label(3, num, "",wcf3));
                                }
                                num = num + 1;
                                sheet.addCell(new Label(0, num, "预收金额",wcf4));
                                sheet.mergeCells(1, num, 5, 0);//
                                if(payEstimateInquiry.getRealDeFee()!=null){
                                    sheet.addCell(new Number(1, num, payEstimateInquiry.getRealDeFee(),wcf3));
                                }else{
                                    sheet.addCell(new Label(1, num, "",wcf3));
                                }
                                num = num + 1;
                            }
                        }
                    }
                    sheet.mergeCells(0, num, 5, 1);//
                    sheet.addCell(new Label(0, num, "",wcf3));
                }
                if ("5".equals(type)) {
                    //签约指导
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "签约指导",wcf2));
                    num = num + 1;
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "基本信息",wcf4));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "委托人",wcf4));
                    sheet.addCell(new Label(1, num, caseEntrustInput.getInjuredPerson()==null?"":caseEntrustInput.getInjuredPerson(),wcf3));
                    sheet.addCell(new Label(2, num, "性别",wcf4));
                    if(caseInfo.getExtend2().getSex()!=null){
                        if(caseInfo.getExtend2().getSex()==1){
                            sheet.addCell(new Label(3, num, "男",wcf3));
                        }else if(caseInfo.getExtend2().getSex()==2){
                            sheet.addCell(new Label(3, num, "女",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(3, num, "",wcf3));
                    }
                    sheet.addCell(new Label(4, num, "年龄",wcf4));
                    if(caseInfo.getExtend2().getAge()!=null){
                        sheet.addCell(new Number(5, num, caseInfo.getExtend2().getAge(),wcf3));
                    }else{
                        sheet.addCell(new Label(5, num, "",wcf3));
                    }
                    num = num + 1;
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "户籍信息",wcf4));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "户籍性质",wcf4));
                    if (caseInfo.getExtend2().getDomicile() != null) {
                        if ("1".equals(caseInfo.getExtend2().getDomicile())) {
                            sheet.addCell(new Label(1, num, "农业", wcf3));
                        }else if ("2".equals(caseInfo.getExtend2().getDomicile())) {
                            sheet.addCell(new Label(1, num, "非农业", wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(1, num, "", wcf3));
                    }

                    sheet.addCell(new Label(2, num, "户籍地址",wcf4));
                    sheet.mergeCells(3, num, 5, 0);//
                    sheet.addCell(new Label(3, num, caseInfo.getExtend2().getDomicileProvince()==null?"":caseInfo.getExtend2().getDomicileProvince()+caseInfo.getExtend2().getDomicileCity()==null?"":caseInfo.getExtend2().getDomicileCity()+caseInfo.getExtend2().getDomicileDistrict()==null?"":caseInfo.getExtend2().getDomicileDistrict()+caseInfo.getExtend2().getDomicileAddress()==null?"":caseInfo.getExtend2().getDomicileAddress(),wcf3));
                    num = num + 1;

                    //工作情况
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "工作情况",wcf4));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "工作是否一年以上",wcf4));
                    if(caseInfo.getExtend2().getIsJobSatisfy()!=null){
                        if(caseInfo.getExtend2().getIsJobSatisfy()==1){
                            sheet.addCell(new Label(1, num, "是",wcf3));
                        }else if(caseInfo.getExtend2().getIsJobSatisfy()==2){
                            sheet.addCell(new Label(1, num, "否",wcf3));
                        }
                    }
                    sheet.addCell(new Label(2, num, "是否公务员、事业单位",wcf4));
                    if(caseInfo.getExtend2().getIsServant()!=null){
                        if(caseInfo.getExtend2().getIsServant()==1){
                            sheet.addCell(new Label(3, num, "是",wcf3));
                        }else if(caseInfo.getExtend2().getIsServant()==2){
                            sheet.addCell(new Label(3, num, "否",wcf3));
                        }
                    }
                    sheet.addCell(new Label(4, num, "有无劳动合同",wcf4));
                    if(caseInfo.getExtend2().getLaborContract()!=null){
                        if(caseInfo.getExtend2().getLaborContract()==1){
                            sheet.addCell(new Label(5, num, "是",wcf3));
                        }else if(caseInfo.getExtend2().getLaborContract()==2){
                            sheet.addCell(new Label(5, num, "否",wcf3));
                        }
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "有无银行流水",wcf4));
                    if(caseInfo.getExtend2().getBankInfo()!=null){
                        if(caseInfo.getExtend2().getBankInfo()==1){
                            sheet.addCell(new Label(1, num, "是",wcf3));
                        }else if(caseInfo.getExtend2().getBankInfo()==2){
                            sheet.addCell(new Label(1, num, "否",wcf3));
                        }
                    }
                    sheet.addCell(new Label(2, num, "有无纳税证明",wcf4));
                    if(caseInfo.getExtend2().getTaxCertificate()!=null){
                        if(caseInfo.getExtend2().getTaxCertificate()==1){
                            sheet.addCell(new Label(3, num, "是",wcf3));
                        }else if(caseInfo.getExtend2().getTaxCertificate()==2){
                            sheet.addCell(new Label(3, num, "否",wcf3));
                        }
                    }
                    sheet.addCell(new Label(4, num, "有无误工证明",wcf4));
                    if(caseInfo.getExtend2().getWorkCertificate()!=null){
                        if(caseInfo.getExtend2().getWorkCertificate()==1){
                            sheet.addCell(new Label(5, num, "是",wcf3));
                        }else if(caseInfo.getExtend2().getWorkCertificate()==2){
                            sheet.addCell(new Label(5, num, "否",wcf3));
                        }
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "工资",wcf4));
                    sheet.addCell(new Number(1, num, caseInfo.getExtend2().getWages()==null?0D:caseInfo.getExtend2().getWages(),wcf3));
                    sheet.addCell(new Label(2, num, "其他",wcf4));
                    sheet.mergeCells(3, num, 5, 0);//
                    sheet.addCell(new Label(3, num, caseInfo.getExtend2().getOtherWorkInfo()==null?"":caseInfo.getExtend2().getOtherWorkInfo(),wcf3));
                    num = num + 1;

                    //居住情况
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "居住情况",wcf4));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "居住区域",wcf4));
                    sheet.addCell(new Label(1, num, caseInfo.getExtend2().getHabitation()==null?"":caseInfo.getExtend2().getHabitation(),wcf3));
                    if(caseInfo.getExtend2().getHabitation()!=null){
                        if("1".equals(caseInfo.getExtend2().getHabitation())){
                            sheet.addCell(new Label(1, num, "城镇",wcf3));
                        }else if("2".equals(caseInfo.getExtend2().getHabitation())){
                            sheet.addCell(new Label(1, num, "农村",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(1, num, "",wcf3));
                    }

                    sheet.addCell(new Label(2, num, "是否城镇居住一年以上",wcf4));
                    if(caseInfo.getExtend2().getIsTownSatisfy()!=null){
                        if(caseInfo.getExtend2().getIsTownSatisfy()==1){
                            sheet.addCell(new Label(3, num, "是",wcf3));
                        }else if(caseInfo.getExtend2().getIsTownSatisfy()==2){
                            sheet.addCell(new Label(3, num, "否",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(3, num, "",wcf3));
                    }
                    sheet.addCell(new Label(4, num, "有无居住证/暂住证",wcf4));
                    if(caseInfo.getExtend2().getResidencePermit()!=null){
                        if(caseInfo.getExtend2().getResidencePermit()==1){
                            sheet.addCell(new Label(5, num, "是",wcf3));
                        }else if(caseInfo.getExtend2().getResidencePermit()==2){
                            sheet.addCell(new Label(5, num, "否",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(5, num, "",wcf3));
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "是否拆迁/征地",wcf4));
                    if(caseInfo.getExtend2().getLandExpropriation()!=null){
                        if(caseInfo.getExtend2().getLandExpropriation()==1){
                            sheet.addCell(new Label(5, num, "是",wcf3));
                        }else if(caseInfo.getExtend2().getLandExpropriation()==2){
                            sheet.addCell(new Label(5, num, "否",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(5, num, "",wcf3));
                    }
                    sheet.addCell(new Label(2, num, "其他",wcf4));
                    sheet.mergeCells(3, num, 5, 0);//
                    sheet.addCell(new Label(3, num, caseInfo.getExtend2().getOtherLive()==null?"":caseInfo.getExtend2().getOtherLive(),wcf3));
                    num = num + 1;

                    //事故责任
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "事故责任",wcf4));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "事故类型",wcf4));
                    if(caseInfo.getExtend2().getAccidentType()!=null){
                        if(caseInfo.getExtend2().getAccidentType()==1){
                            sheet.addCell(new Label(1, num, "单方事故",wcf3));
                        }else if(caseInfo.getExtend2().getAccidentType()==2){
                            sheet.addCell(new Label(1, num, "双方事故",wcf3));
                        }else if(caseInfo.getExtend2().getAccidentType()==3){
                            sheet.addCell(new Label(1, num, "多方事故",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(1, num, "",wcf3));
                    }
                    sheet.addCell(new Label(2, num, "对方是否为机动车",wcf4));
                    if(caseInfo.getExtend2().getIsMotorVehicle()!=null){
                        if(caseInfo.getExtend2().getIsMotorVehicle()==1){
                            sheet.addCell(new Label(3, num, "是",wcf3));
                        }else if(caseInfo.getExtend2().getIsMotorVehicle()==2){
                            sheet.addCell(new Label(3, num, "否",wcf3));
                        }
                    }
                    sheet.addCell(new Label(4, num, "对方是否有人伤",wcf4));
                    if(caseInfo.getExtend2().getIsHumanInjury()!=null){
                        if(caseInfo.getExtend2().getIsHumanInjury()==1){
                            sheet.addCell(new Label(5, num, "是",wcf3));
                        }else if(caseInfo.getExtend2().getIsHumanInjury()==2){
                            sheet.addCell(new Label(5, num, "否",wcf3));
                        }
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "对方是否有车物损",wcf4));
                    if(caseInfo.getExtend2().getIsVehicleDamage()!=null){
                        if(caseInfo.getExtend2().getIsVehicleDamage()==1){
                            sheet.addCell(new Label(1, num, "是",wcf3));
                        }else if(caseInfo.getExtend2().getIsVehicleDamage()==2){
                            sheet.addCell(new Label(1, num, "否",wcf3));
                        }
                    }
                    sheet.addCell(new Label(2, num, "我方责任",wcf4));
                    if(caseInfo.getExtend2().getOurResponsibilities()!=null){
                        if(caseInfo.getExtend2().getOurResponsibilities() == 1){
                            sheet.addCell(new Label(3, num, "全责",wcf3));
                        }else if(caseInfo.getExtend2().getOurResponsibilities() == 2){
                            sheet.addCell(new Label(3, num, "主责",wcf3));
                        }else if(caseInfo.getExtend2().getOurResponsibilities() == 3){
                            sheet.addCell(new Label(3, num, "同责",wcf3));
                        }else if(caseInfo.getExtend2().getOurResponsibilities() == 4){
                            sheet.addCell(new Label(3, num, "次责",wcf3));
                        }else if(caseInfo.getExtend2().getOurResponsibilities() == 5){
                            sheet.addCell(new Label(3, num, "无责",wcf3));
                        }else if(caseInfo.getExtend2().getOurResponsibilities() == 6){
                            sheet.addCell(new Label(3, num, "责任无法认定",wcf3));
                        }
                    }
                    sheet.addCell(new Label(4, num, "本方交通方式",wcf4));
                    if(caseInfo.getExtend2().getOurTransportation()!=null){
                        if(caseInfo.getExtend2().getOurTransportation()==1){
                            sheet.addCell(new Label(5, num, "机动车",wcf3));
                        }else if(caseInfo.getExtend2().getOurTransportation()==2){
                            sheet.addCell(new Label(5, num, "非机动车",wcf3));
                        }
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "处理交警队",wcf4));
                    sheet.addCell(new Label(1, num, caseInfo.getExtend2().getPoliceTeam()==null?"":caseInfo.getExtend2().getPoliceTeam(),wcf3));
                    sheet.addCell(new Label(2, num, "其他",wcf4));
                    sheet.mergeCells(3, num, 5, 0);//
                    sheet.addCell(new Label(3, num, caseInfo.getExtend2().getOtherAccidentLiability()==null?"":caseInfo.getExtend2().getOtherAccidentLiability(),wcf3));
                    num = num + 1;

                    //承保信息
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "承保信息",wcf4));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "有无保险",wcf4));
                    if(caseInfo.getExtend2().getAcceptInsurance()!=null){
                        if(caseInfo.getExtend2().getAcceptInsurance()==1){
                            sheet.addCell(new Label(1, num, "有",wcf3));
                        }else if(caseInfo.getExtend2().getAcceptInsurance()==2){
                            sheet.addCell(new Label(1, num, "无",wcf3));
                        }
                    }
                    sheet.addCell(new Label(2, num, "交强险保险公司",wcf4));
                    sheet.addCell(new Label(3, num, caseInfo.getExtend2().getInsuranceCompany()==null?"":caseInfo.getExtend2().getInsuranceCompany(),wcf3));
                    sheet.addCell(new Label(4, num, "商业险保险公司",wcf4));
                    sheet.addCell(new Label(5, num, caseInfo.getExtend2().getInsuranceCompany2()==null?"":caseInfo.getExtend2().getInsuranceCompany2(),wcf3));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "承保地",wcf4));
                    sheet.addCell(new Label(1, num, caseInfo.getExtend2().getUnderwritingPlace()==null?"":caseInfo.getExtend2().getUnderwritingPlace(),wcf3));
                    sheet.addCell(new Label(2, num, "商业险",wcf4));
                    sheet.addCell(new Number(3, num, caseInfo.getExtend2().getCommercialInsurance()==null?0D:caseInfo.getExtend2().getCommercialInsurance(),wcf3));
                    sheet.addCell(new Label(4, num, "有无不计免赔",wcf4));
                    if(caseInfo.getExtend2().getExcludingDeductible()!=null){
                        if(caseInfo.getExtend2().getExcludingDeductible()==1){
                            sheet.addCell(new Label(5, num, "有",wcf3));
                        }else if(caseInfo.getExtend2().getExcludingDeductible()==2){
                            sheet.addCell(new Label(5, num, "无",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(5, num, "",wcf3));
                    }
                    num = num + 1;

                    //其他信息
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "其他信息",wcf4));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "伤情诊断",wcf4));
                    sheet.mergeCells(1, num, 5, 0);//
                    sheet.addCell(new Label(1, num, caseInfo.getExtend2().getInjuryDiagnosis()==null?"":caseInfo.getExtend2().getInjuryDiagnosis(),wcf3));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "就诊医院",wcf4));
                    sheet.addCell(new Label(1, num, caseInfo.getExtend2().getVisitingHospital()==null?"":caseInfo.getExtend2().getVisitingHospital(),wcf3));
                    sheet.addCell(new Label(2, num, "是否同意鉴定",wcf4));
                    if(caseInfo.getExtend2().getIsAgreeAppraisal()!=null){
                        if(caseInfo.getExtend2().getIsAgreeAppraisal()==1){
                            sheet.addCell(new Label(3, num, "是",wcf3));
                        }else if(caseInfo.getExtend2().getIsAgreeAppraisal()==2){
                            sheet.addCell(new Label(3, num, "否",wcf3));
                        }
                    }
                    sheet.addCell(new Label(4, num, "是否同意诉讼",wcf4));
                    if(caseInfo.getExtend2().getIsAgreeLitigation()!=null){
                        if(caseInfo.getExtend2().getIsAgreeLitigation()==1){
                            sheet.addCell(new Label(5, num, "是",wcf3));
                        }else if(caseInfo.getExtend2().getIsAgreeLitigation()==2){
                            sheet.addCell(new Label(5, num, "否",wcf3));
                        }
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "客户意向",wcf4));
                    sheet.mergeCells(1, num, 5, 0);//
                    sheet.addCell(new Label(1, num, caseInfo.getExtend2().getCustomerIntention()==null?"":caseInfo.getExtend2().getCustomerIntention(),wcf3));
                    num = num + 1;

                    //指导结果
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "指导结果",wcf4));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "赔偿标准分类",wcf4));
                    if(caseSignGuidance.getCompensatePlan()!=null){
                        if(caseSignGuidance.getCompensatePlan()==1){
                            sheet.addCell(new Label(1, num, "A",wcf3));
                        }else if(caseSignGuidance.getCompensatePlan()==2){
                            sheet.addCell(new Label(1, num, "B",wcf3));
                        }else if(caseSignGuidance.getCompensatePlan()==3){
                            sheet.addCell(new Label(1, num, "C",wcf3));
                        }else if(caseSignGuidance.getCompensatePlan()==4){
                            sheet.addCell(new Label(1, num, "D",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(1, num, "",wcf3));
                    }
                    sheet.addCell(new Label(2, num, "客户分类",wcf4));
                    if(caseSignGuidance.getCustomerType()!=null){
                        if(caseSignGuidance.getCustomerType()==1){
                            sheet.addCell(new Label(3, num, "A",wcf3));
                        }else if(caseSignGuidance.getCustomerType()==2){
                            sheet.addCell(new Label(3, num, "B",wcf3));
                        }else if(caseSignGuidance.getCustomerType()==3){
                            sheet.addCell(new Label(3, num, "C",wcf3));
                        }else if(caseSignGuidance.getCustomerType()==4){
                            sheet.addCell(new Label(3, num, "D",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(3, num, "",wcf3));
                    }
                    sheet.addCell(new Label(4, num, "案件产品归类",wcf4));
                    if(caseSignGuidance.getSignProduct()!=null){
                        if(caseSignGuidance.getSignProduct()==1){
                            sheet.addCell(new Label(5, num, "乐赔宝",wcf3));
                        }else if(caseSignGuidance.getSignProduct()==2){
                            sheet.addCell(new Label(5, num, "索赔通",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(5, num, "",wcf3));
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "签约指导意见",wcf4));
                    sheet.mergeCells(1, num, 5, 0);//
                    sheet.addCell(new Label(1, num, caseSignGuidance.getSignGuidance()==null?"":caseSignGuidance.getSignGuidance(),wcf3));
                    num = num + 1;
                    sheet.mergeCells(0, num, 5, 1);//
                    sheet.addCell(new Label(0, num, "",wcf3));
                }
                if ("6".equals(type)) {
                    //伤残预估
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "伤残预估",wcf2));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "预估时间",wcf4));
                    if(estimateReport.getApplyTime()!=null){
                        sheet.addCell(new DateTime(1, num, estimateReport.getApplyTime()));
                    }else{
                        sheet.addCell(new Label(1, num, "",wcf3));
                    }
                    sheet.addCell(new Label(2, num, "治疗方式",wcf4));
                    if(estimateReport.getIsOperation()!=null){
                        if(estimateReport.getIsOperation()==0){
                            sheet.addCell(new Label(3, num, "非手术",wcf3));
                        }else if(estimateReport.getIsOperation()==1){
                            sheet.addCell(new Label(3, num, "手术",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(3, num, "",wcf3));
                    }
                    sheet.addCell(new Label(4, num, "备注",wcf4));
                    sheet.addCell(new Label(5, num, estimateReport.getReportDesc()==null?"":estimateReport.getReportDesc(),wcf3));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "伤情诊断",wcf4));
                    sheet.mergeCells(1, num, 5, 0);//
                    sheet.addCell(new Label(1, num, estimateReport.getInjuryDiagnose()==null?"":estimateReport.getInjuryDiagnose(),wcf3));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "评残依据",wcf4));
                    sheet.mergeCells(1, num, 5, 0);//
                    sheet.addCell(new Label(1, num, estimateReport.getReportBasis()==null?"":estimateReport.getReportBasis(),wcf3));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "预估伤残等级",wcf4));
                    sheet.mergeCells(1, num, 5, 0);//
                    sheet.addCell(new Label(1, num, estimateReport.getInvalidismGradeStr()==null?"":estimateReport.getInvalidismGradeStr(),wcf3));
                    num = num + 1;
                    sheet.mergeCells(0, num, 5, 1);//
                    sheet.addCell(new Label(0, num, "",wcf3));
                }
                if ("7".equals(type)) {
                    //测算报告
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "测算报告",wcf2));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "事故责任",wcf4));
                    sheet.mergeCells(1, num, 5, 0);//
                    if(apply.getMyAccidentLiability()!=null){
                        if(apply.getMyAccidentLiability()==1){
                            sheet.addCell(new Label(1, num, "全部责任",wcf3));
                        }else if(apply.getMyAccidentLiability()==2){
                            sheet.addCell(new Label(1, num, "主要责任",wcf3));
                        }else if(apply.getMyAccidentLiability()==3){
                            sheet.addCell(new Label(1, num, "同等责任",wcf3));
                        }else if(apply.getMyAccidentLiability()==4){
                            sheet.addCell(new Label(1, num, "次要责任",wcf3));
                        }else if(apply.getMyAccidentLiability()==5){
                            sheet.addCell(new Label(1, num, "无责任",wcf3));
                        }else if(apply.getMyAccidentLiability()==6){
                            sheet.addCell(new Label(1, num, "责任无法认定",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(1, num, "",wcf3));
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "赔偿项目",wcf4));
                    sheet.addCell(new Label(1, num, "实际损失（元）",wcf4));
                    sheet.addCell(new Label(2, num, "实际损失审核金额（元）",wcf4));
                    sheet.addCell(new Label(3, num, "交强险可赔偿金额（元）",wcf4));
                    sheet.addCell(new Label(4, num, "商业险可赔偿金额（元）",wcf4));
                    sheet.addCell(new Label(5, num, "肇事方可赔偿金额（元）",wcf4));
                    num = num + 1;
                    if (paymentEstimateReportDtos != null && !paymentEstimateReportDtos.isEmpty()) {
                        for (int i = 0; i < paymentEstimateReportDtos.size(); i++) {
                            sheet.addCell(new Label(0, num, paymentEstimateReportDtos.get(i).getPaymentProject() == null ? "" : paymentEstimateReportDtos.get(i).getPaymentProject(),wcf3));
                            sheet.addCell(new Number(1, num, paymentEstimateReportDtos.get(i).getMedicalFee() == null ? 0 : paymentEstimateReportDtos.get(i).getMedicalFee(),wcf3));
                            sheet.addCell(new Number(2, num, paymentEstimateReportDtos.get(i).getCheckMedicalFee() == null ? 0 : paymentEstimateReportDtos.get(i).getCheckMedicalFee(),wcf3));
                            sheet.addCell(new Number(3, num, paymentEstimateReportDtos.get(i).getCompulsoryInsuranceFee() == null ? 0 : paymentEstimateReportDtos.get(i).getCompulsoryInsuranceFee(),wcf3));
                            sheet.addCell(new Number(4, num, paymentEstimateReportDtos.get(i).getCommercialInsuranceFee() == null ? 0 : paymentEstimateReportDtos.get(i).getCommercialInsuranceFee(),wcf3));
                            sheet.addCell(new Number(5, num, paymentEstimateReportDtos.get(i).getCauseTroubleFee() == null ? 0 : paymentEstimateReportDtos.get(i).getCauseTroubleFee(),wcf3));
                            num = num + 1;
                        }
                    }
                    sheet.mergeCells(0, num, 5, 1);//
                    sheet.addCell(new Label(0, num, "",wcf3));
                }
                if ("8".equals(type)) {
                    //评估服务费
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "评估服务费",wcf2));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "服务费金额",wcf4));
                    sheet.mergeCells(1, num, 5, 0);//
                    sheet.addCell(new Number(1, num, payEstimateInquiry.getAgentServiceFee()==null?0D:payEstimateInquiry.getAgentServiceFee(),wcf3));
                    num = num + 1;
                    sheet.mergeCells(0, num, 5, 1);//
                    sheet.addCell(new Label(0, num, "",wcf3));
                }
                if ("9".equals(type)) {
                    //索赔指导
                    if(caseClaimGuidance!=null){
                        sheet.mergeCells(0, num, 5, 0);//
                        sheet.addCell(new Label(0, num, "索赔指导",wcf2));
                        num = num + 1;
                        sheet.addCell(new Label(0, num, "伤残情况",wcf4));
                        sheet.mergeCells(1, num, 5, 0);//
                        sheet.addCell(new Label(1, num, caseClaimGuidance.getDisability()==null?"":caseClaimGuidance.getDisability(),wcf3));
                        num = num + 1;
                        sheet.addCell(new Label(0, num, "保险责任情况",wcf4));
                        sheet.mergeCells(1, num, 5, 0);//
                        sheet.addCell(new Label(1, num, caseClaimGuidance.getInsuranceLiability()==null?"":caseClaimGuidance.getInsuranceLiability(),wcf3));
                        num = num + 1;
                        sheet.addCell(new Label(0, num, "误工费情况",wcf4));
                        sheet.mergeCells(1, num, 5, 0);//
                        sheet.addCell(new Label(1, num, caseClaimGuidance.getDelayWork()==null?"":caseClaimGuidance.getDelayWork(),wcf3));
                        num = num + 1;
                        sheet.addCell(new Label(0, num, "承保情况",wcf4));
                        sheet.mergeCells(1, num, 5, 0);//
                        sheet.addCell(new Label(1, num, caseClaimGuidance.getInsuranceLiability()==null?"":caseClaimGuidance.getInsuranceLiability(),wcf3));
                        num = num + 1;
                        sheet.addCell(new Label(0, num, "事故性质情况",wcf4));
                        sheet.mergeCells(1, num, 5, 0);//
                        sheet.addCell(new Label(1, num, caseClaimGuidance.getAccidentProperty()==null?"":caseClaimGuidance.getAccidentProperty(),wcf3));
                        num = num + 1;
                        sheet.addCell(new Label(0, num, "其他",wcf4));
                        sheet.mergeCells(1, num, 5, 0);//
                        sheet.addCell(new Label(1, num, caseClaimGuidance.getOther()==null?"":caseClaimGuidance.getOther(),wcf3));
                        num = num + 1;
                    }
                    sheet.mergeCells(0, num, 5, 1);//
                    sheet.addCell(new Label(0, num, "",wcf3));
                }
                if ("10".equals(type)) {
                    //索赔预案
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "索赔预案",wcf2));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "索赔员",wcf4));
                    sheet.addCell(new Label(1, num, caseInfo.getClaimantName()==null?"":caseInfo.getClaimantName(),wcf3));
                    sheet.addCell(new Label(2, num, "索赔受理时间",wcf4));
                    if(caseInfo.getClaimantDate()!=null){
                        sheet.addCell(new DateTime(3, num, caseInfo.getClaimantDate()));
                    }else{
                        sheet.addCell(new Label(3, num, "",wcf3));
                    }
                    sheet.addCell(new Label(4, num, "适用标准",wcf4));
                    if(claim.getAppStandType()!=null){
                        if("1".equals(claim.getAppStandType())){
                            sheet.addCell(new Label(5, num, "城镇",wcf3));
                        }else if("2".equals(claim.getAppStandType())){
                            sheet.addCell(new Label(5, num, "农村",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(5, num, "",wcf3));
                    }
                    num = num + 1;
                    sheet.mergeCells(0, num, 5, 1);//
                    sheet.addCell(new Label(0, num, "",wcf3));
                }
                if ("11".equals(type)) {
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "赔偿预案调解金额",wcf2));
                    num = num + 1;

                    sheet.addCell(new Label(0, num, "赔偿项目",wcf4));
                    sheet.addCell(new Label(1, num, "预案金额（元）",wcf4));
                    sheet.addCell(new Label(2, num, "",wcf3));
                    sheet.addCell(new Label(3, num, "预案审核（元）",wcf4));
                    sheet.addCell(new Label(4, num, "",wcf3));
                    sheet.addCell(new Label(5, num, "备注",wcf4));
                    num = num + 1;
                    if (claimReports != null && claimReports.size()>0) {
                        for (int i = 0; i < claimReports.size(); i++) {
                            sheet.addCell(new Label(0, num, claimReports.get(i).getProjectName() == null ? "" : claimReports.get(i).getProjectName(),wcf3));
                            sheet.addCell(new Number(1, num, claimReports.get(i).getOpinionMoney() == null ? 0 : claimReports.get(i).getOpinionMoney(),wcf3));
                            sheet.addCell(new Label(2, num, "",wcf3));
                            sheet.addCell(new Number(3, num, claimReports.get(i).getAuditingMoney() == null ? 0 : claimReports.get(i).getAuditingMoney(),wcf3));
                            sheet.addCell(new Label(4, num, "",wcf3));
                            sheet.addCell(new Label(5, num, claimReports.get(i).getCheckBasis() == null ? "" : claimReports.get(i).getCheckBasis(),wcf3));
                            num = num + 1;
                        }
                    }
                    sheet.addCell(new Label(0, num, "交强险",wcf4));
                    sheet.mergeCells(1, num, 2, 0);//
                    if(claim.getCpsMoney()!=null){
                        sheet.addCell(new Number(1, num,claim.getCpsMoney(),wcf3));
                    }else{
                        sheet.addCell(new Label(1, num,"",wcf3));
                    }
                    sheet.addCell(new Label(3, num, "商业三者险",wcf4));
                    sheet.mergeCells(4, num, 5, 0);//
                    if(claim.getCocMoney()!=null){
                        sheet.addCell(new Number(5, num,claim.getCocMoney(),wcf3));
                    }else{
                        sheet.addCell(new Label(5, num,"",wcf3));
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "备注",wcf4));
                    sheet.mergeCells(1, num, 5, 0);//
                    sheet.addCell(new Label(1, num, claim.getCaseDesc()==null?"":claim.getCaseDesc(),wcf3));
                    num = num + 1;
                    sheet.mergeCells(0, num, 5, 1);//
                    sheet.addCell(new Label(0, num, "",wcf3));
                }
                if ("12".equals(type)) {
                    //诉讼预案
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "诉讼预案",wcf2));
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "诉讼员",wcf4));
                    sheet.addCell(new Label(1, num, caseInfo.getLegalUserName()==null?"":caseInfo.getLegalUserName(),wcf3));
                    sheet.addCell(new Label(2, num, "诉讼受理时间",wcf4));
                    if(caseInfo.getLegalDate()!=null){
                        sheet.addCell(new DateTime(3, num, caseInfo.getLegalDate()));
                    }else{
                        sheet.addCell(new Label(3, num, "",wcf3));
                    }
                    sheet.addCell(new Label(4, num, "适用标准",wcf4));
                    if(claimLegal.getAppStandType()!=null){
                        if("1".equals(claimLegal.getAppStandType())){
                            sheet.addCell(new Label(5, num, "城镇",wcf3));
                        }else if("2".equals(claimLegal.getAppStandType())){
                            sheet.addCell(new Label(5, num, "农村",wcf3));
                        }
                    }else{
                        sheet.addCell(new Label(5, num, "",wcf3));
                    }
                    num = num + 1;
                    sheet.mergeCells(0, num, 5, 1);//
                    sheet.addCell(new Label(0, num, "",wcf3));
                }
                if ("13".equals(type)) {
                    sheet.mergeCells(0, num, 5, 0);//
                    sheet.addCell(new Label(0, num, "赔偿预案诉讼金额",wcf2));
                    num = num + 1;

                    sheet.addCell(new Label(0, num, "赔偿项目",wcf4));
                    sheet.addCell(new Label(1, num, "预案金额（元）",wcf4));
                    sheet.addCell(new Label(2, num, "",wcf3));
                    sheet.addCell(new Label(3, num, "预案审核（元）",wcf4));
                    sheet.addCell(new Label(4, num, "",wcf3));
                    sheet.addCell(new Label(5, num, "备注",wcf4));
                    num = num + 1;
                    if (claimReportLegals != null && claimReportLegals.size()>0) {
                        for (int i = 0; i < claimReportLegals.size(); i++) {
                            sheet.addCell(new Label(0, num, claimReportLegals.get(i).getProjectName() == null ? "" : claimReportLegals.get(i).getProjectName(),wcf3));
                            sheet.addCell(new Number(1, num, claimReportLegals.get(i).getOpinionMoney() == null ? 0 : claimReportLegals.get(i).getOpinionMoney(),wcf3));
                            sheet.addCell(new Label(2, num, "",wcf3));
                            sheet.addCell(new Number(3, num, claimReportLegals.get(i).getAuditingMoney() == null ? 0 : claimReportLegals.get(i).getAuditingMoney(),wcf3));
                            sheet.addCell(new Label(4, num, "",wcf3));
                            sheet.addCell(new Label(5, num, claimReportLegals.get(i).getCheckBasis() == null ? "" : claimReportLegals.get(i).getCheckBasis(),wcf3));
                            num = num + 1;
                        }
                    }
                    sheet.addCell(new Label(0, num, "交强险",wcf4));
                    sheet.mergeCells(1, num, 2, 0);//
                    if(claimLegal.getCpsMoney()!=null){
                        sheet.addCell(new Number(1, num,claimLegal.getCpsMoney(),wcf3));
                    }else{
                        sheet.addCell(new Label(1, num,"",wcf3));
                    }
                    sheet.addCell(new Label(3, num, "商业三者险",wcf4));
                    sheet.mergeCells(4, num, 5, 0);//
                    if(claimLegal.getCocMoney()!=null){
                        sheet.addCell(new Number(5, num,claimLegal.getCocMoney(),wcf3));
                    }else{
                        sheet.addCell(new Label(5, num,"",wcf3));
                    }
                    num = num + 1;
                    sheet.addCell(new Label(0, num, "备注",wcf4));
                    sheet.mergeCells(1, num, 5, 0);//
                    sheet.addCell(new Label(1, num, claimLegal.getCaseDesc()==null?"":claimLegal.getCaseDesc(),wcf3));
                }
                num = num + 1;
                sheet.mergeCells(0, num, 5, 0);//
                sheet.addCell(new Label(0, num, "",wcf3));

            }

            // 写入数据并关闭文件
            book.write();
            book.close();
            os.close();
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 单证处理移动，删除
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "caseFileMidOperate")
    public String caseFileMidOperate(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_FILE_MID_OPERATE,null,req,rsp);
    }

    /**
     * 确定受伤部位（使用场景：1、infonew页面案件基础信息--修改--选择受伤部位）
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("injuredPartSubmit")
    public String injuredPartSubmit(HttpServletRequest req, HttpServletResponse rsp,String buss){
        Map param = new HashMap();
        param.put("buss",buss);
        String retJson = "";
        String json = buss;
        req.getSession().setAttribute("json",json);
        return WebHelper.outputJson(json, rsp);
    }
}
