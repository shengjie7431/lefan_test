package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.SurveyChannelCostNew;
import com.lefancrm.backend.dto.claims.*;
import com.lefancrm.backend.util.ExcelReport;
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

@Controller
@RequestMapping(value = "/claims/")
public class BackendClaimsController extends  BackendBaseController{
    @RequestMapping(value = "list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp){
        String menuCode = req.getParameter("menuCode");
        Map model = new HashMap();
        model.put("menuCode",menuCode);
        return new ModelAndView("/claims/" + menuCode,model);
    }

    /**
     * 操作
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "operate")
    public String operate(HttpServletRequest req,HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CLAIMS_OPERATE, null, req, rsp);
    }

    /**
     * ajax获取数据
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "ajaxData")
    public String ajaxData(HttpServletRequest req,HttpServletResponse rsp){
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CLAIMS_AJAX_DATA, null, req, rsp);
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
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CLAIMS_LIST, appendMap, req, rsp);
    }

    /**
     * 导出
     * @param req
     * @param rsp
     */
    @RequestMapping(value = "export")
    public void export(HttpServletRequest req, HttpServletResponse rsp){
        Map<String,Object> appendMap = new HashMap<>();
        appendMap.put("report","report");
        String tableName = req.getParameter("tableName");
        TypeToken typeToken = null;
        if ("sqlp".equals(tableName)){
            typeToken = new TypeToken<ApiFinalResponse<List<ClaimsApplyInfo>>>() {};
        }else if ("sgzrzd".equals(tableName)){
            typeToken = new TypeToken<ApiFinalResponse<List<ClaimsAnalysisInfo>>>() {};
        }else if ("sgzrfw".equals(tableName)){
            typeToken = new TypeToken<ApiFinalResponse<List<ClaimsShuttleInfo>>>() {};
        }else if ("wwts".equals(tableName)){
            typeToken = new TypeToken<ApiFinalResponse<List<ClaimsVisitInfo>>>() {};
        }else if ("qcbb".equals(tableName)){
            typeToken = new TypeToken<ApiFinalResponse<List<ClaimsArrangedInfo>>>() {};
        }
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CLAIMS_LIST, appendMap, req);
        switch (tableName){
            case "sqlp" : ExcelReport.report(new ClaimsApplyInfo(),(List<ClaimsApplyInfo>)apiFinalResponse.getResults(),"申请理赔",rsp); break;
            case "sgzrzd" : ExcelReport.report(new ClaimsAnalysisInfo(),(List<ClaimsAnalysisInfo>)apiFinalResponse.getResults(),"事故责任分析与指导",rsp); break;
            case "sgzrfw" : ExcelReport.report(new ClaimsShuttleInfo(),(List<ClaimsShuttleInfo>)apiFinalResponse.getResults(),"事故处理接送服务",rsp); break;
            case "wwts" : ExcelReport.report(new ClaimsVisitInfo(),(List<ClaimsVisitInfo>)apiFinalResponse.getResults(),"慰问探视",rsp); break;
            case "qcbb" : ExcelReport.report(new ClaimsArrangedInfo(),(List<ClaimsArrangedInfo>)apiFinalResponse.getResults(),"全程包办",rsp); break;
        }
    }
}
