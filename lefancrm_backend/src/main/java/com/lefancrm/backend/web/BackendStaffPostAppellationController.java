package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.staff.StaffPostAppellationDto;
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
@RequestMapping(value = "/staff/staffpostappellation")
public class BackendStaffPostAppellationController extends BackendBaseController {
    /*
     * 获取职务称谓列表
     */
    @RequestMapping(value = "/list")
    public ModelAndView staffpostappellationList(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        String appellationName=req.getParameter("appellationName");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="1";
        }
        appendMap.put("pageSize", req.getParameter("pageSize"));//code标识
        appendMap.put("appellationName",appellationName);
        appendMap.put("state",req.getParameter("state") == null ? 0 : req.getParameter("state"));//默认启用
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<StaffPostAppellationDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_QUERY_STAFF_POST_APPLLATION_LIST, appendMap, req);
        appendMap.put("page",page);
        appendMap.put("appellationName",appellationName);
        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/staff/staffPostAppellation/list",appendMap);
    }

    /*
     * 详情获取
     */
    @RequestMapping(value = "/staffPostAppellationDetails")
    public ModelAndView staffPostAppellationDetails(HttpServletRequest req, HttpServletResponse rsp) {
        //详情获取
        Map model = new HashMap();
        if (StringUtils.isEmpty(req.getParameter("id"))) {
            return new ModelAndView("/staff/staffPostAppellation/edit",model);
        }
        else {
            TypeToken typeToken = new TypeToken<ApiFinalResponse<StaffPostAppellationDto>>() {};
            ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_QUERY_STAFF_POST_APPLLATION_BY_ID, null, req);
            model.put("staffPostAppellation",apiFinalResponse.getResults());
            return new ModelAndView("/staff/staffPostAppellation/edit",model);
        }
    }
    /*
     * 职务称谓修改或新增
     */
    @RequestMapping(value = "/saveOrupdate")
    public String saveOrupdate(HttpServletRequest req, HttpServletResponse rsp) {
        if (StringUtils.isEmpty(req.getParameter("id"))) {
            //新增数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_STAFF_POST_APPLLATION_ADD_SELETIVE, null, req, rsp);
        } else {
            //修改数据
            return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_UPDATE_STAFF_POST_APPLLATION_BY_PARAM, null, req, rsp);
        }

    }
}
