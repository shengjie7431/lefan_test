package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.AppApiMethodEnum;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lixianfeng on 2018/3/22.
 */
@Controller
@RequestMapping("/suning")
public class BackendSuningController extends BackendBaseController {
    /**
     * 财务代扣列表
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/withholdApply/list")
    public ModelAndView findWithholdApplyList(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SuningWithholdApplyDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.SUNING_SELECT_WHA_LIST, null, req);
        model.put("apiRsp",apiFinalResponse);
        String withholdState = req.getParameter("withholdState");
        String caseNo = req.getParameter("caseNo");
        String caseTitle = req.getParameter("caseTitle");
        String orderCode = req.getParameter("orderCode");
        String isTestcase = req.getParameter("isTestcase");
        String accountState = req.getParameter("accountState");
        model.put("withholdState",withholdState);
        model.put("caseNo",caseNo);
        model.put("caseTitle",caseTitle);
        model.put("type",req.getParameter("type"));
        model.put("numberType",req.getParameter("numberType"));
        model.put("orderCode",orderCode);
        model.put("accountState",accountState==""?null:accountState);
        model.put("pageSize",req.getParameter("pageSize"));
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

        return new ModelAndView("/suning/withholdApply/list",model);
    }

    /**
     * 确认发起代扣
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/withholdApply/okStart")
    public String okStart(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.SUNING_OK_START_WHA_INFO,null,req,rsp);
//        String okType = req.getParameter("okType");
//        if ("okSuning".equals(okType)){
//            return this.callApiAndOutput(BackendApiMethodEnum.SUNING_OK_START_WHA_INFO,null,req,rsp);
//        }else if("okCommission".equals(okType)){
//            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_COMMISSION_CALCULATION,null,req,rsp);
//        }
//        return null;
    }

    /**
     * 确认代扣
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/withholdApply/okApply")
    public String okApply(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.SUNING_OK_WHA_INFO,null,req,rsp);
    }

    /**
     * 开票申请列表
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/billApply/list")
    public ModelAndView findBillApplyList(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.SUNING_BILL_APPLY_LIST, null, req);

        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_ORG_LIST_BY_ORGPARENTID, null, req);
        List<OrgInfoDto> orgInfoDtos = (List<OrgInfoDto>) apiFinalResponse1.getResults();

        //开票类目 -- 枚举查询
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("enumCode", "billingEnum");
        TypeToken typeToken2 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
        ApiFinalResponse apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
        List<CommonEnumDto> bullingEnums = (List<CommonEnumDto>) apiFinalResponse2.getResults();
        model.put("bullingEnums",bullingEnums);
        //开票项目 -- 枚举查询
        appendMap = new HashMap<String, Object>();
        appendMap.put("enumCode", "billingItem");
        typeToken2 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
        List<CommonEnumDto> bullingItems = (List<CommonEnumDto>) apiFinalResponse2.getResults();
        model.put("bullingItems",bullingItems);
        //选择公司
        appendMap = new HashMap<String, Object>();
        appendMap.put("noPageIndex", 1);
        typeToken2 = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_BILLING_APPLY_CORPORATION_LIST, appendMap, req);
        List<BillingApplyCorporationDto> corporations = (List<BillingApplyCorporationDto>) apiFinalResponse2.getResults();
        model.put("corporations",corporations);

        String op = req.getParameter("op");
        if ("view".equals(op)){
            model.put("op","view");
        }
        model.put("apiRsp",apiFinalResponse);
        String billingState = req.getParameter("billingState");
        String caseNo = req.getParameter("caseNo");
        String caseTitle = req.getParameter("caseTitle");
        String billingItem = req.getParameter("billingItem");
        String billingEnum = req.getParameter("billingEnum");
        String billingType = req.getParameter("billingType");
        String orgId = req.getParameter("orgId");
        String businessType = req.getParameter("businessType");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String insuredName = req.getParameter("insuredName");
        String carNo = req.getParameter("carNo");
        String woundedName = req.getParameter("woundedName");
        model.put("billingState",billingState);
        model.put("caseNo",caseNo);
        model.put("caseTitle",caseTitle);
        model.put("billingItem",billingItem==null?"":billingItem);
        model.put("billingEnum",billingEnum==null?"":billingEnum);
        model.put("billingType",billingType==null?"":billingType);
        model.put("menuType",req.getParameter("menuType"));
        model.put("orgInfoDtos",orgInfoDtos);
        model.put("orgId",orgId==null?"":orgId);
        model.put("businessType",businessType==null?"":businessType);
        model.put("startDate",startDate==null?"":startDate);
        model.put("endDate",endDate==null?"":endDate);
        model.put("insuredName",insuredName==null?"":insuredName);
        model.put("carNo",carNo==null?"":carNo);
        model.put("woundedName",woundedName==null?"":woundedName);
        return new ModelAndView("/suning/billApply/list",model);
    }

    /**
     * 开票确认
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/billApply/upd")
    public String updBillApply(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.SUNING_BILL_APPLY_UPD,null,req,rsp);
    }

    /**
     * 到账详情
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/arrivalInfo/list")
    public ModelAndView findArrivalList(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<ArrivalInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.SUNING_ARRIVAL_INFO_LIST, null, req);
        String op = req.getParameter("op");
        if ("view".equals(op)){
            model.put("op","view");
        }
        model.put("apiRsp",apiFinalResponse);
        String caseNo = req.getParameter("caseNo");
        String caseTitle = req.getParameter("caseTitle");
        model.put("caseNo",caseNo);
        model.put("caseTitle",caseTitle);
        return new ModelAndView("/suning/arrivalInfo/list",model);
    }



    /**
     * 查看开票详情页面
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/billApply/findBillApplyView")
    public ModelAndView findBillApplyView(HttpServletRequest req, HttpServletResponse rsp){

        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();

        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<BillingApplyDto>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.SUNING_BILL_APPLY_VIEW_BY_CASE_ID, null, req);
        BillingApplyDto billingApplyInfo = (BillingApplyDto) apiFinalResponse1.getResults();
        model.put("billingApplyInfo", billingApplyInfo);

        String id = req.getParameter("id");
        model.put("id",id);
        model.put("menuType",req.getParameter("menuType"));

        //根据id查询开票申请表附表记录
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyImgsDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_IMGS_BY_ID, null, req);
        model.put("apiRsp",apiFinalResponse);

        //根据billId，查询开票材料的list
        TypeToken typeToken2 = new TypeToken<ApiFinalResponse<List<BillingApplyMaterialDto>>>() {};
        ApiFinalResponse apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_BILLING_APPLY_MATERIAL_BY_ID, null, req);

        model.put("apiRsp2", apiFinalResponse2);

        //开票类目 -- 枚举查询
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("enumCode", "billingEnum");
        typeToken2 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
        List<CommonEnumDto> bullingEnums = (List<CommonEnumDto>) apiFinalResponse2.getResults();
        model.put("bullingEnums", bullingEnums);
        //开票项目 -- 枚举查询
        appendMap = new HashMap<String, Object>();
        appendMap.put("enumCode", "billingItem");
        typeToken2 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
        List<CommonEnumDto> bullingItems = (List<CommonEnumDto>) apiFinalResponse2.getResults();
        model.put("bullingItems", bullingItems);

        //选择公司
        appendMap = new HashMap<String, Object>();
        appendMap.put("noPageIndex", 1);
        typeToken2 = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_BILLING_APPLY_CORPORATION_LIST, appendMap, req);
        List<BillingApplyCorporationDto> corporations = (List<BillingApplyCorporationDto>) apiFinalResponse2.getResults();
        model.put("corporations",corporations);

        return new ModelAndView("/suning/billApply/view",model);
    }

    /**
     * 查看开票详情页面
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/billApply/uploadBillApplyImg")
    public ModelAndView uploadBillApplyImg(HttpServletRequest req, HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();
        String id = req.getParameter("id");
        model.put("id",id);
        String caseNo = req.getParameter("caseNo");
        String fromType = req.getParameter("fromType");
        model.put("fromType",fromType);
        model.put("caseNo",caseNo);
        model.put("yhc",req.getParameter("yhc"));
        model.put("type",req.getParameter("type"));
        model.put("operatorType",req.getParameter("operatorType"));
        model.put("billMoney",req.getParameter("billMoney"));
        model.put("size",1);
        if (StringUtils.isNotBlank(req.getParameter("operatorType")) && ("upd".equals(req.getParameter("operatorType")) || "hc".equals(req.getParameter("operatorType")))){
            model.put("money",req.getParameter("money"));
            model.put("code",req.getParameter("code"));
            model.put("img",req.getParameter("img"));
            model.put("createTime",req.getParameter("createTime"));
            if ("hc".equals(req.getParameter("operatorType"))){
                model.put("createTime",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            }
            return new ModelAndView("/suning/billApply/updBillApplyImg",model);
        }
        if ("one".equals(fromType)){//单个开票
            return new ModelAndView("/suning/billApply/uploadBillApplyImg",model);
        }else if ("two".equals(fromType)){//多个开票

            if (StringUtils.isNotBlank(req.getParameter("billMoney")) && StringUtils.isNotBlank(req.getParameter("billMoney"))){
                Double billMoney = Double.parseDouble(req.getParameter("billMoney"));
                Double oneBillMoney = 0d;
                if (billMoney>100000){
                    oneBillMoney = 100000d;
                }else {
                    oneBillMoney = billMoney;
                }
                model.put("oneBillMoney",oneBillMoney);
            }
            return new ModelAndView("/suning/billApply/billSet",model);
        }else if ("twoOk".equals(fromType)) {//多个开票确认
            model.put("billMoney",req.getParameter("billMoney"));
            model.put("oneBillMoney",req.getParameter("oneBillMoney"));
            model.put("billItems", Arrays.asList(req.getParameter("billItems")));
            model.put("billImg",req.getParameter("billImg"));
            model.put("startBillCode",req.getParameter("startBillCode"));
            model.put("createTime",req.getParameter("createTime"));
            return new ModelAndView("/suning/billApply/uploadBillApplyImg",model);
        }
        return null;
    }

    /**
     * 查看确认到账详情页面
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/withholdApply/findWithholdApplyView")
    public ModelAndView findWithholdApplyView(HttpServletRequest req, HttpServletResponse rsp){

        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();


        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<SuningWithholdApplyDto>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.SUNING_WITH_HOLD_APPLY_VIEW, null, req);
        SuningWithholdApplyDto suningWithholdApplyDto = (SuningWithholdApplyDto) apiFinalResponse1.getResults();
        model.put("dto", suningWithholdApplyDto);

        //根据‘caseId’查询案件信息
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseCenterInfo>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_CENTER_INFO_BY_ID, null, req);
        CaseCenterInfo caseCenterInfo = (CaseCenterInfo) apiFinalResponse.getResults();
        model.put("caseCenterInfo", caseCenterInfo);

        String id = req.getParameter("id");
        model.put("id",id);
        String type = req.getParameter("type");
        model.put("type",type);
        return new ModelAndView("/suning/withholdApply/view",model);
    }


    /**
     * 财务-确认到账列表
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/withholdApply/findWithholdApplyListByWithHoldState")
    public ModelAndView findWithholdApplyListByWithHoldState(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SuningWithholdApplyDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.SUNING_SELECT_WHA_LIST_BY_WITHHOLD_STATE, null, req);
        model.put("apiRsp",apiFinalResponse);
        String withholdState = req.getParameter("withholdState");
        String caseNo = req.getParameter("caseNo");
        String caseTitle = req.getParameter("caseTitle");
        model.put("withholdState",withholdState);
        model.put("caseNo",caseNo);
        model.put("caseTitle",caseTitle);
        return new ModelAndView("/suning/withholdApply/withholdApplyList",model);
    }

    /**
     * 打开确认代扣页面  或者确定到账界面
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/withholdApply/pass")
    public ModelAndView pass(HttpServletRequest req,HttpServletResponse rsp){
        String passType = req.getParameter("passType");
        Map model = new HashMap();
        Long id = Long.valueOf(req.getParameter("id"));
        String caseNo = req.getParameter("caseNo");
        Integer applyType = Integer.valueOf(req.getParameter("applyType"));

        model.put("id",id);
        model.put("caseNo",caseNo);
        model.put("applyType",applyType);
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
     * 评估 - 修改开票金额
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/billApply/PGpass")
    public ModelAndView PGpass(HttpServletRequest req, HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();
        String id = req.getParameter("id");
        model.put("id",id);
        String menuType = req.getParameter("menuType");
        model.put("menuType",menuType);
        String mode = req.getParameter("model");
        model.put("model",mode);
        return new ModelAndView("/suning/billApply/pgPass",model);
    }
}
