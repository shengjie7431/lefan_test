package com.lefancrm.backend.web;


import com.alibaba.fastjson.JSONArray;
import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.backend.util.DateUtil;
import com.lefancrm.backend.util.ExcelReport;
import com.lefancrm.backend.util.StaffExcel;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.apache.http.impl.cookie.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping(value = "/hobo/case/")
public class BackendHoboCaseController extends BackendBaseController{
    @Value("${survey.setting.source}")
    private String settingSource;
    @Value("${survey.file.path.sftp}")
    private String fileHttp;


    @RequestMapping(value = "list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        model.put("pageSize",req.getParameter("pageSize"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<HoboProCase>>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.HOBO_PRO_CASE_LIST, null, req);
        model.put("apiRsp",apiFinalResponse);
        model.put("searchStr",req.getParameter("searchStr"));
        model.put("carNo",req.getParameter("carNo"));
        model.put("proName",req.getParameter("proName"));
        model.put("entrustTimeStart",req.getParameter("entrustTimeStart"));
        model.put("entrustTimeEnd",req.getParameter("entrustTimeEnd"));
        return new ModelAndView("/hobo/case/list",model);
    }

    @RequestMapping(value = "reportData")
    public ModelAndView reportData(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<HoboReportData>>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.HOBO_PRO_CASE_REPORT_LIST, null, req);
        model.put("apiRsp",apiFinalResponse);
        model.put("entrustTimeStart",req.getParameter("entrustTimeStart"));
        model.put("entrustTimeEnd",req.getParameter("entrustTimeEnd"));
        return new ModelAndView("/hobo/case/reportList",model);
    }

    @RequestMapping(value = "info")
    public ModelAndView info(HttpServletRequest req, HttpServletResponse rsp){
        String oprType = req.getParameter("oprType");
        Map model = new HashMap();
        if ("import".equals(oprType)){
            return new ModelAndView("/hobo/case/import",model);
        }else if ("detail".equals(oprType)){
            TypeToken typeToken = new TypeToken<ApiFinalResponse<HoboProCase>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.HOBO_PRO_CASE_INFO, null, req);
            model.put("dto",apiFinalResponse.getResults());
            return new ModelAndView("/hobo/case/detail",model);
        }
        return null;
    }


    /**
     * 创建清单
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping(value = "ajaxData")
    public String ajaxData(MultipartHttpServletRequest req, HttpServletResponse rsp){
        String btnCode = req.getParameter("btnCode");
        if ("import".equals(btnCode)){
            String entrustTime = req.getParameter("entrustTime");
            MultipartFile multipartFile = req.getFile("file");
            Map<String,Object> appendMap =  new HashMap<String,Object>();
            try {
                InputStream inputStream = multipartFile.getInputStream();
                List<HoboProCase> zhaData = StaffExcel.importHoboProCase(inputStream);
                appendMap.put("data", JSONArray.toJSON(zhaData));
                appendMap.put("entrustTime",entrustTime);
            }catch (Exception e){
                e.printStackTrace();
            }
            return this.callApiAndOutput(BackendApiMethodEnum.HOBO_PRO_CASE_IMPORT, appendMap, req, rsp);
        }else if ("".equals(btnCode)){

        }else if ("".equals(btnCode)){

        }
        return null;
    }

    @RequestMapping(value = "exportData")
    public void  exportData(HttpServletRequest req, HttpServletResponse rsp){
        String exportType = req.getParameter("exportType");
        if ("1".equals(exportType)){
            Map appendMap = new HashMap();
            appendMap.put("btnCode","report");
            TypeToken typeToken = new TypeToken<ApiFinalResponse<List<HoboProCase>>>() {};
            ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.HOBO_PRO_CASE_LIST, appendMap, req);
            List<HoboProCase> list = (List<HoboProCase>) apiFinalResponse.getResults();
            ExcelReport.report(new HoboProCase(),list,"导出数据",rsp);
        }else if ("2".equals(exportType)){
            ExcelReport.report(new HoboProCaseTemplate(),new ArrayList<>(),"导出模板" + DateUtils.formatDate(new Date(),"yyyy-MM-dd"),rsp);
        }
    }

}
