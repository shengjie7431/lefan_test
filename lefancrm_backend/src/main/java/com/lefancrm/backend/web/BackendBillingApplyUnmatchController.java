package com.lefancrm.backend.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.util.ExcelReport;
import com.lefancrm.backend.util.FileZipUtil;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import com.lefancrm.base.web.WebHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.Boolean;
import java.util.*;


/**
 * Created by wangwei on 2018/12/24.
 */
@Controller
@RequestMapping(value = "/billingApplyUnmatch")
public class BackendBillingApplyUnmatchController extends BackendBaseController {
    @Value("${survey.account.path}")
    private String accountExcelPath;
    /**
     * 列表
     *
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp) {
        String unmatchNo = req.getParameter("unmatchNo");
        String claimBy = req.getParameter("claimBy");
        String states = req.getParameter("states")==null?"1,3":req.getParameter("states");
        String payTimeStart = req.getParameter("payTimeStart");
        String payTimeEnd = req.getParameter("payTimeEnd");
        String billingItemsIds=req.getParameter("billingItemsIds");
        String receivingCompanyIds=req.getParameter("receivingCompanyIds");
        String payer=req.getParameter("payer");
        String waitCheck=req.getParameter("waitCheck");
        String remark=req.getParameter("remark");

        Map findMap=new HashMap();
        findMap.put("states",states);
        findMap.put("waitCheck",waitCheck);
        findMap.put("limit","true");

        //开票list
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyUnmatchDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_UNMATCH_LIST, findMap, req);


        Map model = new HashMap();
        model.put("apiRsp",apiFinalResponse);
        model.put("unmatchNo",unmatchNo==null?"":unmatchNo);
        model.put("claimBy",claimBy==null?"":claimBy);
        model.put("states",states==null?"":states);
        model.put("payTimeStart",payTimeStart==null?"":payTimeStart);
        model.put("payTimeEnd",payTimeEnd==null?"":payTimeEnd);
        model.put("billingItemsIds",billingItemsIds==null?"":billingItemsIds);
        model.put("receivingCompanyIds",receivingCompanyIds==null?"":receivingCompanyIds);
        model.put("payer",payer==null?"":payer);
        model.put("waitCheck",waitCheck==null?"":waitCheck);
        model.put("remark",remark==null?"":remark);
        model.put("startReceiveTime",req.getParameter("startReceiveTime"));
        model.put("endReceiveTime",req.getParameter("endReceiveTime"));

        Map paramMap = new HashMap();
        //开票项目 -- 枚举查询
        paramMap = new HashMap<>();
        paramMap.put("enumCode", "billingEnum");
        typeToken = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, paramMap, req);
        List<CommonEnumDto> bullingItems = (List<CommonEnumDto>) apiFinalResponse.getResults();
        model.put("bullingItems", bullingItems);

        Map params = new HashMap();
        params.put("bullingItems", JsonUtil.objectToJson(bullingItems));
        //到账公司
        paramMap = new HashMap<>();
        paramMap.put("noPageIndex", 1); //不分页
        typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_CORPORATION_LIST, paramMap, req);
        List<BillingApplyCorporationDto> corporations = (List<BillingApplyCorporationDto>) apiFinalResponse.getResults();
        model.put("corporations", corporations);
        params.put("corporations", JsonUtil.objectToJson(corporations));

        model.put("params", params);
        model.put("pageSize",req.getParameter("pageSize"));
        return new ModelAndView("/billingApply/unmatch/list",model);
    }
    /**
     * 详情页面
     *
     */
    @RequestMapping(value = "/info")
    public ModelAndView info(HttpServletRequest req , HttpServletResponse rsp) {
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<BillingApplyUnmatchDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_UNMATCH_BY_ID, null, req);
        BillingApplyUnmatchDto info = (BillingApplyUnmatchDto) apiFinalResponse.getResults();
        model.put("info", info);


        typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BILLING_RECEIVE_INFO_SELECTBYMAP, null, req);
        List<BillingApplyDto> BillingApplyDtoList=(List<BillingApplyDto>)apiFinalResponse.getResults();
        model.put("BillingApplyDtoList", BillingApplyDtoList);

        typeToken = new TypeToken<ApiFinalResponse<List<BillingRefundInfoDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BILLING_RECEIVE_INFO_SELECTBYMAP_REFUND, null, req);
        List<BillingRefundInfoDto> billingRefundInfoDtoList=(List<BillingRefundInfoDto>)apiFinalResponse.getResults();
        model.put("billingRefundInfoDtoList", billingRefundInfoDtoList);

        //开票类目 -- 枚举查询
        Map<String, Object> appendMap = new HashMap<String, Object>();
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
        model.put("bullingEnums",bullingEnums);
        model.put("bullingItems",bullingItems);

        typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_CUR_USER_ROLES, null, req);
        List<BusUserRoleDto> userRoles = (List<BusUserRoleDto>)apiFinalResponse.getResults();
        model.put("france",isRoleUser(userRoles,23L));

        appendMap.put("menuName","开票清单");
        typeToken = new TypeToken<ApiFinalResponse<JSONObject>>() {};
        apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SELECT_CUR_USER_ROLES_MENU, appendMap, req);
        JSONObject results = (JSONObject)apiFinalResponse.getResults();
        model.put("haveMenu",results.get("haveMenu"));//是否有开票清单这个菜单
        model.put("currentUserId",getSessionAdminId(req));
        return new ModelAndView("/billingApply/unmatch/info",model);
    }

    /**
     * 添加页面
     *
     */
    @RequestMapping(value = "/add")
    public ModelAndView add(HttpServletRequest req){
        Map model = new HashMap();

        String btnCode = req.getParameter("btnCode");
        if (StringUtils.isNotBlank(btnCode) && "refund".equals(btnCode)){
            String unmatchId = req.getParameter("unmatchId");
            String money = req.getParameter("money");
            String apply = req.getParameter("apply");
            model.put("unmatchId",unmatchId);
            model.put("unmatchMoney",money);
            model.put("apply",apply);
            return new ModelAndView("/billingApply/unmatch/refund",model);
        }

        //开票项目 -- 枚举查询
        Map paramMap = new HashMap<>();
        paramMap.put("enumCode", "billingEnum");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, paramMap, req);
        List<CommonEnumDto> bullingItems = (List<CommonEnumDto>) apiFinalResponse.getResults();
        model.put("bullingItems", bullingItems);

        paramMap = new HashMap<>();
        paramMap.put("noPageIndex", 1); //不分页
        typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_CORPORATION_LIST, paramMap, req);
        List<BillingApplyCorporationDto> corporations = (List<BillingApplyCorporationDto>) apiFinalResponse.getResults();
        model.put("corporations", corporations);
        return new ModelAndView("/billingApply/unmatch/edit",model);
    }

    /**
     * 生成编号
     *
     */
    @RequestMapping(value = "/toBuildUnmatchNo")
    public String toBuildUnmatchNo(HttpServletRequest req, HttpServletResponse rsp) {
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_TO_BUILD_UNMATCH_NO, null, req);
        req.getSession().setAttribute("json",json);
        return WebHelper.outputJson(json, rsp);
    }
    /**
     * 添加页面
     *
     */
    @RequestMapping(value = "/edit")
    public ModelAndView edit(HttpServletRequest req){
        Map<String, Object> paramMap = new HashMap<>();

        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<BillingApplyUnmatchDto>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_UNMATCH_BY_ID, null, req);
        BillingApplyUnmatchDto info = (BillingApplyUnmatchDto) apiFinalResponse.getResults();
        model.put("info", info);


        //开票项目 -- 枚举查询
        paramMap = new HashMap<>();
        paramMap.put("enumCode", "billingEnum");
        typeToken = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, paramMap, req);
        List<CommonEnumDto> bullingItems = (List<CommonEnumDto>) apiFinalResponse.getResults();
        model.put("bullingItems", bullingItems);

        //到账公司
        paramMap = new HashMap<>();
        paramMap.put("noPageIndex", 1); //不分页
        typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_CORPORATION_LIST, paramMap, req);
        List<BillingApplyCorporationDto> corporations = (List<BillingApplyCorporationDto>) apiFinalResponse.getResults();
        model.put("corporations", corporations);
        return new ModelAndView("/billingApply/unmatch/edit",model);
    }

    /**
     * 保存
     *
     */
    @RequestMapping(value = "/update")
    public String update(HttpServletRequest req,HttpServletResponse rsp){
        if("claim".equals(req.getParameter("meunCode"))){
            return this.callApiAndOutput(BackendApiMethodEnum.BILLING_RECEIVE_INFO_CHANGE, null, req, rsp);
        }else if ("unminfo".equals(req.getParameter("meunCode"))){
            return this.callApiAndOutput(BackendApiMethodEnum.BILLING_RECEIVE_INFO_CHANGE_UPD, null, req, rsp);
        }else if ("refundInfo".equals(req.getParameter("meunCode"))){
            return this.callApiAndOutput(BackendApiMethodEnum.BILLING_RECEIVE_INFO_CHANGE_REFUND_UPD, null, req, rsp);
        }else if ("refundAdd".equals(req.getParameter("meunCode"))){
            return this.callApiAndOutput(BackendApiMethodEnum.BILLING_RECEIVE_INFO_CHANGE_REFUND_ADD, null, req, rsp);
        }
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_APPLY_UNMATCH_UPDATE, null, req, rsp);
    }


    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_APPLY_UNMATCH_DELETE, null, req, rsp);
    }

    /**
     * 已开票认领--公估未到账的list
     *
     */
    @RequestMapping(value = "/billingList")
    public ModelAndView billingList(HttpServletRequest req , HttpServletResponse rsp) {
        Map model = new HashMap();
        Map<String, Object> paramMap = new HashMap<>();
        String unmatchId = req.getParameter("unmatchId");
        String money = req.getParameter("money");
        String caseNo = req.getParameter("caseNo");
        String billingCode = req.getParameter("billingCode");
        String caseTitle = req.getParameter("caseTitle");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_LIST_FOR_UNMATCH, null, req);
        model.put("apiRsp",apiFinalResponse);
        model.put("unmatchId",unmatchId);
        model.put("money",money);
        model.put("caseNo",caseNo==null?"":caseNo.trim());
        model.put("billingCode",billingCode==null?"":billingCode.trim());
        model.put("caseTitle",caseTitle==null?"":caseTitle.trim());
        model.put("apply",req.getParameter("apply"));

        //开票类目 -- 枚举查询
        Map<String, Object> appendMap = new HashMap<String, Object>();
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
        model.put("bullingEnums",bullingEnums);
        model.put("bullingItems",bullingItems);

        return new ModelAndView("/billingApply/unmatch/billingList",model);
    }

    /**
     * 已开票认领
     *
     */
    @RequestMapping(value = "/claim")
    public String claim(HttpServletRequest req,HttpServletResponse rsp){
        if (StringUtils.isNotBlank(req.getParameter("annual"))){
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_ANNUAL_TABLE_LIST, null, req, rsp);
        }
        if (StringUtils.isNotBlank(req.getParameter("unmatchBank"))){
            return this.callApiAndOutput(BackendApiMethodEnum.SURVEY_CHANNEL_AJAX_DATA_NEW, null, req, rsp);
        }
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_BILLING_APPLY_UNMATCH_CLAIM, null, req, rsp);
    }


    /**
     * 年度汇总表
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "/annualTableList")
    public ModelAndView annualTable(HttpServletRequest req , HttpServletResponse rsp) {
        Map model = new HashMap();

        //判断当前登录人角色
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CURRENT_USER_ROLE_LIST, null, req);
        List<BusUserRoleDto> userRoles = (List<BusUserRoleDto>)apiFinalResponse.getResults();

        Boolean orgManage = false;
        for (BusUserRoleDto busUserRoleDto : userRoles) {
            if(busUserRoleDto.getRoleId() == 117){
                orgManage = true;
            }
        }
        Boolean finance = false;
        for (BusUserRoleDto busUserRoleDto : userRoles) {
            if(busUserRoleDto.getRoleId() == 23){
                finance = true;
            }
        }
        //公估业务机构
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("productTypeId", 1);
        appendMap.put("btnCode",1000);
        appendMap.put("orgManage",orgManage);//机构负责人
        appendMap.put("finance",finance);//财务部
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_BILLING_SELECT_INFO_BY_RELATION_ID, appendMap, req);
        List<OrgInfoDto> orgInfoDtosGG = (List<OrgInfoDto>) apiFinalResponse1.getResults();

        //个人业务机构
        appendMap = new HashMap<String, Object>();
        appendMap.put("productTypeId", 2);
        appendMap.put("btnCode",1000);
        appendMap.put("orgManage",orgManage);//机构负责人
        appendMap.put("finance",finance);//财务部
        typeToken1 = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>() {};
        apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_BILLING_SELECT_INFO_BY_RELATION_ID, appendMap, req);
        List<OrgInfoDto> orgInfoDtosGR = (List<OrgInfoDto>) apiFinalResponse1.getResults();

        //开票类目 -- 枚举查询
        appendMap = new HashMap<String, Object>();
        appendMap.put("enumCode", "billingEnum");
        TypeToken typeToken2 = new TypeToken<ApiFinalResponse<List<CommonEnumDto>>>() {};
        ApiFinalResponse apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_COMMON_ENUM_FOR_BILLING_ENUM, appendMap, req);
        List<CommonEnumDto> bullingEnums = (List<CommonEnumDto>) apiFinalResponse2.getResults();

        //开票公司
        appendMap = new HashMap<String, Object>();
        appendMap.put("noPageIndex", 1); //不分页
        typeToken2 = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationDto>>>() {};
        apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_BILLING_APPLY_CORPORATION_LIST, appendMap, req);
        List<BillingApplyCorporationDto> corporations = (List<BillingApplyCorporationDto>) apiFinalResponse2.getResults();


        model.put("orgInfoDtosGG",JSON.toJSONString(orgInfoDtosGG));
        model.put("orgInfoDtosGR", JSON.toJSONString(orgInfoDtosGR));
        model.put("bullingEnums", JSON.toJSONString(bullingEnums));
        model.put("corporations", JSON.toJSONString(corporations));

        model.put("orgManage", orgManage);
        model.put("finance", finance);

        return new ModelAndView("/billingApply/annualTableList",model);
    }


    /**
     * 年度汇总表导出
     *
     * */
    @RequestMapping(value = "/export")
    public void export(HttpServletRequest req, HttpServletResponse rsp) {
        String fromType = req.getParameter("ummatch");
        if (StringUtils.isNotBlank(fromType)){//未匹配收款导出
            String states = req.getParameter("states")==null?"1,3":req.getParameter("states");
            Map findMap=new HashMap();
            findMap.put("states",states);
            findMap.put("limit","false");

            //开票list
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BillingApplyUnmatchDto>>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_APPLY_UNMATCH_LIST, findMap, req);
            List<BillingApplyUnmatchDto> billingApplyUnmatchDtos = (List<BillingApplyUnmatchDto>)apiFinalResponse.getResults();
            ExcelReport.reportUnmatch(billingApplyUnmatchDtos,rsp);
            return;
        }


        String bookType = req.getParameter("bookType");
        String dateTimes = req.getParameter("dateTimes");
        if("all".equals(bookType)){//总表
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("export","export");
            paramMap.put("dateTimes",dateTimes);
            TypeToken<ApiFinalResponse<List<OrgInfoDto>>> typeToken = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>(){};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_ANNUAL_TABLE_LIST, paramMap, req);
            List<OrgInfoDto> dtos = (List<OrgInfoDto>)apiFinalResponse.getResults();

            ExcelReport.reportOrgInfo(dtos, new HashMap<>(), rsp, null, "总表");
        }else{
            Long timestamp = System.currentTimeMillis();//当前时间戳
            String excelPath = accountExcelPath + timestamp;
            File file = new File(excelPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            //选择公司(权限过滤后的列表)
            Map<String, Object> appendMap = new HashMap<String, Object>();
            appendMap.put("noPageIndex", 1); //不分页
            appendMap.put("btnCode",1000);
            TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<BillingApplyCorporationDto>>>() {};
            ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_BILLING_APPLY_CORPORATION_LIST, appendMap, req);
            List<BillingApplyCorporationDto> corporations = (List<BillingApplyCorporationDto>) apiFinalResponse1.getResults();
            //循环开票公司
            for (BillingApplyCorporationDto corporation : corporations) {
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("export","export");
                paramMap.put("dateTimes",dateTimes);
                paramMap.put("businessTypeId",corporation.getId());
                TypeToken<ApiFinalResponse<List<OrgInfoDto>>> typeToken = new TypeToken<ApiFinalResponse<List<OrgInfoDto>>>(){};
                ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BILLING_ANNUAL_TABLE_LIST, paramMap, req);
                List<OrgInfoDto> dtos = (List<OrgInfoDto>)apiFinalResponse.getResults();

                ExcelReport.reportOrgInfo(dtos, new HashMap<>(), rsp, excelPath, corporation.getName());
            }

            FileZipUtil.createZip(excelPath, excelPath + ".zip");
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
        }
    }

}
