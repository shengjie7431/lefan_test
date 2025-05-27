package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.PositionInfoDto;
import com.lefancrm.backend.dto.PositionLevelDto;
import com.lefancrm.backend.dto.hb.HbOrgInfoDto;
import com.lefancrm.backend.dto.hb.HbRecordDto;
import com.lefancrm.backend.dto.hb.HbUserInfoDto;
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
@RequestMapping(value = "/hb/")
public class BackendHbController extends BackendBaseController{

    @RequestMapping(value = "/hbOrgList")
    public ModelAndView hbOrgList(HttpServletRequest req, HttpServletResponse rsp, ModelAndView modelAndView) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="0";
        }


        //
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<HbOrgInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_HB_ORG_LIST, null, req);
        //List<HbOrgInfoDto> hbOrgInfoDtos = (List<HbOrgInfoDto>) apiFinalResponse1.getResults();

        Map model = new HashMap();
        model.put("apiRsp", apiFinalResponse1);
        model.put("hbOrgName", req.getParameter("hbOrgName"));
        model.put("hbOrgStatus", req.getParameter("hbOrgStatus"));
        model.put("page", page);
        return new ModelAndView("/hb/list",model);
    }

    /*
     * add页面
     */
    @RequestMapping(value = "/hbOrgListToAdd")
    public ModelAndView hbOrgListToAdd(HttpServletRequest req, HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();
        Map model = new HashMap();
        return new ModelAndView("/hb/add",model);
    }
    /*
     * add页面
     */
    @RequestMapping(value = "/hbOrgListToEdit")
    public ModelAndView hbOrgListToEdit(HttpServletRequest req, HttpServletResponse rsp){
        Map<String, Object> paramMap = new HashMap<>();


        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<HbOrgInfoDto>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.QUERY_HB_ORG_BYID, null, req);
        HbOrgInfoDto hbOrgInfoDto = (HbOrgInfoDto) apiFinalResponse1.getResults();

        Map model = new HashMap();
        model.put("hbOrgInfoDto",hbOrgInfoDto);
        return new ModelAndView("/hb/edit",model);
    }
    /*
     * 新增或修改数据
     */
    @RequestMapping(value = "/hbOrgInfoUpdate")
    public String hbOrgInfoUpdate(HttpServletRequest req, HttpServletResponse rsp) {
        if (StringUtils.isEmpty(req.getParameter("id"))) {
            //新增数据
            return this.callApiAndOutput(BackendApiMethodEnum.SAVE_HB_ORG, null, req, rsp);
        } else {
            //修改数据
            return this.callApiAndOutput(BackendApiMethodEnum.EDIT_HB_ORG, null, req, rsp);
        }

    }

    @RequestMapping(value = "/hbOrgDelete")
    public String hbOrgDelete(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.DELETE_HB_ORG, null, req, rsp);
    }

    @RequestMapping(value = "/hbOrgDetails")
    public ModelAndView hbOrgDetails(HttpServletRequest req, HttpServletResponse rsp) {

        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<HbOrgInfoDto>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.QUERY_HB_ORG_BYID, null, req);
        HbOrgInfoDto hbOrgInfoDto = (HbOrgInfoDto) apiFinalResponse1.getResults();

        Map model = new HashMap();
        model.put("hbOrgInfoDto",hbOrgInfoDto);
        model.put("id",req.getParameter("id"));
        return new ModelAndView("/hb/info",model);
    }

    @RequestMapping(value = "/hbUserList")
    public ModelAndView hbUserList(HttpServletRequest req, HttpServletResponse rsp, ModelAndView modelAndView) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        String page = req.getParameter("page");
        if (org.apache.commons.lang3.StringUtils.isEmpty(page)){
            page="0";
        }
        //获取职级数据list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<HbUserInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_HB_USER_LIST, null, req);
       // List<HbUserInfoDto> hbUserInfoDtos = (List<HbUserInfoDto>) apiFinalResponse1.getResults();

        Map model = new HashMap();
        model.put("apiRsp", apiFinalResponse1);
        model.put("page", page);
        return new ModelAndView("/hb/hbUser/list",model);
    }
    @RequestMapping(value = "/hbUserDelete")
    public String hbUserDelete(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.DELETE_HB_USER, null, req, rsp);
    }
    @RequestMapping(value = "/hbReCordList")
    public ModelAndView hbReCordList(HttpServletRequest req, HttpServletResponse rsp, ModelAndView modelAndView) {
        Map<String, Object> appendMap = new HashMap<String, Object>();

        String hbOrgId = req.getParameter("hbOrgId");
        String hbUserName = req.getParameter("hbUserName");
        String productName = req.getParameter("productName");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String page =  req.getParameter("page");
        String pageIndex =  req.getParameter("pageIndex");

        //获取核保记录list
        TypeToken typeToken1 = new TypeToken<ApiFinalResponse<List<HbRecordDto>>>() {};
        ApiFinalResponse apiFinalResponse1= this.callApi(typeToken1, BackendApiMethodEnum.BACKEND_HB_RECORD_LIST, appendMap, req);

        TypeToken typeToken2 = new TypeToken<ApiFinalResponse<List<HbOrgInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse2= this.callApi(typeToken2, BackendApiMethodEnum.BACKEND_HB_ORG_LIST, appendMap, req);
        //List<HbOrgInfoDto> hbOrgInfoDtos = (List<HbOrgInfoDto>) apiFinalResponse1.getResults();
        Map model = new HashMap();

        model.put("hbOrgId", hbOrgId==null?"":hbOrgId);
        model.put("hbUserName", hbUserName==null?"":hbUserName);
        model.put("productName", productName==null?"":productName);
        model.put("startDate", startDate==null?"":startDate);
        model.put("endDate", endDate==null?"":endDate);
        model.put("apiRsp", apiFinalResponse1);
        model.put("apiFinalResponse2", apiFinalResponse2);
        return new ModelAndView("/hb/hbRecordList",model);
    }

}
