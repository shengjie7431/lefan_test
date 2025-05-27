package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.SurveyConsignorDto;
import com.lefancrm.backend.dto.SurveyFranchiseeDto;
import com.lefancrm.backend.dto.survey.SurveyCaseArchivesDto;
import com.lefancrm.backend.util.ExcelReport;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2020年10月28日13:46:27
 * 归档处理
 */
@Controller
@RequestMapping(value = "/surveyCaseArchives/")
public class BackendSurveyCaseArchivesController extends BackendBaseController{

    /**
     * 归档处理
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp){
        Map<String, Object> appendMap = new HashMap<String, Object>();
        Map model = new HashMap();

        model.put("surveyCaseNo",req.getParameter("surveyCaseNo"));//快捷查询
        model.put("entrustOrgIds",req.getParameter("entrustOrgIds")==null?"":req.getParameter("entrustOrgIds"));
        model.put("surveyOrgIds",req.getParameter("surveyOrgIds")==null?"":req.getParameter("surveyOrgIds"));
        model.put("archivesState",req.getParameter("archivesState"));//归档状态（0：未归档完成，1：已经归档完成）
        model.put("startTime",req.getParameter("startTime")==null?"":req.getParameter("startTime"));
        model.put("endTime",req.getParameter("endTime")==null?"":req.getParameter("endTime"));

/*        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyCaseArchivesDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_ARCHIVES_LIST, null, req);
        req.setAttribute("apiRsp", apiFinalResponse);*/

        Map params=new HashMap();
        //所有的委托方机构
        appendMap = new HashMap<String, Object>();
        appendMap.put("menuType",1); //不分页
        appendMap.put("surveyCode","consignor");//查询所有的委托方
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
        List<SurveyConsignorDto> consignors = (List<SurveyConsignorDto>) apiFinalResponse.getResults();
        model.put("consignors",consignors);
        params.put("consignorsJson", JsonUtil.objectToJson(consignors));

        //所有的调查方
        appendMap = new HashMap<String, Object>();
        appendMap.put("menuType",1); //不分页
        appendMap.put("surveyCode","franchisee");//查询所有的调查方
        typeToken = new TypeToken<ApiFinalResponse<List<SurveyFranchiseeDto>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, appendMap, req);
        List<SurveyFranchiseeDto> franchisees = (List<SurveyFranchiseeDto>) apiFinalResponse.getResults();
        model.put("franchisees",franchisees);
        params.put("franchiseesJson", JsonUtil.objectToJson(franchisees));
        model.put("params", params);

        return new ModelAndView("/survey/surveyCaseArchives/list",model);
    }


    /**
     * 获取详情列表信息
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "getDetail")
    public String getDetail(HttpServletRequest req, HttpServletResponse rsp){
        Map param =  new HashMap<>();
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_ARCHIVES_LIST, null, req, rsp);
    }

    @RequestMapping(value = "operate")
    public String operate(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CASE_ARCHIVES_OPERATE, null, req, rsp);

    }

    @RequestMapping(value = "export")
    public void export(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap = new HashMap<String, Object>();
        appendMap.put("noPage",1); //不分页
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyCaseArchivesDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CASE_ARCHIVES_LIST, appendMap, req);

        ExcelReport.reportCaseArchives((List<SurveyCaseArchivesDto>) apiFinalResponse.getResults(), rsp);

    }

}
