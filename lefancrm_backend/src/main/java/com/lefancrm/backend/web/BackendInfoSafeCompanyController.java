package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.*;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2018/11/28.
 * 保险公司
 */
@Controller
@RequestMapping(value = "/infoSafeCompany")
public class BackendInfoSafeCompanyController extends BackendBaseController{

    /**
     * 保险公司列表
     *
    */
    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp) {
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<InfoSafeCompanyDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_INFO_SAFE_COMPANY_LIST, null, req);
        Map model = new HashMap();
        String safeName = req.getParameter("safeName");
        String safeTel = req.getParameter("safeTel");
        String safeUser = req.getParameter("safeUser");
        model.put("safeName", safeName);
        model.put("safeTel", safeTel);
        model.put("safeUser", safeUser);

        req.setAttribute("apiRsp", apiFinalResponse);
        return new ModelAndView("/info/safeCompany/list",model);
    }

    /**
    * 新增、修改页面
    */
    @RequestMapping(value = "/edit")
    public ModelAndView edit(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("id", req.getParameter("id"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<InfoSafeCompanyDto>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_INFO_SAFE_COMPANY_EDIT, appendMap, null);
        Map model = new HashMap();

        model.put("infoSafeCompany",apiFinalResponse.getResults());
        return new ModelAndView("/info/safeCompany/edit",model);
    }

    /**
     * 详情页面
     */
    @RequestMapping(value = "/info")
    public ModelAndView info(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("id", req.getParameter("id"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<InfoSafeCompanyDto>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_INFO_SAFE_COMPANY_EDIT, appendMap, null);
        Map model = new HashMap();
        model.put("infoSafeCompany",apiFinalResponse.getResults());
        return new ModelAndView("/info/safeCompany/info",model);
    }

    /**
    * 新增或修改数据
    */
    @RequestMapping(value = "/save")
     public String save(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_INFO_SAFE_COMPANY_SAVE, null, req, rsp);
    }

    /**
     * 分配用户页面
     */
    @RequestMapping(value = "/selectAllUser")
    public ModelAndView selectAllUser(HttpServletRequest req, HttpServletResponse rsp) {
        String safeCompanyId=req.getParameter("safeCompanyId");
        String userName = req.getParameter("userName");
        String userTel = req.getParameter("userTel");
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<UserInfo>>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SELECT_ALL_USER_INFO_LIST, null, req);

        Map model = new HashMap();
        model.put("apiRsp", apiFinalResponse);
        model.put("safeCompanyId",safeCompanyId);
        model.put("userName",userName);
        model.put("userTel",userTel);
        return new ModelAndView("/info/safeCompany/allUser",model);
    }

    /**
     * 确认分配用户
     */
    @RequestMapping(value = "/confirmUser")
    public String confirmUser(HttpServletRequest req, HttpServletResponse rsp, String users) {
        Map param = new HashMap();
        param.put("safeCompanyId",req.getParameter("safeCompanyId"));
        param.put("users",users);
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CONFIRM_USER_INFO, param, req, rsp);
    }


    /**
     * 查看保险公司名下用户
     */
    @RequestMapping(value = "/companyUser")
    public ModelAndView companyUser(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("safeCompanyId", req.getParameter("safeCompanyId"));

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<UserInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SELECT_COMPANY_USER_INFO_LIST, appendMap, req);
        String safeCompanyId = req.getParameter("safeCompanyId");
        String userName = req.getParameter("userName");
        String userTel = req.getParameter("userTel");
        Map model = new HashMap();
        model.put("apiRsp", apiFinalResponse);
        model.put("safeCompanyId",safeCompanyId);
        model.put("userName",userName);
        model.put("userTel",userTel);
        return new ModelAndView("/info/safeCompany/companyUser",model);
    }

    /**
     * 新增或修改数据
     */
    @RequestMapping(value = "/remove")
    public String remove(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_INFO_SAFE_USER_REMOVE, null, req, rsp);
    }

    /**
     * 删除数据
     */
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest req, HttpServletResponse rsp) {
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_INFO_SAFE_COMPANY_DELETE, null, req, rsp);
    }
}
