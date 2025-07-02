package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.dto.financial.FinancialReApply;
import com.lefancrm.backend.dto.financial.FinancialReApplyDto;
import com.lefancrm.backend.dto.staff.StaffPersonnelInfoDto;
import com.lefancrm.backend.util.ExcelReport;
import com.lefancrm.backend.util.PDFFinaancial;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Created by wangwei on 2021/01/22.
 * 每刻报销
 */
@Controller
@RequestMapping(value = "/financial/reApply/")
public class BackendFinancialReApplyController extends BackendBaseController{

    @Value("${survey.file.path.sftp}")
    public String httpFilePath;
    @Value("${survey.file.source.sftp}")
    private String surveySource;
    @Value("${survey.temp.path}")
    private String realTempPath;


    /**
    *
     */
    @RequestMapping(value = "list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        Map model = new HashMap();
        //判断是不是人事角色，控制添加 按钮
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BusUserRoleDto>>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CURRENT_USER_ROLE_LIST, null, req);
        List<BusUserRoleDto> userRoles = (List<BusUserRoleDto>)apiFinalResponse.getResults();
        Boolean financeRole = false;
        for (BusUserRoleDto busUserRoleDto : userRoles) {
            if(busUserRoleDto.getRoleId() == 23){ //财务专员
                financeRole = true;
            }
        }
        model.put("financeRole", financeRole);
        //当前登录人信息
        UserInfo adminSession = this.getSessionAdmin(req);
        model.put("currentUserId",adminSession.getUserId());

        return new ModelAndView("/financial/reApply/list",model);
    }


    @RequestMapping(value = "edit")
    public ModelAndView edit(HttpServletRequest req,HttpServletResponse rsp){
        Map<String, Object> appendMap = new HashMap<String, Object>();
        //初始化数据
        Map model = new HashMap();
        String financialReApplyId = req.getParameter("financialReApplyId");
        model.put("financialReApplyId",financialReApplyId);
        model.put("reType",req.getParameter("reType"));//报销类型（1：日常费用报销，2：对公支付，3：借款）
        model.put("copy",req.getParameter("copy"));

        if(financialReApplyId == null){ // 初始化
            //当前登录人信息
            appendMap = new HashMap<String, Object>();
            appendMap.put("dataType","staff-personnel-info");//
            TypeToken typeToken = new TypeToken<ApiFinalResponse<StaffPersonnelInfoDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_AJAX_DATA_FINANCIAL_RE_APPLY, appendMap, req);
            StaffPersonnelInfoDto info = (StaffPersonnelInfoDto)apiFinalResponse.getResults();
            if (info == null){
                model.put("msg","请联系管理员添加员工信息！");
                return new ModelAndView("/financial/reApply/error",model);
            }
            model.put("companyId", info.getSocialSecurityCompanyId());
            model.put("organId", info.getOrganId());
            model.put("updateOrgan",info.getIsCanModify());//是否能修改机构以及公司 0否 1是

            //历史记录数据
            appendMap = new HashMap<String, Object>();
            appendMap.put("dataType","last-financial-re-apply-info");//
            appendMap.put("reType",req.getParameter("reType"));
            typeToken = new TypeToken<ApiFinalResponse<FinancialReApplyDto>>() {};
            apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_AJAX_DATA_FINANCIAL_RE_APPLY, appendMap, req);
            FinancialReApplyDto reApplyDto = (FinancialReApplyDto)apiFinalResponse.getResults();
            if(reApplyDto!=null){
                model.put("lastReApplyInfo", reApplyDto);
                model.put("payeeNo", reApplyDto.getPayeeNo());
                model.put("payeeName", reApplyDto.getPayeeName());
                model.put("bankId", reApplyDto.getBankId());
                model.put("branchBank", reApplyDto.getBranchBank());
                model.put("payUserId", reApplyDto.getPayUserId());
            }
        }

        return new ModelAndView("/financial/reApply/add",model);
    }

    /**
     * ajax获取list 列表
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "getDetail")
    public String getDetail(HttpServletRequest req, HttpServletResponse rsp){
        Map<String,Object> appendMap = new HashMap<>();
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINANCIAL_RE_APPLY_LIST, appendMap, req, rsp);
    }


    @RequestMapping(value = "operate")
    public String operate(HttpServletRequest req, HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_FINANCIAL_RE_APPLY_OPERATE, null, req, rsp);
    }

    @RequestMapping(value = "ajaxData")
    public void ajaxData(HttpServletRequest req, HttpServletResponse rsp){
        this.callApiAndOutput(BackendApiMethodEnum.BACKEND_AJAX_DATA_FINANCIAL_RE_APPLY, null, req, rsp);

    }

    @RequestMapping(value = "operateView")
    public ModelAndView operateView(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        String btnCode = req.getParameter("btnCode");
        model.put("btnCode",btnCode);
        model.put("financialReApplyId",req.getParameter("financialReApplyId"));
        Map<String, Object> appendMap = new HashMap<String, Object>();
        if("repayment".equals(btnCode))//新增还款
        {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<FinancialReApplyDto>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINANCIAL_RE_APPLY_INFO, null, req);
            FinancialReApplyDto financialReApplyDto = (FinancialReApplyDto)apiFinalResponse.getResults();
            if(financialReApplyDto!=null){
                model.put("repaymentMoney",financialReApplyDto.getRepaymentMoney());
            }
            return new ModelAndView("/financial/reApply/repayment",model);
        }
        else if("progres".equals(btnCode))//进度
        {
            return new ModelAndView("/financial/reApply/progres",model);
        }
        else if("info".equals(btnCode))//详情页
        {
            model.put("reType",req.getParameter("reType"));//报销类型（1：日常费用报销，2：对公支付，3：借款）
            return new ModelAndView("/financial/reApply/info",model);
        }

        return null;
    }


    //导出
    @RequestMapping(value = "/downLoad")
    public void   downLoad(HttpServletRequest req, HttpServletResponse rsp) {
        String dataType = req.getParameter("dataType");
        if("get-info".equals(dataType)) { // 打印分支
            Map<String,Object> jsonMap = new HashMap<>();
            TypeToken typeToken = new TypeToken<ApiFinalResponse<FinancialReApply>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_AJAX_DATA_FINANCIAL_RE_APPLY, null, req);
            FinancialReApply data = (FinancialReApply) apiFinalResponse.getResults();
            File file = PDFFinaancial.generates(realTempPath + File.separator + "pdf" + File.separator + System.currentTimeMillis(), data);
            jsonMap.put("fileUrl",file.getPath().replace("/mnt/sftp/files/",httpFilePath));
            String json = sh.zj100.common.util.JsonUtil.objectToJson(jsonMap);
            this.outputJson(json, rsp);
//                        System.out.println(data);
//            return ;
        }else{//导出
            String surveyCode = req.getParameter("surveyCode");
            //每刻报销导出
            if("reAppleInfo".equals(surveyCode)) {
                downLoadReAppleInfo(req, rsp, surveyCode);
            }
        }
    }
    //每刻报销导出
    private void downLoadReAppleInfo(HttpServletRequest req, HttpServletResponse rsp,String surveyCode) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("report","report");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<FinancialReApplyDto>>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_FINANCIAL_RE_APPLY_LIST, appendMap, req);
        List<FinancialReApplyDto> infos = (List<FinancialReApplyDto>)apiFinalResponse.getResults();
        ExcelReport.reportReAppleInfo(infos, new HashMap<>(), rsp);
    }
}
