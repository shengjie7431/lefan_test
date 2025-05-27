package com.lefancrm.backend.web;

import com.alibaba.fastjson.JSON;
import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.dto.staff.StaffBudgetCompanyDto;
import com.lefancrm.backend.dto.staff.StaffCompanyDto;
import com.lefancrm.backend.dto.staff.StaffPersonnelInfoDto;
import com.lefancrm.backend.util.FileZipUtil;
import com.lefancrm.backend.util.SerialNumberUtil;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import com.lefancrm.base.web.WebHelper;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.io.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by wangwei on 2018/07/24.
 */
@Controller
@RequestMapping(value = "/billingApply")
public class BackendBillingApplyController extends BackendBaseController {

    @Value("${survey.gonggu.path}")
    private String gongguExcelPath;

    /**
     * 查询开票列表
     *
     */
    @RequestMapping(value = "/billingApplyList")
    public ModelAndView billingApplyList(HttpServletRequest req, HttpServletResponse rsp) {
        String caseNo = req.getParameter("caseNo");
        String caseTitle = req.getParameter("caseTitle");
        String orgId = req.getParameter("orgId");
        String billingState = req.getParameter("billingState");
        String billingItem = req.getParameter("billingItem");
        String billingEnum = req.getParameter("billingEnum");
        String billingType = req.getParameter("billingType");
        String businessType = req.getParameter("businessType");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String insuredName = req.getParameter("insuredName");
        String carNo = req.getParameter("carNo");
        String woundedName = req.getParameter("woundedName");
        String menuType = req.getParameter("menuType");
        String confirmStartDate = req.getParameter("confirmStartDate");
        String confirmEndDate = req.getParameter("confirmEndDate");
        String billingCode = req.getParameter("billingCode");
        String billingCompany = req.getParameter("billingCompany");
        String business = req.getParameter("business");
        String recipientsName = req.getParameter("recipientsName");
        String confirmAccountState = req.getParameter("confirmAccountState");
        String meritName = req.getParameter("meritName");
        String imgsState = req.getParameter("imgsState");
        String orgIdGR = req.getParameter("orgIdGR");
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("fromType","ggywkp");//公估业务开票页面的查询
        //开票list
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_LIST, paramMap, req);

        //parentId为1的机构list
//        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
//        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_ORG_LIST_BY_ORGPARENTID, null, req);
//        List<OrgInfoDto> orgInfoDtos = (List<OrgInfoDto>) apiFinalResponse1.getResults();


        //公估业务机构
        Map<String, Object> appendMap = new HashMap<String, Object>();
//        appendMap.put("productTypeId", 1);
//        appendMap.put("btnCode",1000);
//        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
//        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_BILLING_SELECT_INFO_BY_RELATION_ID, appendMap, req);
//        List<OrgInfoDto> orgInfoDtosGG = (List<OrgInfoDto>) apiFinalResponse1.getResults();


        //收入归属机构
        appendMap = new HashMap<String, Object>();
        appendMap.put("noPageIndex", 1);
        appendMap.put("havePage","no");//不分页
        appendMap.put("surveyCode", "organ");
        TypeToken typeTokenOrgan = new TypeToken<ApiFinalResponse<List<StaffOrganDto>>>() {};
        ApiFinalResponse  apiFinalResponseOrgan=this.callApi(typeTokenOrgan,BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, null);
        List<StaffOrganDto> staffOrganDtoList = (List<StaffOrganDto>) apiFinalResponseOrgan.getResults();


        //个人业务机构
//        appendMap = new HashMap<String, Object>();
////        appendMap.put("productTypeId", 2);
////        appendMap.put("btnCode",1000);
////        typeToken1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
////        apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_BILLING_SELECT_INFO_BY_RELATION_ID, appendMap, req);
////        List<OrgInfoDto> orgInfoDtosGR = (List<OrgInfoDto>) apiFinalResponse1.getResults();

        //开票类目 -- 枚举查询
        appendMap = new HashMap<String, Object>();
        appendMap.put("enumCode", "billingEnum");
        TypeToken typeToken2 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
        ApiFinalResponse apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
        List<CommonEnumDto> bullingEnums = (List<CommonEnumDto>) apiFinalResponse2.getResults();

        //开票项目 -- 枚举查询
        appendMap = new HashMap<String, Object>();
        appendMap.put("enumCode", "billingItem");
        typeToken2 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
        List<CommonEnumDto> bullingItems = (List<CommonEnumDto>) apiFinalResponse2.getResults();

        //选择公司(权限过滤后的列表)
        appendMap = new HashMap<String, Object>();
        appendMap.put("noPageIndex", 1); //不分页
        typeToken2 = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_BILLING_APPLY_CORPORATION_LIST, appendMap, req);
        List<BillingApplyCorporationDto> corporations = (List<BillingApplyCorporationDto>) apiFinalResponse2.getResults();

        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
//        model.put("orgInfoDtos",orgInfoDtos);
//        model.put("orgInfoDtosGG",orgInfoDtosGG);
//        model.put("orgInfoDtosGR",orgInfoDtosGR);
        model.put("staffOrgans",staffOrganDtoList);
        model.put("caseNo",caseNo==null?"":caseNo);
        model.put("orgId",orgId==null?"":orgId);
        model.put("orgIdGR",orgIdGR==null?"":orgIdGR);
        model.put("caseTitle",caseTitle==null?"":caseTitle);
        model.put("billingState",billingState==null?"":billingState);
        model.put("billingItem",billingItem==null?"":billingItem);
        model.put("billingEnum",billingEnum==null?"":billingEnum);
        model.put("billingType",billingType==null?"":billingType);
        model.put("businessType",businessType==null?"":businessType);
        model.put("startDate",startDate==null?"":startDate);
        model.put("endDate",endDate==null?"":endDate);
        model.put("insuredName",insuredName==null?"":insuredName);
        model.put("carNo",carNo==null?"":carNo);
        model.put("woundedName",woundedName==null?"":woundedName);
        model.put("menuType",menuType==null?"":menuType);
        model.put("confirmStartDate",confirmStartDate==null?"":confirmStartDate);
        model.put("confirmEndDate",confirmEndDate==null?"":confirmEndDate);
        model.put("billingCode",billingCode==null?"":billingCode);
        model.put("billingCompany",billingCompany==null?"":billingCompany);
        model.put("recipientsName",recipientsName==null?"":recipientsName);
        model.put("confirmAccountState",confirmAccountState==null?"":confirmAccountState);
        model.put("pageSize",req.getParameter("pageSize"));
        if("3".equals(menuType)){
            model.put("business",business==null?"":business);
        }
        model.put("corporations",corporations);
//        model.put("enumItems",enumItems);
        model.put("bullingEnums",bullingEnums);
        model.put("bullingItems",bullingItems);
        model.put("meritName",meritName);
        model.put("imgsState",imgsState==null?"":imgsState);

        //下拉多选
        model.put("businessTypeIds",req.getParameter("businessTypeIds")==null?"":req.getParameter("businessTypeIds"));
//        model.put("orgIdGRs",req.getParameter("orgIdGRs")==null?"":req.getParameter("orgIdGRs"));
//        model.put("orgIdGGs",req.getParameter("orgIdGGs")==null?"":req.getParameter("orgIdGGs"));
        model.put("staffOrgIds",req.getParameter("staffOrgIds")==null?"":req.getParameter("staffOrgIds"));
        model.put("billingEnums",req.getParameter("billingEnums")==null?"":req.getParameter("billingEnums"));
        model.put("billingItems",req.getParameter("billingItems")==null?"":req.getParameter("billingItems"));
        model.put("billingStates",req.getParameter("billingStates")==null?"":req.getParameter("billingStates"));
        model.put("imgsStates",req.getParameter("imgsStates")==null?"":req.getParameter("imgsStates"));
        model.put("billingTypes",req.getParameter("billingTypes")==null?"":req.getParameter("billingTypes"));
        model.put("confirmAccountStates",req.getParameter("confirmAccountStates")==null?"":req.getParameter("confirmAccountStates"));

        return new ModelAndView("/billingApply/billingApplyList",model);
    }
    /**
     * 查询开票详情页面
     *
     */
    @RequestMapping(value = "/billingApplyView")
    public ModelAndView billingApplyView(HttpServletRequest req , HttpServletResponse rsp) {
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<BillingApplyDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_BY_ID, null, req);
        BillingApplyDto billingApplyInfo = (BillingApplyDto) apiFinalResponse.getResults();
        model.put("billingApplyInfo", billingApplyInfo);

        //根据billId，查询开票材料的list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<BillingApplyMaterialDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_BILLING_APPLY_MATERIAL_BY_ID, null, req);

        model.put("apiRsp", apiFinalResponse1);

        //根据id查询开票申请表附表记录 --发票编号及图片表
        TypeToken typeToken2 = new TypeToken<ApiFinalResponse<List<BillingApplyImgsDto>>>() {};
        ApiFinalResponse apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_BILLING_APPLY_IMGS_BY_ID, null, req);
        model.put("apiRsp2",apiFinalResponse2);

        typeToken2 = new TypeToken<ApiFinalResponse<List<BillingApplyImgsDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_BILLING_APPLY_IMGS_BY_ID_NEW, null, req);
        model.put("apiRsp666",apiFinalResponse2);

        //根据id查询 公估确认到账记录
        TypeToken typeToken6 = new TypeToken<ApiFinalResponse<List<BillingApplyAccountsDto>>>() {};
        ApiFinalResponse apiFinalResponse6= this.callApi(typeToken6, BackendApiMethodEnum.BACKEND_BILLING_APPLY_ACCOUNTS_BY_ID, null, req);
        model.put("apiRsp6",apiFinalResponse6);

        String mType = req.getParameter("mType");
        model.put("mType",mType);
        model.put("menuType",req.getParameter("menuType"));

        //查询当前登录人的角色:是不是新角色
        TypeToken typeToken4 = new TypeToken<ApiFinalResponse<BusUserRoleDto>>() {};
        ApiFinalResponse apiFinalResponse4= this.callApi(typeToken4, BackendApiMethodEnum.BACKEND_BILLING_APPLY_MATERIAL_ROLE, null, req);
        BusUserRoleDto busUserRoleDto = (BusUserRoleDto) apiFinalResponse4.getResults();

        //开票类目 -- 枚举查询
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("enumCode", "billingEnum");
        TypeToken typeToken5 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
        ApiFinalResponse apiFinalResponse5= this.callApi(typeToken5, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
        List<CommonEnumDto> bullingEnums = (List<CommonEnumDto>) apiFinalResponse5.getResults();
        model.put("bullingEnums",bullingEnums);
        //开票项目 -- 枚举查询
        appendMap = new HashMap<String, Object>();
        appendMap.put("enumCode", "billingItem");
        typeToken5 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
        apiFinalResponse5= this.callApi(typeToken5, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
        List<CommonEnumDto> bullingItems = (List<CommonEnumDto>) apiFinalResponse5.getResults();
        model.put("bullingItems",bullingItems);

        //选择公司(没有过滤权限)
        appendMap = new HashMap<String, Object>();
        appendMap.put("noPageIndex", 1);
        typeToken2 = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_BILLING_APPLY_CORPORATION_LIST, appendMap, req);
        List<BillingApplyCorporationDto> corporations = (List<BillingApplyCorporationDto>) apiFinalResponse2.getResults();
        model.put("corporations",corporations);

        //1、未申请的；2、不是合并生成；3、角色是新角色
        if(billingApplyInfo.getBillingState() == 1 && billingApplyInfo.getIsMerge() == null && busUserRoleDto != null){
            //查询开票是否需要合并开票
            TypeToken typeToken3 = new TypeToken<ApiFinalResponse<List<BillingApplyDto>>>() {};
            ApiFinalResponse apiFinalResponse3= this.callApi(typeToken3, BackendApiMethodEnum.BACKEND_BILLING_APPLY_MERGE_LIST, null, req);
            List<BillingApplyDto> billingApplys = (List<BillingApplyDto>) apiFinalResponse3.getResults();

            model.put("billingApplys",billingApplys);
            //如果可合并开票的数据，达到两条及以上，进入合并页面
            if(billingApplys.size() > 1){
                return new ModelAndView("/billingApply/billingApplyMerge",model);
            }
        }

        return new ModelAndView("/billingApply/billingApplyView",model);
    }
    /**
     * 添加新开票--页面
     *
     */
    @RequestMapping(value = "/billingApplyAdd")
    public String billingApplyAdd(HttpServletRequest req){
        //地区信息
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("parentId",0);
        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
        Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
        }.getType();
        ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
        req.setAttribute("apiRsp",apiRsp);



        //获取开票对象
        TypeToken typeToken2 = new TypeToken<ApiFinalResponse<List<BillingApplyCompanyDto>>>() {};
        ApiFinalResponse apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_BILLING_APPLY_COMPANY_LIST, null, req);
        List<BillingApplyCompanyDto> companys = (List<BillingApplyCompanyDto>) apiFinalResponse2.getResults();

        //选择公司
        appendMap = new HashMap<String, Object>();
        appendMap.put("noPageIndex", 1);
        appendMap.put("auth", 1);//权限过滤
        typeToken2 = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_BILLING_APPLY_CORPORATION_LIST, appendMap, req);
        List<BillingApplyCorporationDto> corporations = (List<BillingApplyCorporationDto>) apiFinalResponse2.getResults();

        //开票公司(人事系统  预算归属公司)
        appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode","budgetCompany");
        appendMap.put("havePage","no");
        appendMap.put("state",0);
        typeToken2 = new TypeToken<ApiFinalResponse<List<StaffBudgetCompanyDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
        List<StaffBudgetCompanyDto> billCompanys = (List<StaffBudgetCompanyDto>)apiFinalResponse2.getResults();


        //查询收入归属机构
        appendMap = new HashMap<String, Object>();
        //appendMap.put("noPageIndex", 1);
        appendMap.put("havePage","no");//不分页
        appendMap.put("surveyCode", "organ");
        typeToken2 = new TypeToken<ApiFinalResponse<List<StaffOrganDto>>>() {};
        apiFinalResponse2=this.callApi(typeToken2,BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
        List<StaffOrganDto> staffOrganDtoList=(List<StaffOrganDto>) apiFinalResponse2.getResults();

        //选择产品类型
        appendMap = new HashMap<String, Object>();
        appendMap.put("noPageIndex", 1);
        appendMap.put("surveyCode", "product");
        typeToken2 = new TypeToken<ApiFinalResponse<List<BillingApplyProductTypeDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_BILLING_ROLE_LIST, appendMap, req);
        List<BillingApplyProductTypeDto> products = (List<BillingApplyProductTypeDto>) apiFinalResponse2.getResults();

        req.setAttribute("apiRsp", apiRsp);
        req.setAttribute("corporations", corporations);
        req.setAttribute("billCompanys", billCompanys);
        req.setAttribute("staffOrganDtoList", staffOrganDtoList);
        req.setAttribute("companys", companys);
        req.setAttribute("products", products);
        //获取当前登录人
        UserInfo adminSession = this.getSessionAdmin(req);
        req.setAttribute("currentUserName", adminSession.getUserName());

        //未匹配收款 -- 认领
        String billingMoney = req.getParameter("money");
        req.setAttribute("billingMoney", billingMoney);
        String claim = req.getParameter("claim");
        req.setAttribute("claim", claim);
        String unmatchId = req.getParameter("unmatchId");
        req.setAttribute("unmatchId", unmatchId);

        //案件来源：1、发票清单的添加、关联开票(值为2)；2、未匹配收款的认领(值为6)
        String billingSource = req.getParameter("billingSource");
        req.setAttribute("billingSource", billingSource);

        appendMap.put("surveyCode","personnelInfo");
        appendMap.put("havePage","no");//不分页
        typeToken2 = new TypeToken<ApiFinalResponse<List<StaffPersonnelInfoDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
        List<StaffPersonnelInfoDto> staffPersonnelInfoDtoList = (List<StaffPersonnelInfoDto>)apiFinalResponse2.getResults();
        req.setAttribute("staffPersonnelInfoDtoList",JSON.toJSONString(staffPersonnelInfoDtoList));


        //获取默认的收入归属机构、收件人信息等
        TypeToken typeToken = new TypeToken<ApiFinalResponse<BillingApplyRecipientDto>>(){};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_RECIPIENT_INFO, null, req);
        BillingApplyRecipientDto recipient = (BillingApplyRecipientDto)apiFinalResponse.getResults();
        BillingApplyDto billingApplyInfo = new BillingApplyDto();
        if (recipient != null){
            billingApplyInfo.setStaffOrgId(recipient.getStaffOrgId());
            billingApplyInfo.setStaffOrgName(recipient.getStaffOrgName());
            billingApplyInfo.setRecipientsName(recipient.getRecipientsName());
            billingApplyInfo.setRecipientsPhone(recipient.getRecipientsPhone());
            billingApplyInfo.setProvinceId(recipient.getProvinceId());
            billingApplyInfo.setProvince(recipient.getProvince());
            billingApplyInfo.setCityId(recipient.getCityId());
            billingApplyInfo.setCity(recipient.getCity());
            billingApplyInfo.setDistrictId(recipient.getDistrictId());
            billingApplyInfo.setDistrict(recipient.getDistrict());
            billingApplyInfo.setAddress(recipient.getAddress());

            //市信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",billingApplyInfo.getProvinceId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> cityApiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("cityApiRsp", cityApiRsp);

            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",billingApplyInfo.getCityId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> districtApiRsp = JsonUtil.jsonToObject(json, type);
            req.setAttribute("districtApiRsp", districtApiRsp);
        }
        req.setAttribute("billingApplyInfo",billingApplyInfo);

        return "/billingApply/billingApplyAdd";
    }

    /**
     * 根据选择的类目 生成案件编号
     *
     */
    @RequestMapping(value = "/toBuildCaseNo")
    public String toBuildCaseNo(HttpServletRequest req, HttpServletResponse rsp) {
        String billingEnum=req.getParameter("billingEnum");
        Map param = new HashMap();
        param.put("billingEnum",billingEnum);
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_TO_BUILD_CASE_NO, param, req);
        req.getSession().setAttribute("json",json);
        return WebHelper.outputJson(json, rsp);
    }

    /**
     * 保存开票信息
     *
     */
    @RequestMapping(value = "/billingApplySave")
    public String billingApplySave(HttpServletRequest req,HttpServletResponse rsp){
        String btnCode = req.getParameter("btnCode");
        if ("save-refund".equals(btnCode)){//保存退费信息
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_APPLY_REFUND_OPERATE, null, req, rsp);
        }else{
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_APPLY_SAVE, null, req, rsp);
        }
    }

    /**
     * 提交开票材料-页面
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/uploadBillingApplyMaterial")
    public ModelAndView uploadBillingApplyMaterial(HttpServletRequest req, HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();
        String id = req.getParameter("id");
        String caseNo = req.getParameter("caseNo");
        model.put("id",id);
        model.put("caseNo",caseNo);
        return new ModelAndView("/billingApply/uploadBillingApplyMaterial",model);
    }

    /**
     * 退回
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/billApplyStateUpBack")
    public ModelAndView billApplyStateUpBack(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        String id = req.getParameter("id");
        model.put("id",id);
        model.put("menuType",req.getParameter("menuType"));
        model.put("model",req.getParameter("model"));
        return new ModelAndView("/billingApply/billingApplyViewBack",model);
    }

    /**
     * 提交申请
     *
     */
    @RequestMapping(value = "/billApplyStateUp")
    public String billApplyStateUp(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_APPLY_STATE_UP, null, req, rsp);
    }

    /**
     * 保存开票材料
     *
     */
    @RequestMapping(value = "/saveMaterial")
    public String saveMaterial(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_APPLY_SAVE_MATERIAL, null, req, rsp);
    }

    /**
     * 删除开票信息
     *
     */
    @RequestMapping(value = "/billingApplyDelete")
    public String billingApplyDelete(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_APPLY_DELETE, null, req, rsp);
    }

    /**
     * 修改
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/billApplyEdit")
    public ModelAndView billApplyEdit(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        String copy = req.getParameter("copy");
        model.put("copy",copy);
        BillingApplyDto billingApplyInfo = null;
        if ("2".equals(copy)){//调查案件 开票
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyRiskCaseInfoDto>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_INFO, null, req);
            SurveyRiskCaseInfoDto dto = (SurveyRiskCaseInfoDto)apiFinalResponse.getResults();
            billingApplyInfo = new BillingApplyDto();
            billingApplyInfo.setCaseNo(dto.getSurveyCno());
            billingApplyInfo.setCaseTitle(dto.getEntrustOrgName().concat(dto.getCreateUserName()).concat("案件"));
//            Double billMoney = dto.getEntrustOkPrice1() + dto.getEntrustOkPrice2();
//            billingApplyInfo.setBillingMoney(billMoney);
            billingApplyInfo.setBillingMoney(dto.getBillingMoney());
            billingApplyInfo.setBillingEnum(12);
            billingApplyInfo.setBillingItem(1);
            billingApplyInfo.setBillingType(2);
            billingApplyInfo.setBillingSource(3);
            billingApplyInfo.setProductType(1);
            if (dto.getSurveyConsignor() != null) {
                SurveyConsignorDto surveyConsignor = dto.getSurveyConsignor();
                model.put("entrustOrgId",surveyConsignor.getId());
                //获取开票对象
                if (surveyConsignor.getCompanys() != null) {
                    if (surveyConsignor.getCompanys().size() > 0) {
                        BillingApplyCompanyDto billingApplyCompanyDto = surveyConsignor.getCompanys().get(0);
                        billingApplyInfo.setCompanyId(billingApplyCompanyDto.getId());
                        billingApplyInfo.setCompanyName(billingApplyCompanyDto.getCompanyName());
//                        billingApplyInfo.setTaxRate();
                    }
                }
                if (surveyConsignor.getBillCompanyId() != null){
                    billingApplyInfo.setBusinessType(surveyConsignor.getBillCompanyId().intValue());//开票公司-从委托方机构取值
                }
//                billingApplyInfo.setBusinessType(surveyConsignor.getBillCompanyId().intValue());//开票公司-从委托方机构取值
            }
            //获取默认的收入归属机构、收件人信息等
            typeToken = new TypeToken<ApiFinalResponse<BillingApplyRecipientDto>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_RECIPIENT_INFO, null, req);
            BillingApplyRecipientDto recipient = (BillingApplyRecipientDto)apiFinalResponse.getResults();
            if (recipient != null){
//                model.put("staffOrgId",recipient.getStaffOrgId());
//                model.put("staffOrgName",recipient.getStaffOrgName());
                billingApplyInfo.setStaffOrgId(recipient.getStaffOrgId());
                billingApplyInfo.setStaffOrgName(recipient.getStaffOrgName());
                billingApplyInfo.setRecipientsName(recipient.getRecipientsName());
                billingApplyInfo.setRecipientsPhone(recipient.getRecipientsPhone());
                billingApplyInfo.setProvinceId(recipient.getProvinceId());
                billingApplyInfo.setProvince(recipient.getProvince());
                billingApplyInfo.setCityId(recipient.getCityId());
                billingApplyInfo.setCity(recipient.getCity());
                billingApplyInfo.setDistrictId(recipient.getDistrictId());
                billingApplyInfo.setDistrict(recipient.getDistrict());
                billingApplyInfo.setAddress(recipient.getAddress());
            }
            model.put("billingApplyInfo", billingApplyInfo);
        }else if ("3".equals(copy)){//调查案件 批量开票
//            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyRiskCaseInfoDto>>(){};
//            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_BILLING_LIST, null, req);
//            SurveyRiskCaseInfoDto dto = (SurveyRiskCaseInfoDto)apiFinalResponse.getResults();
            billingApplyInfo = new BillingApplyDto();
            billingApplyInfo.setCaseNo(req.getParameter("surveyNo"));
            if (StringUtils.isEmpty(billingApplyInfo.getCaseNo())){
                String no = SerialNumberUtil.getSurveyCode("CWTPL");
                billingApplyInfo.setCaseNo(no);
            }
            billingApplyInfo.setCaseTitle(req.getParameter("companyName").concat("批量开票").concat("案件"));
            billingApplyInfo.setBillingMoney(Double.valueOf(req.getParameter("billingMoney")));
            billingApplyInfo.setBillingEnum(12);
            billingApplyInfo.setBillingItem(1);
            billingApplyInfo.setBillingType(2);
            billingApplyInfo.setBillingSource(3);
            billingApplyInfo.setProductType(1);

            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("entrustOrgId",req.getParameter("entrustOrgId"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyConsignorDto>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CONSIGNOR_info, paramMap, req);
            if (apiFinalResponse.getResults() != null){
                SurveyConsignorDto surveyConsignor = (SurveyConsignorDto)apiFinalResponse.getResults();
                model.put("entrustOrgId",surveyConsignor.getId());
                //获取开票对象
                if (surveyConsignor.getCompanys() != null) {
                    if (surveyConsignor.getCompanys().size() > 0) {
                        BillingApplyCompanyDto billingApplyCompanyDto = surveyConsignor.getCompanys().get(0);
                        billingApplyInfo.setCompanyId(billingApplyCompanyDto.getId());
                        billingApplyInfo.setCompanyName(billingApplyCompanyDto.getCompanyName());
                    }
                }
                if (surveyConsignor.getBillCompanyId() != null){
                    billingApplyInfo.setBusinessType(surveyConsignor.getBillCompanyId().intValue());//开票公司-从委托方机构取值
                }
            }
            //获取默认的收入归属机构、收件人信息等
            typeToken = new TypeToken<ApiFinalResponse<BillingApplyRecipientDto>>(){};
            apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_RECIPIENT_INFO, null, req);
            BillingApplyRecipientDto recipient = (BillingApplyRecipientDto)apiFinalResponse.getResults();
            if (recipient != null){
//                model.put("staffOrgId",recipient.getStaffOrgId());
//                model.put("staffOrgName",recipient.getStaffOrgName());
                billingApplyInfo.setStaffOrgId(recipient.getStaffOrgId());
                billingApplyInfo.setStaffOrgName(recipient.getStaffOrgName());
                billingApplyInfo.setRecipientsName(recipient.getRecipientsName());
                billingApplyInfo.setRecipientsPhone(recipient.getRecipientsPhone());
                billingApplyInfo.setProvinceId(recipient.getProvinceId());
                billingApplyInfo.setProvince(recipient.getProvince());
                billingApplyInfo.setCityId(recipient.getCityId());
                billingApplyInfo.setCity(recipient.getCity());
                billingApplyInfo.setDistrictId(recipient.getDistrictId());
                billingApplyInfo.setDistrict(recipient.getDistrict());
                billingApplyInfo.setAddress(recipient.getAddress());
            }
            model.put("billingApplyInfo", billingApplyInfo);
            //批量选中的id集合
            String idList = req.getParameter("idList");
            model.put("idList", idList);
        }else if("4".equals(copy)){//垫付业务开票
            billingApplyInfo = new BillingApplyDto();
            billingApplyInfo.setCaseNo(req.getParameter("surveyNo"));
            billingApplyInfo.setCaseTitle(req.getParameter("companyName").concat("垫付业务开票").concat("("+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+")"));
            billingApplyInfo.setBillingMoney(Double.valueOf(req.getParameter("billingMoney")));
            billingApplyInfo.setBillingEnum(12);
            billingApplyInfo.setBillingItem(1);
            billingApplyInfo.setBillingType(2);
            billingApplyInfo.setBillingSource(6);
            billingApplyInfo.setProductType(1);
            model.put("billingApplyInfo", billingApplyInfo);
            //批量选中的id集合
            String idList = req.getParameter("idList");
            model.put("idList", idList);
        }else if("8".equals(copy)){
            Map paramMap = new HashMap();
            paramMap.put("fwId",req.getParameter("fwId"));
            TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyFwCaseDTO>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INFO_FW_CASE, paramMap, req);
            SurveyFwCaseDTO surveyFwCase = (SurveyFwCaseDTO) apiFinalResponse.getResults();
            billingApplyInfo = new BillingApplyDto();
            billingApplyInfo.setCompanyName(surveyFwCase.getKpmc());
            String fwgs = surveyFwCase.getFwgs();
            if ("公估".equals(fwgs)){
                billingApplyInfo.setBusinessType(2);
            }else if ("健康".equals(fwgs)){
                billingApplyInfo.setBusinessType(5);
            }else if ("金融".equals(fwgs)){
                billingApplyInfo.setBusinessType(1);
            }else if ("乐欲".equals(fwgs)){
                billingApplyInfo.setBusinessType(52);
            }
            billingApplyInfo.setStaffOrgId(89L);
            billingApplyInfo.setCaseNo(SerialNumberUtil.getSurveyCode("ZXFW"));
            billingApplyInfo.setCaseTitle(surveyFwCase.getKhmc() + "("+surveyFwCase.getXm()+")案件");
            billingApplyInfo.setBillingMoney(surveyFwCase.getKpje());
            billingApplyInfo.setTaxRate(6D);
            billingApplyInfo.setBillingType(2);
            model.put("billingApplyInfo", billingApplyInfo);
            model.put("fwId",surveyFwCase.getId());

            //地区信息
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",0);
            String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
            model.put("apiRsp",apiRsp);

            //市信息
            billingApplyInfo.setProvinceId(1);
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",billingApplyInfo.getProvinceId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> cityApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("cityApiRsp", cityApiRsp);

            billingApplyInfo.setCityId(2);
            appendMap = new HashMap<String, Object>();
            appendMap.put("parentId",billingApplyInfo.getCityId());
            json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
            type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
            }.getType();
            ApiFinalResponse<List<CommonArea>> districtApiRsp = JsonUtil.jsonToObject(json, type);
            model.put("districtApiRsp", districtApiRsp);

            appendMap = new HashMap<String, Object>();
            appendMap.put("noPageIndex", 1);
            appendMap.put("havePage","no");//不分页
            appendMap.put("surveyCode", "organ");
            TypeToken typeTokenOrgan = new TypeToken<ApiFinalResponse<List<StaffOrganDto>>>() {};
            ApiFinalResponse  apiFinalResponseOrgan=this.callApi(typeTokenOrgan,BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, null);
            List<StaffOrganDto> staffOrganDtoList = (List<StaffOrganDto>) apiFinalResponseOrgan.getResults();
            model.put("staffOrganDtoList",staffOrganDtoList);

            //开票公司(人事系统  预算归属公司)
            appendMap = new HashMap<String, Object>();
            appendMap.put("surveyCode","budgetCompany");
            appendMap.put("havePage","no");
            appendMap.put("state",0);
            typeToken = new TypeToken<ApiFinalResponse<List<StaffBudgetCompanyDto>>>() {};
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
            List<StaffBudgetCompanyDto> billCompanys = (List<StaffBudgetCompanyDto>)apiFinalResponse.getResults();
            model.put("billCompanys", billCompanys);
            return new ModelAndView("/fw/billingApplyEdit",model);
        }else{
            //开票信息
            TypeToken typeToken = new TypeToken<ApiFinalResponse<BillingApplyDto>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_BY_ID, null, req);
            billingApplyInfo = (BillingApplyDto) apiFinalResponse.getResults();
            model.put("billingApplyInfo", billingApplyInfo);
        }

        //地区信息
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("parentId",0);
        String json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
        Type type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
        }.getType();
        ApiFinalResponse<List<CommonArea>> apiRsp = JsonUtil.jsonToObject(json, type);
        model.put("apiRsp",apiRsp);

        //市信息
        appendMap = new HashMap<String, Object>();
        appendMap.put("parentId",billingApplyInfo.getProvinceId());
        json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
        type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
        }.getType();
        ApiFinalResponse<List<CommonArea>> cityApiRsp = JsonUtil.jsonToObject(json, type);
        model.put("cityApiRsp", cityApiRsp);

        appendMap = new HashMap<String, Object>();
        appendMap.put("parentId",billingApplyInfo.getCityId());
        json = this.callApi(BackendApiMethodEnum.BACKEND_ORG_TO_ADD, appendMap, req);
        type = new TypeToken<ApiFinalResponse<List<CommonArea>>>() {
        }.getType();
        ApiFinalResponse<List<CommonArea>> districtApiRsp = JsonUtil.jsonToObject(json, type);
        model.put("districtApiRsp", districtApiRsp);

        //机构lis
        appendMap = new HashMap<String, Object>();
        appendMap.put("productTypeId",billingApplyInfo.getProductType());
        appendMap.put("btnCode",1000);
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_BILLING_SELECT_INFO_BY_RELATION_ID, appendMap, req);
        List<OrgInfoDto> orgInfoDtos = (List<OrgInfoDto>) apiFinalResponse1.getResults();

        //查询收入归属机构
        appendMap = new HashMap<String, Object>();
        appendMap.put("noPageIndex", 1);
        appendMap.put("havePage","no");//不分页
        appendMap.put("surveyCode", "organ");
        TypeToken typeTokenOrgan = new TypeToken<ApiFinalResponse<List<StaffOrganDto>>>() {};
        ApiFinalResponse  apiFinalResponseOrgan=this.callApi(typeTokenOrgan,BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, null);
        List<StaffOrganDto> staffOrganDtoList=(List<StaffOrganDto>) apiFinalResponseOrgan.getResults();

        //获取开票对象
        TypeToken typeToken2 = new TypeToken<ApiFinalResponse<List<BillingApplyCompanyDto>>>() {};
        ApiFinalResponse apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_BILLING_APPLY_COMPANY_LIST, null, req);
        List<BillingApplyCompanyDto> companys = (List<BillingApplyCompanyDto>) apiFinalResponse2.getResults();

        //根据billId，查询开票材料的list
        TypeToken typeToken3 = new TypeToken<ApiFinalResponse<List<BillingApplyMaterialDto>>>() {};
        ApiFinalResponse apiFinalResponse3= this.callApi(typeToken3, BackendApiMethodEnum.BACKEND_BILLING_APPLY_MATERIAL_BY_ID, null, req);

        model.put("apiRsp3", apiFinalResponse3);

        //开票类目 -- 枚举查询
        appendMap = new HashMap<String, Object>();
        appendMap.put("noPageIndex", 1);
        appendMap.put("corporationId", billingApplyInfo.getBusinessType());
        TypeToken typeToken4 = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationEnumDto>>>() {};
        ApiFinalResponse apiFinalResponse4= this.callApi(typeToken4, BackendApiMethodEnum.BACKEND_BILLING_APPLY_CORPORATION_ENUM_LIST, appendMap, req);
        List<BillingApplyCorporationEnumDto> bullingEnums = (List<BillingApplyCorporationEnumDto>) apiFinalResponse4.getResults();
        req.setAttribute("bullingEnums",bullingEnums);
        if (bullingEnums != null && bullingEnums.size() == 1){
            Long billingEnunId = bullingEnums.get(0).getBillingEnumId();
            if (billingEnunId != null){
                billingApplyInfo.setBillingEnum(billingEnunId.intValue());
            }
        }

        //开票项目 -- 枚举查询
        appendMap = new HashMap<String, Object>();
        appendMap.put("noPageIndex", 1);
        appendMap.put("billingEnumId", billingApplyInfo.getBillingEnum());
        appendMap.put("corporationId", billingApplyInfo.getBusinessType());
        typeToken2 = new TypeToken<ApiFinalResponse<List<BillingApplyEnumItemDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_BILLING_APPLY_ENUM_ITEM_LIST, appendMap, req);
        List<BillingApplyEnumItemDto> bullingItems = (List<BillingApplyEnumItemDto>) apiFinalResponse2.getResults();
        req.setAttribute("bullingItems",bullingItems);
        if (bullingItems != null && bullingItems.size() == 1){
            Long billingItemId = bullingItems.get(0).getBillingItemId();
            if (billingItemId != null){
                billingApplyInfo.setBillingItem(billingItemId.intValue());
            }
        }

        //选择公司
        appendMap = new HashMap<String, Object>();
        appendMap.put("noPageIndex", 1);
        appendMap.put("auth", 1);//权限过滤
        typeToken2 = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_BILLING_APPLY_CORPORATION_LIST, appendMap, req);
        List<BillingApplyCorporationDto> corporations = (List<BillingApplyCorporationDto>) apiFinalResponse2.getResults();
        req.setAttribute("corporations",corporations);

        //开票公司(人事系统  预算归属公司)
        appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode","budgetCompany");
        appendMap.put("havePage","no");
        appendMap.put("state",0);
        typeToken2 = new TypeToken<ApiFinalResponse<List<StaffBudgetCompanyDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
        List<StaffBudgetCompanyDto> billCompanys = (List<StaffBudgetCompanyDto>)apiFinalResponse2.getResults();
        model.put("billCompanys", billCompanys);
        Integer businessType = billingApplyInfo.getBusinessType();
        if (businessType != null){
            List<StaffBudgetCompanyDto> collect = billCompanys.stream().filter(p -> p.getId() == businessType.longValue()).collect(Collectors.toList());
            if (collect.size() > 0) {
                billingApplyInfo.setTaxRate(collect.get(0).getTaxRate());
            }
        }

        //选择产品类型
        appendMap = new HashMap<String, Object>();
        appendMap.put("noPageIndex", 1);
        appendMap.put("surveyCode", "product");
        typeToken2 = new TypeToken<ApiFinalResponse<List<BillingApplyProductTypeDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_BILLING_ROLE_LIST, appendMap, req);
        List<BillingApplyProductTypeDto> products = (List<BillingApplyProductTypeDto>) apiFinalResponse2.getResults();

        model.put("apiRsp", apiRsp);
        model.put("orgInfoDtos", orgInfoDtos);
        model.put("staffOrganDtoList", staffOrganDtoList);
        model.put("companys", companys);
        model.put("products", products);

        if("1".equals(copy)){
            appendMap.clear();
            appendMap.put("surveyCode","personnelInfo");
            appendMap.put("havePage","no");//不分页
            typeToken2 = new TypeToken<ApiFinalResponse<List<StaffPersonnelInfoDto>>>() {};
            apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, null);
            List<StaffPersonnelInfoDto> staffPersonnelInfoDtoList = (List<StaffPersonnelInfoDto>)apiFinalResponse2.getResults();
            model.put("staffPersonnelInfoDtoList",JSON.toJSONString(staffPersonnelInfoDtoList));
            return new ModelAndView("/billingApply/billingApplyDuplicate",model);
        }else if ("2".equals(copy) || "3".equals(copy) || "4".equals(copy)){

            appendMap = new HashMap<String, Object>();
            appendMap.put("consignorId", req.getParameter("companyId"));
            appendMap.put("surveyCode", "consignorDepartment");
            appendMap.put("menuType", 1);//不分页
            typeToken4 = new TypeToken<ApiFinalResponse<List<SurveyConsignorDepartmentDto>>>() {};
            apiFinalResponse4= this.callApi(typeToken4, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
            List<SurveyConsignorDepartmentDto> departmentDto = (List<SurveyConsignorDepartmentDto>) apiFinalResponse4.getResults();
            model.put("departmentDto", departmentDto);
            model.put("companyName", req.getParameter("companyName"));
            model.put("companyId", req.getParameter("companyId"));

            //开票主体信息
//            appendMap = new HashMap<String, Object>();
//            appendMap.put("consignorId", req.getParameter("companyId"));
//            appendMap.put("surveyCode", "consignorBillSubject");
//            appendMap.put("menuType", 1);//不分页
//            typeToken4 = new TypeToken<ApiFinalResponse<List<SurveyConsignorBillSubjectDto>>>() {};
//            apiFinalResponse4= this.callApi(typeToken4, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
//            List<SurveyConsignorBillSubjectDto> billSubjectDtoList = (List<SurveyConsignorBillSubjectDto>) apiFinalResponse4.getResults();
//            model.put("billSubjectDtoList", billSubjectDtoList);

            appendMap.clear();
            appendMap.put("surveyCode","personnelInfo");
            appendMap.put("havePage","no");//不分页
            typeToken2 = new TypeToken<ApiFinalResponse<List<StaffPersonnelInfoDto>>>() {};
            apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, null);
            List<StaffPersonnelInfoDto> staffPersonnelInfoDtoList = (List<StaffPersonnelInfoDto>)apiFinalResponse2.getResults();
            model.put("staffPersonnelInfoDtoList",JSON.toJSONString(staffPersonnelInfoDtoList));
            return new ModelAndView("/billingApply/billingApplySurvey",model);
        }
        appendMap.put("surveyCode","personnelInfo");
        appendMap.put("havePage","no");//不分页
        typeToken2 = new TypeToken<ApiFinalResponse<List<StaffPersonnelInfoDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_STAFF_LIST, appendMap, req);
        List<StaffPersonnelInfoDto> staffPersonnelInfoDtoList = (List<StaffPersonnelInfoDto>)apiFinalResponse2.getResults();
        model.put("staffPersonnelInfoDtoList", JSON.toJSONString(staffPersonnelInfoDtoList));
        return new ModelAndView("/billingApply/billingApplyEdit",model);
    }

    /**
     * 删除单条发票或材料记录
     */
    @RequestMapping(value = "/billApplyImgsDelete")
    public String billApplyImgsDelete(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_APPLY_IMGS_DELETE, null, req, rsp);
    }

    /**
     * 公估 确认到账页面
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/confirmAccount")
    public ModelAndView confirmAccount(HttpServletRequest req, HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();
        String id = req.getParameter("id");
        model.put("id",id);
        String imgsId = req.getParameter("imgsId");
        model.put("imgsId",imgsId);
        String caseNo = req.getParameter("caseNo");
        model.put("caseNo",caseNo);
        String noAccountMoney = req.getParameter("noAccountMoney");
        model.put("noAccountMoney",noAccountMoney);
        model.put("nowDate",LocalDate.now().toString());
        return new ModelAndView("/billingApply/confirmAccount",model);
    }

    /**
     * 公估 确认到账
     */
    @RequestMapping(value = "/confirmAccountSubmit")
    public String confirmAccountSubmit(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CONFIRM_ACCOUNT_SUBMIT, null, req, rsp);
    }

    /**
     * 发票作废或红冲
     */
    @RequestMapping(value = "/billApplyImgsUpd")
    public String billApplyImgsUpd(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_APPLY_IMGS_UPD, null, req, rsp);
    }

    /**
     * 开票申请驳回原因
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/billApplyRejectReason")
    public ModelAndView billApplyRejectReason(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        String id = req.getParameter("id");
        model.put("id",id);
        return new ModelAndView("/billingApply/billingApplyRejectReason",model);
    }


    /**
     * 查询开票列表
     *
     */
    @RequestMapping(value = "/billingApplyListExport")
    public void billingApplyListExport(HttpServletRequest req, HttpServletResponse rsp) {
        String caseNo = req.getParameter("caseNo");
        String caseTitle = req.getParameter("caseTitle");
        String orgId = req.getParameter("orgId");
        String billingState = req.getParameter("billingState");
        String billingItem = req.getParameter("billingItem");
        String billingEnum = req.getParameter("billingEnum");
        String billingType = req.getParameter("billingType");
        String businessType = req.getParameter("businessType");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String insuredName = req.getParameter("insuredName");
        String carNo = req.getParameter("carNo");
        String woundedName = req.getParameter("woundedName");
        String menuType = req.getParameter("menuType");
        String confirmStartDate = req.getParameter("confirmStartDate");
        String confirmEndDate = req.getParameter("confirmEndDate");
        String billingCode = req.getParameter("billingCode");
        String billingCompany = req.getParameter("billingCompany");
        String recipientsName = req.getParameter("recipientsName");
        String meritName = req.getParameter("meritName");
        String imgsState = req.getParameter("imgsState");
        String orgIdGR = req.getParameter("orgIdGR");
        String exportType = req.getParameter("exportType");// company 按照开票公司导出

        Map map = new HashMap();
        map.put("caseNo",caseNo==null?"":caseNo);
        map.put("orgId",orgId==null?"":orgId);
        map.put("caseTitle",caseTitle==null?"":caseTitle);
        map.put("billingState",orgId==null?"":billingState);
        map.put("billingItem",billingItem==null?"":billingItem);
        map.put("billingEnum",billingEnum==null?"":billingEnum);
        map.put("billingType",billingType==null?"":billingType);
        map.put("businessType",businessType==null?"":businessType);
        map.put("startDate",startDate==null?"":startDate);
        map.put("endDate",endDate==null?"":endDate);
        map.put("insuredName",insuredName==null?"":insuredName);
        map.put("carNo",carNo==null?"":carNo);
        map.put("woundedName",woundedName==null?"":woundedName);
        map.put("menuType",menuType==null?"":menuType);
        map.put("confirmStartDate",confirmStartDate==null?"":confirmStartDate);
        map.put("confirmEndDate",confirmEndDate==null?"":confirmEndDate);
        map.put("billingCode",billingCode==null?"":billingCode);
        map.put("billingCompany",billingCompany==null?"":billingCompany);
        map.put("recipientsName",recipientsName==null?"":recipientsName);
        map.put("meritName",meritName==null?"":meritName);
        map.put("imgsState",imgsState==null?"":imgsState);
        map.put("orgIdGR",orgIdGR==null?"":orgIdGR);

        //下拉多选
        map.put("businessTypeIds",req.getParameter("businessTypeIds")==null?"":req.getParameter("businessTypeIds"));
        map.put("orgIdGRs",req.getParameter("orgIdGRs")==null?"":req.getParameter("orgIdGRs"));
        map.put("orgIdGGs",req.getParameter("orgIdGGs")==null?"":req.getParameter("orgIdGGs"));
        map.put("billingEnums",req.getParameter("billingEnums")==null?"":req.getParameter("billingEnums"));
        map.put("billingItems",req.getParameter("billingItems")==null?"":req.getParameter("billingItems"));
        map.put("billingStates",req.getParameter("billingStates")==null?"":req.getParameter("billingStates"));
        map.put("imgsStates",req.getParameter("imgsStates")==null?"":req.getParameter("imgsStates"));
        map.put("billingTypes",req.getParameter("billingTypes")==null?"":req.getParameter("billingTypes"));
        map.put("confirmAccountStates",req.getParameter("confirmAccountStates")==null?"":req.getParameter("confirmAccountStates"));
        map.put("pageSize", 1000);
        map.put("fromType","ggywkp");
        //选择公司(权限过滤后的列表)
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("noPageIndex", 1); //不分页
        appendMap.put("btnCode",1000);
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_BILLING_APPLY_CORPORATION_LIST, appendMap, req);
        List<BillingApplyCorporationDto> corporations = (List<BillingApplyCorporationDto>) apiFinalResponse1.getResults();

        TypeToken<ApiFinalResponse<List<BillingApplyDto>>> typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyDto>>>() {};
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(exportType)){//按照开票公司导出
            String businessTypeIds = req.getParameter("businessTypeIds");
            Long timestamp= System.currentTimeMillis();//当前时间戳
            String excelPath = gongguExcelPath + timestamp;
            File file = new File(excelPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (StringUtils.isBlank(businessTypeIds)){
                for (BillingApplyCorporationDto corporation : corporations) {
                    map.put("businessTypeIds",corporation.getId());
                    map.put("pageIndex", 0);
                    ApiFinalResponse apiFinalResponse2 = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_LIST_FOR_EXPORT, map, req);
                    List<BillingApplyDto> billingApplyDtoList = ecursiveResult(apiFinalResponse2, typeToken, map, req, new ArrayList<>(apiFinalResponse2.getCount()), 0);
                    export(billingApplyDtoList,rsp,df,excelPath,corporation.getName());
                }
            }else {
                String[] typeIds = req.getParameter("businessTypeIds").split(",");
                List<BillingApplyCorporationDto> collect = corporations.stream().filter(e -> new ArrayList<>(Arrays.asList(typeIds)).contains(e.getId().toString())).collect(Collectors.toList());
                for (BillingApplyCorporationDto corporation : collect) {
                    map.put("businessTypeIds",corporation.getId());
                    map.put("pageIndex", 0);
                    ApiFinalResponse apiFinalResponse2 = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_LIST_FOR_EXPORT, map, req);
                    List<BillingApplyDto> billingApplyDtoList = ecursiveResult(apiFinalResponse2, typeToken, map, req, new ArrayList<>(apiFinalResponse2.getCount()), 0);
                    export(billingApplyDtoList,rsp,df,excelPath,corporation.getName());
                }
            }
            FileZipUtil.createZip(excelPath,excelPath + ".zip");
            String url = excelPath+ ".zip";
            File downLoadFile = new File(url);
            try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(downLoadFile));
                BufferedOutputStream out = new BufferedOutputStream(rsp.getOutputStream())){
                byte[] buff = new byte[2048];
                int length = 0;
                while ((length = in.read(buff)) > 0) {
                    out.write(buff, 0, length);
                    out.flush();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            ApiFinalResponse apiFinalResponse2 = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_LIST_FOR_EXPORT, map, req);
            List<BillingApplyDto> billingApplyDtoList = ecursiveResult(apiFinalResponse2, typeToken, map, req, new ArrayList<>(apiFinalResponse2.getCount()), 0);
            export(billingApplyDtoList,rsp,df,null,"总表");
        }
    }


    //递归 分段处理数据
    public List<BillingApplyDto> ecursiveResult(ApiFinalResponse apiFinalResponse, TypeToken<ApiFinalResponse<List<BillingApplyDto>>> typeToken, Map map, HttpServletRequest req, List<BillingApplyDto> temporaryList, int pageNum) {
        List<BillingApplyDto> results = new ArrayList<>((List<BillingApplyDto>) apiFinalResponse.getResults());
        temporaryList.addAll(results);
        pageNum = pageNum + 1;
        while (pageNum * 1000 < apiFinalResponse.getCount()) {
            map.put("pageIndex", pageNum * 1000);
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_LIST_FOR_EXPORT, map, req);
            return ecursiveResult(apiFinalResponse, typeToken, map, req, temporaryList, pageNum);
        }
        return temporaryList;
    }

    public void export(List<BillingApplyDto> billingApplyDtoList,HttpServletResponse rsp ,SimpleDateFormat df ,String excelPath,String fileName){
        // 创建excel
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFSheet sheet_0 = wb.createSheet(fileName);//建立sheet对象
        sheet_0.setDefaultColumnWidth(15);
        sheet_0.setDefaultRowHeightInPoints(15);
        //设置头
        HSSFRow cell_row_0 = sheet_0.createRow(0);
        int index = -1;
        if ("总表".equals(fileName)){
            cell_row_0.createCell(++index).setCellValue("开票公司");
        }

        cell_row_0.createCell(++index).setCellValue("收入归属机构");
        cell_row_0.createCell(++index).setCellValue("业务员");
        cell_row_0.createCell(++index).setCellValue("对方公司");
        cell_row_0.createCell(++index).setCellValue("发票号");
        cell_row_0.createCell(++index).setCellValue("开票金额");
        cell_row_0.createCell(++index).setCellValue("开票日期");
        cell_row_0.createCell(++index).setCellValue("发票类型");
        cell_row_0.createCell(++index).setCellValue("报案号");
        cell_row_0.createCell(++index).setCellValue("案件性质");
        cell_row_0.createCell(++index).setCellValue("实际到账金额");
        cell_row_0.createCell(++index).setCellValue("回款日期");
        cell_row_0.createCell(++index).setCellValue("确认日期");
        int size = 1;
        for (int i = 0; i < billingApplyDtoList.size(); i++) {
            BillingApplyDto billingApplyDto = billingApplyDtoList.get(i);
            HSSFRow cell_row_i = sheet_0.createRow(size);
            cell_row_i.setHeightInPoints(18);//行高设置成18px
            index = -1;
            if ("总表".equals(fileName)){
                Integer businessType = billingApplyDto.getBusinessType();
                if (businessType != null){
                    if (businessType == 5){
                        cell_row_i.createCell(++index).setCellValue("乐凡健康");
                    }else if (businessType == 4){
                        cell_row_i.createCell(++index).setCellValue("江苏奕赔");
                    }else if (businessType == 3){
                        cell_row_i.createCell(++index).setCellValue("正言金融");
                    }else if (businessType == 2){
                        cell_row_i.createCell(++index).setCellValue("乐凡公估");
                    }else if (businessType == 1){
                        cell_row_i.createCell(++index).setCellValue("乐凡金融");
                    }else if (businessType == 6){
                        cell_row_i.createCell(++index).setCellValue("乐凡公估（盐城分公司）");
                    }else if (businessType == 7){
                        cell_row_i.createCell(++index).setCellValue("乐凡公估（常熟分公司）");
                    }else{
                        cell_row_i.createCell(++index).setCellValue("");
                    }
                }
            }

//            cell_row_i.createCell(++index).setCellValue(billingApplyDto.getOrgName());
            cell_row_i.createCell(++index).setCellValue(billingApplyDto.getStaffOrgName());
            cell_row_i.createCell(++index).setCellValue(billingApplyDto.getCreateBy());
            cell_row_i.createCell(++index).setCellValue(billingApplyDto.getCompanyName());
            cell_row_i.createCell(++index).setCellValue(Optional.ofNullable(billingApplyDto.getBillingCode()).orElse(""));
            cell_row_i.createCell(++index).setCellValue(Optional.ofNullable(billingApplyDto.getBillingMoney()).orElse(0d));
            cell_row_i.createCell(++index).setCellValue(df.format(billingApplyDto.getCreateTime()));
            cell_row_i.createCell(++index).setCellValue(billingApplyDto.getBillingType() == 1 ? "专票" : "普票");
            cell_row_i.createCell(++index).setCellValue("【伤者名称】" + Optional.ofNullable(billingApplyDto.getWoundedName()).orElse(" ") + "  【被保人姓名】" + Optional.ofNullable(billingApplyDto.getInsuredName()).orElse(" ") + "  【车牌号】" + Optional.ofNullable(billingApplyDto.getCarNo()).orElse(" ") + "  【备注】" + Optional.ofNullable(billingApplyDto.getRemark()).orElse(" "));
            cell_row_i.createCell(++index).setCellValue(billingApplyDto.getBillingEnumName());
            int state = billingApplyDto.getBillingState();
            if (state == 3){//红冲的都是没有到账的
                cell_row_i.createCell(++index).setCellValue(0);
                cell_row_i.createCell(++index).setCellValue("已红冲");
                cell_row_i.createCell(++index).setCellValue("无");
            }
            List<BillingApplyAccountsDto> billingApplyAccountsList = billingApplyDto.getBillingApplyAccountsList() == null ? Collections.emptyList() : billingApplyDto.getBillingApplyAccountsList();
            for (int j = 0; j < billingApplyAccountsList.size(); j++) {
                BillingApplyAccountsDto billingApplyAccountsDto = billingApplyAccountsList.get(j);
                HSSFRow row2;
                if (j == 0){
                    row2 = cell_row_i;
                }else {
                    row2  = sheet_0.createRow(size);
                }
                int index2 = "总表".equals(fileName)? 9 : 8;
                row2.createCell(++index2).setCellValue(billingApplyAccountsDto.getMoney());
                row2.createCell(++index2).setCellValue(billingApplyAccountsDto.getAccountTime()==null?"":df.format(billingApplyAccountsDto.getAccountTime()));
                row2.createCell(++index2).setCellValue(billingApplyAccountsDto.getCreateTime()==null?"":df.format(billingApplyAccountsDto.getCreateTime()));
                for (int k = 0; k < 9; k++) {
                    CellRangeAddress region = new CellRangeAddress(cell_row_i.getRowNum(), size, k, k);
                    sheet_0.addMergedRegion(region);
                }
                size += 1;
            }
            if (billingApplyAccountsList.size()==0)size += 1;
        }

        if (null == excelPath){
            try (OutputStream output = rsp.getOutputStream()){
                //设置响应头
                rsp.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName.concat("-").concat(LocalDate.now().toString()), "UTF-8") + ".xls");
                rsp.setContentType("application/msexcel");
                wb.write(output);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            try (OutputStream output = new FileOutputStream(excelPath + File.separator + fileName+".xls")) {
                wb.write(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void export33(String json,HttpServletResponse rsp,Map map ,HttpServletRequest req){
        Type type = new TypeToken<ApiFinalResponse<List<BillingApplyDto>>>() {
        }.getType();
        ApiFinalResponse<List<BillingApplyDto>> apiRsp = JsonUtil.jsonToObject(json, type);
        if (apiRsp == null) {
            return;
        }

        // 创建excel
        List<BillingApplyDto> queryList = apiRsp.getResults();
        WritableWorkbook book = null; // 创建jxl工作簿
        String filename ="开票数据.xls";
        try {
            OutputStream os = rsp.getOutputStream();
            rsp.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO8859-1"));
            rsp.setContentType("application/msexcel");
            // 打开文件
            book = Workbook.createWorkbook(os);
            // 生成名为"机构活动量统计"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("开票数据", 0);

            // 设置表头
            sheet.addCell(new Label(0, 0, "案件编号"));
            sheet.addCell(new Label(1,0,"案件标题"));
            sheet.addCell(new Label(2,0,"产品"));
            sheet.addCell(new Label(3,0,"开票项目"));
            sheet.addCell(new Label(4,0,"开票类型"));
            sheet.addCell(new Label(5,0,"业务类别"));
            sheet.addCell(new Label(6,0,"开票状态"));
            sheet.addCell(new Label(7,0,"开票金额"));
            sheet.addCell(new Label(8,0,"开票时间"));
            sheet.addCell(new Label(9,0,"开票单号"));
            sheet.addCell(new Label(10,0,"票据凭证"));
            sheet.addCell(new Label(11,0,"机构名字"));
            sheet.addCell(new Label(12,0,"收件人姓名"));
            sheet.addCell(new Label(13,0,"收件人手机号"));
            sheet.addCell(new Label(14,0,"省"));
            sheet.addCell(new Label(15,0,"市"));
            sheet.addCell(new Label(16,0,"区/县"));
            sheet.addCell(new Label(17,0,"收件人详细地址"));
            sheet.addCell(new Label(18,0,"被保险人姓名"));
            sheet.addCell(new Label(19,0,"车牌号码"));
            sheet.addCell(new Label(20,0,"伤者姓名"));
            sheet.addCell(new Label(21,0,"保险公司"));
            sheet.addCell(new Label(22,0,"保单号"));
            sheet.addCell(new Label(23,0,"报案号"));
            sheet.addCell(new Label(24,0,"退回原因"));
            sheet.addCell(new Label(25,0,"驳回申请原因"));
            sheet.addCell(new Label(26,0,"绩效所属人员"));
            sheet.addCell(new Label(27,0,"确认到账金额"));
            sheet.addCell(new Label(28,0,"确认到账时间"));
            sheet.addCell(new Label(29,0,"确认到账状态"));
            sheet.addCell(new Label(30,0,"服务费金额"));
            sheet.addCell(new Label(31,0,"通道费"));
            sheet.addCell(new Label(32,0,"保险费"));
            sheet.addCell(new Label(33,0,"扣费金额"));
            sheet.addCell(new Label(34,0,"备注"));
            sheet.addCell(new Label(35,0,"创建人"));
            sheet.addCell(new Label(36,0,"创建时间"));
            sheet.addCell(new Label(37,0,"开票人"));
            sheet.addCell(new Label(38,0,"更新人"));
            sheet.addCell(new Label(39,0,"更新时间"));
            sheet.addCell(new Label(40,0,"开票对象"));
            sheet.addCell(new Label(41,0,"是否是合并开票"));
            sheet.addCell(new Label(42,0,"绩效所属"));

            if(queryList!=null && !queryList.isEmpty()){
                for(int i=0; i<queryList.size(); i++){
                    sheet.addCell(new Label(0, i+1, queryList.get(i).getCaseNo()));
                    sheet.addCell(new Label(1, i+1, queryList.get(i).getCaseTitle()));
                    sheet.addCell(new Label(2, i+1, queryList.get(i).getBillingEnumName()));
                    sheet.addCell(new Label(3, i+1, queryList.get(i).getBillingItemName()));

                    //开票类型
                    String billingTypeName = null;
                    if(queryList.get(i).getBillingType() !=null) {
                        billingTypeName = findbillingTypeName(queryList.get(i).getBillingType(), billingTypeName);
                    }
                    sheet.addCell(new Label(4, i+1, billingTypeName));

                    //业务类别
                    String businessTypeName = null;
                    if(queryList.get(i).getBusinessType() !=null) {
                        businessTypeName = findbusinessTypeName(queryList.get(i).getBusinessType(), businessTypeName);
                    }
                    sheet.addCell(new Label(5, i+1, businessTypeName));

                    //开票状态
                    String billingStateName = null;
                    if(queryList.get(i).getBillingState() !=null) {
                        billingStateName = findbillingStateName(queryList.get(i).getBillingState(), billingStateName);
                    }
                    sheet.addCell(new Label(6, i+1, billingStateName));
                    sheet.addCell(new Number(7, i+1, queryList.get(i).getBillingMoney()==null?0D:queryList.get(i).getBillingMoney()));

                    if(queryList.get(i).getBillingTime() !=null){
                        sheet.addCell(new DateTime(8, i+1, queryList.get(i).getBillingTime()));
                    }else{
                        sheet.addCell(new Label(8, i+1, null));
                    }

                    sheet.addCell(new Label(9, i+1, queryList.get(i).getBillingCode()));
                    sheet.addCell(new Label(10, i+1, queryList.get(i).getImg()));
                    sheet.addCell(new Label(11, i+1, queryList.get(i).getOrgName()));
                    sheet.addCell(new Label(12, i+1, queryList.get(i).getRecipientsName()));
                    sheet.addCell(new Label(13, i+1, queryList.get(i).getRecipientsPhone()));
                    sheet.addCell(new Label(14, i+1, queryList.get(i).getProvince()));
                    sheet.addCell(new Label(15, i+1, queryList.get(i).getCity()));
                    sheet.addCell(new Label(16, i+1, queryList.get(i).getDistrict()));
                    sheet.addCell(new Label(17, i+1, queryList.get(i).getAddress()));
                    sheet.addCell(new Label(18, i+1, queryList.get(i).getInsuredName()));
                    sheet.addCell(new Label(19, i+1, queryList.get(i).getCarNo()));
                    sheet.addCell(new Label(20, i+1, queryList.get(i).getWoundedName()));
                    sheet.addCell(new Label(21, i+1, queryList.get(i).getInsurerCompanyName()));
                    sheet.addCell(new Label(22, i+1, queryList.get(i).getPolicyNo()));
                    sheet.addCell(new Label(23, i+1, queryList.get(i).getReportNo()));
                    sheet.addCell(new Label(24, i+1, queryList.get(i).getOperReason()));
                    sheet.addCell(new Label(25, i+1, queryList.get(i).getRejectReason()));
                    sheet.addCell(new Label(26, i+1, queryList.get(i).getMeritName()));
                    sheet.addCell(new Number(27, i+1, queryList.get(i).getConfirmAccountMoney()==null?0D:queryList.get(i).getConfirmAccountMoney()));

                    if(queryList.get(i).getConfirmAccountTime() !=null){
                        sheet.addCell(new DateTime(28, i+1, queryList.get(i).getConfirmAccountTime()));
                    }else{
                        sheet.addCell(new Label(28, i+1, null));
                    }
                    //业务类别
                    String confirmAccountStateName = null;
                    if(queryList.get(i).getConfirmAccountState() !=null) {
                        confirmAccountStateName = findconfirmAccountStateName(queryList.get(i).getConfirmAccountState(), confirmAccountStateName);
                    }
                    sheet.addCell(new Label(29, i+1, confirmAccountStateName));
                    sheet.addCell(new Number(30, i+1, queryList.get(i).getServcieMoney()==null?0D:queryList.get(i).getServcieMoney()));
                    sheet.addCell(new Number(31, i+1, queryList.get(i).getChannelMoney()==null?0D:queryList.get(i).getChannelMoney()));
                    sheet.addCell(new Number(32, i+1, queryList.get(i).getInsuranceMoney()==null?0D:queryList.get(i).getInsuranceMoney()));
                    sheet.addCell(new Number(33, i+1, queryList.get(i).getDeductionMoney()==null?0D:queryList.get(i).getDeductionMoney()));
                    sheet.addCell(new Label(34, i+1, queryList.get(i).getRemark()));
                    sheet.addCell(new Label(35, i+1, queryList.get(i).getCreateBy()));

                    if(queryList.get(i).getCreateTime() !=null){
                        sheet.addCell(new DateTime(36, i+1, queryList.get(i).getCreateTime()));
                    }else{
                        sheet.addCell(new Label(36, i+1, null));
                    }

                    sheet.addCell(new Label(37, i+1, queryList.get(i).getBillingBy()));
                    sheet.addCell(new Label(38, i+1, queryList.get(i).getUpdateBy()));

                    if(queryList.get(i).getUpdateTime() !=null){
                        sheet.addCell(new DateTime(39, i+1, queryList.get(i).getUpdateTime()));
                    }else{
                        sheet.addCell(new Label(39, i+1, null));
                    }

                    sheet.addCell(new Label(40, i+1, queryList.get(i).getCompanyName()));

                    if(queryList.get(i).getIsMerge() == null ){
                        sheet.addCell(new Label(41, i+1, "否"));
                    }else if(queryList.get(i).getIsMerge() != null ){
                        if(queryList.get(i).getIsMerge() == 1){
                            sheet.addCell(new Label(41, i+1, "是"));
                        }
                    }
                    sheet.addCell(new Label(42, i+1, queryList.get(i).getMeritName()));
                }
            }

            //获取发票单号信息
            TypeToken typeTokenImgs = new TypeToken<ApiFinalResponse<List<BillingApplyImgsMaterialDto>>>() {};
            ApiFinalResponse responseImgs = this.callApi(typeTokenImgs, BackendApiMethodEnum.BACKEND_BILLING_APPLY_IMGS_FOR_EXPORT, map, req);
            List<BillingApplyImgsMaterialDto> imgsDtoList = (List<BillingApplyImgsMaterialDto>) responseImgs.getResults();

            //sheet2的内容
            WritableSheet sheetTwo = book.createSheet("发票信息汇总", 1);
            sheetTwo.setColumnView(0,25);// 将第一列的宽度设为30
            sheetTwo.setColumnView(1,30);// 将第一列的宽度设为30
            sheetTwo.setColumnView(2,20);// 将第一列的宽度设为30
            sheetTwo.setColumnView(3,100);// 将第一列的宽度设为30
            // 设置表头
            sheetTwo.addCell(new Label(0,0,"案件编号"));
            sheetTwo.addCell(new Label(1,0,"案件标题"));
            sheetTwo.addCell(new Label(2,0,"开票单号"));
            sheetTwo.addCell(new Label(3,0,"票据凭证"));
            sheetTwo.addCell(new Label(4,0,"上传人"));
            sheetTwo.addCell(new Label(5,0,"上传时间"));
            sheetTwo.addCell(new Label(6,0,"发票状态"));

            if(imgsDtoList!=null && !imgsDtoList.isEmpty()){
                for(int i=0; i< imgsDtoList.size(); i++){
                    sheetTwo.addCell(new Label(0,i+1,  imgsDtoList.get(i).getCaseNo()==null?"":imgsDtoList.get(i).getCaseNo()));
                    sheetTwo.addCell(new Label(1,i+1,  imgsDtoList.get(i).getCaseTitle()==null?"":imgsDtoList.get(i).getCaseTitle()));
                    sheetTwo.addCell(new Label(2,i+1,  imgsDtoList.get(i).getBillingCode()==null?"":imgsDtoList.get(i).getBillingCode()));
                    sheetTwo.addCell(new Label(3,i+1,  imgsDtoList.get(i).getBillingImgs()==null?"":imgsDtoList.get(i).getBillingImgs()));
                    sheetTwo.addCell(new Label(4,i+1,  imgsDtoList.get(i).getImgsCreateBy()==null?"":imgsDtoList.get(i).getImgsCreateBy()));
                    if(imgsDtoList.get(i).getImgsCreateTime() !=null){
                        sheetTwo.addCell(new DateTime(5, i+1, imgsDtoList.get(i).getImgsCreateTime()));
                    }else{
                        sheetTwo.addCell(new Label(5, i+1, null));
                    }
                    if(imgsDtoList.get(i).getImgsState() == null || imgsDtoList.get(i).getImgsState() == 1){
                        sheetTwo.addCell(new Label(6,i+1,  "正常"));
                    }else if(imgsDtoList.get(i).getImgsState() == 2){
                        sheetTwo.addCell(new Label(6,i+1,  "作废"));
                    }else if(imgsDtoList.get(i).getImgsState() == 3){
                        sheetTwo.addCell(new Label(6,i+1,  "红冲"));
                    }

                }
            }

            //获取材料信息
            TypeToken typeTokenMaterial = new TypeToken<ApiFinalResponse<List<BillingApplyImgsMaterialDto>>>() {};
            ApiFinalResponse responseMaterial = this.callApi(typeTokenMaterial, BackendApiMethodEnum.BACKEND_BILLING_APPLY_MATERIAL_FOR_EXPORT, map, req);
            List<BillingApplyImgsMaterialDto> materialDtoList = (List<BillingApplyImgsMaterialDto>) responseMaterial.getResults();

            //sheet3的内容
            WritableSheet sheetThird = book.createSheet("材料信息汇总", 2);

            sheetThird.setColumnView(0,25);// 将第一列的宽度设为30
            sheetThird.setColumnView(1,30);// 将第一列的宽度设为30
            sheetThird.setColumnView(2,20);// 将第一列的宽度设为30
            // 设置表头
            sheetThird.addCell(new Label(0,0,"案件编号"));
            sheetThird.addCell(new Label(1,0,"案件标题"));
            sheetThird.addCell(new Label(2,0,"材料凭证"));
            sheetThird.addCell(new Label(3,0,"上传人"));
            sheetThird.addCell(new Label(4,0,"上传时间"));

            if(materialDtoList!=null && !materialDtoList.isEmpty()){
                for(int i=0; i< materialDtoList.size(); i++){
                    sheetThird.addCell(new Label(0,i+1,  materialDtoList.get(i).getCaseNo()==null?"":materialDtoList.get(i).getCaseNo()));
                    sheetThird.addCell(new Label(1,i+1,  materialDtoList.get(i).getCaseTitle()==null?"":materialDtoList.get(i).getCaseTitle()));
                    sheetThird.addCell(new Label(2,i+1,  materialDtoList.get(i).getMaterialImgs()==null?"":materialDtoList.get(i).getMaterialImgs()));
                    sheetThird.addCell(new Label(3,i+1,  materialDtoList.get(i).getMaterialCreateBy()==null?"":materialDtoList.get(i).getMaterialCreateBy()));
                    if(materialDtoList.get(i).getMaterialCreateTime() !=null){
                        sheetThird.addCell(new DateTime(4, i+1, materialDtoList.get(i).getMaterialCreateTime()));
                    }else{
                        sheetThird.addCell(new Label(4, i+1, null));
                    }

                }
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

    //开票类型(1、专票；2、普票)
    private String findbillingTypeName(Integer billingType, String billingTypeName) {
        if(billingType == 1 ){
            billingTypeName ="专票";
        }else if(billingType == 2 ){
            billingTypeName ="普票";
        }
        return billingTypeName;
    }

    //业务类别: 1、金融 ，2、公估
    private String findbusinessTypeName(Integer businessType, String businessTypeName) {
        if(businessType == 1 ){
            businessTypeName ="金融";
        }else if(businessType == 2 ){
            businessTypeName ="公估";
        }
        return businessTypeName;
    }

    //开票状态(1、未申请；2、申请中；3、已开票; 4、已退票 ; 5退票审核中 ;6重开审核中,7退票审核通过, 8重开审核通过,9退票中)
    private String findbillingStateName(Integer billingState, String billingStateName) {
        if(billingState == 1 ){
            billingStateName ="未申请";
        }else if(billingState == 2 ){
            billingStateName ="申请中";
        }else if(billingState == 3 ){
            billingStateName ="已开票";
        }else if(billingState == 4 ){
            billingStateName ="已退票";
        }else if(billingState == 5 ){
            billingStateName ="退票审核中";
        }else if(billingState == 6 ){
            billingStateName ="重开审核中";
        }else if(billingState == 7 ){
            billingStateName ="退票审核通过";
        }else if(billingState == 8 ){
            billingStateName ="重开审核通过";
        }else if(billingState == 9 ){
            billingStateName ="退票中";
        }
        return billingStateName;
    }

    //确认到账状态：1、未到账 2、已到账
    private String findconfirmAccountStateName(Integer confirmAccountState, String confirmAccountStateName) {
        if(confirmAccountState == 1 ){
            confirmAccountStateName ="未到账";
        }else if(confirmAccountState == 2 ){
            confirmAccountStateName ="已到账";
        }
        return confirmAccountStateName;
    }


    /**
     * 合并开票
     */
    @RequestMapping(value = "/billingApplyMerge")
    public String billingApplyMerge(HttpServletRequest req,HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_APPLY_MERGE, null, req, rsp);
    }

    /**
     * 选择收件人地址
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/selectRecipient")
    public ModelAndView selectRecipient(HttpServletRequest req, HttpServletResponse rsp){
        String recipientsName = req.getParameter("recipientsName");
        String recipientsPhone = req.getParameter("recipientsPhone");

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyRecipientDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_RECIPIENT_LIST, null, req);

        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("recipientsName",recipientsName==null?"":recipientsName);
        model.put("recipientsPhone",recipientsPhone==null?"":recipientsPhone);
        //billingApply、law页面均有调用
        model.put("type",req.getParameter("type"));

        return new ModelAndView("/billingApply/recipient",model);
    }

    /**
     * 确定选择收件人
     *
     */
    @RequestMapping(value = "/selectRecipientInfo")
    public String selectRecipientInfo(HttpServletRequest req, HttpServletResponse rsp) {

        Map param = new HashMap();
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_BILLING_APPLY_RECIPIENT_LIST, param, req);
        req.getSession().setAttribute("json",json);
        return WebHelper.outputJson(json, rsp);
    }

    /**
     * 选择开票对象
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/selectCompanyName")
    public ModelAndView selectCompanyName(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        //狄大人-
        String type = req.getParameter("type");
        if(type!=null && "survey".equals(type)){
            String name = req.getParameter("name");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDepartmentDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_COMPANY_LIST, null, req);
            model.put("apiRsp",apiFinalResponse);
            model.put("name",name==null?"":name);
        }else{
            String companyName = req.getParameter("companyName");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyCompanyDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_COMPANY_LIST, null, req);
            model.put("apiRsp",apiFinalResponse);
            model.put("companyName",companyName==null?"":companyName);
        }
        //billingApply、law页面均有调用
        model.put("type",type);
        return new ModelAndView("/billingApply/company",model);
    }

    /**
     * 确定开票对象
     *
     */
    @RequestMapping(value = "/selectCompanyNameInfo")
    public String selectCompanyNameInfo(HttpServletRequest req, HttpServletResponse rsp) {
        Map param = new HashMap();
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_BILLING_APPLY_COMPANY_LIST, param, req);
        req.getSession().setAttribute("json",json);
        return WebHelper.outputJson(json, rsp);
    }

    /**
     * 财务中心工作台
     *
     */
    @RequestMapping(value = "/financeManager")
    public ModelAndView financeManager(HttpServletRequest req, HttpServletResponse rsp){
        TypeToken typeToken = new TypeToken<ApiFinalResponse<FinanceNumberDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINANCE_INFO_NUMBER, null, req);
        Map model = new HashMap();
        model.put("numberDto",apiFinalResponse.getResults());

        //获取角色
        typeToken = new TypeToken<ApiFinalResponse<CaseUserRoleDto>>(){};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_USER_ROLE, null, req);
        CaseUserRoleDto caseUserRoleDto = (CaseUserRoleDto)apiFinalResponse.getResults();
        model.put("caseUserRole",caseUserRoleDto);

        return new ModelAndView("/billingApply/financeManager",model);
    }

    /**
     * 客服员中心工作台
     *
     */
    @RequestMapping(value = "/customerManager")
    public ModelAndView customerManager(HttpServletRequest req, HttpServletResponse rsp){
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CustomerNumberDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CUSTOMER_INFO_NUMBER, null, req);
        Map model = new HashMap();
        model.put("numberDto",apiFinalResponse.getResults());

        //获取角色
        typeToken = new TypeToken<ApiFinalResponse<CaseUserRoleDto>>(){};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_USER_ROLE, null, req);
        CaseUserRoleDto caseUserRoleDto = (CaseUserRoleDto)apiFinalResponse.getResults();
        model.put("caseUserRole",caseUserRoleDto);

        return new ModelAndView("/billingApply/customerManager",model);
    }


    /**
     * 根据id，查询关联的子表数据
     */
    @RequestMapping(value = "/selectInfoByRelationId")
    public String selectInfoByRelationId(HttpServletRequest req, HttpServletResponse rsp) {
        String btnCode = req.getParameter("btnCode");
        Map<String, Object> appendMap = new HashMap<String, Object>();
        //根据“产品类型id”，查询“机构”
        if("1000".equals(btnCode)) {
            Long productTypeId = Long.parseLong(req.getParameter("productTypeId"));
            appendMap.put("productTypeId", productTypeId);
            appendMap.put("btnCode",btnCode);
            appendMap.put("auth",1);//判断是否需要根据“开票权限”筛选机构数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
        }
        //根据“机构”，查询“产品类目”
        else if("1100".equals(btnCode)) {
            Long organId = Long.parseLong(req.getParameter("organId"));
            appendMap.put("organId", organId);
            appendMap.put("btnCode",btnCode);
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
        }
        //根据“类目”，查询“项目”
        else if("1200".equals(btnCode)) {
            Long billingEnum = Long.parseLong(req.getParameter("billingEnum"));
            appendMap.put("billingEnum", billingEnum);
            appendMap.put("btnCode",btnCode);
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
        }
        //查询批量开票的所有金额
        else if("1300".equals(btnCode) || "13001".equals(btnCode)) {
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_SELECT_INFO_BY_RELATION_ID, appendMap, req, rsp);
        }
        //查询开票公司的税率
        else if("5800".equals(btnCode)) {
            Long corporationId = Long.parseLong(req.getParameter("corporationId"));
            appendMap.put("corporationId", corporationId);
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_APPLY_CORPORATION_DETAILS, appendMap, req, rsp);
        }
        return null;
    }


}
