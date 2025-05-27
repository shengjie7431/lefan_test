package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.BusinessRoleDto;
import com.lefancrm.backend.dto.SurveyLevelDto;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.web.WebHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2019-01-14.
 * 商品
 */
@Controller
@RequestMapping(value = "/surveyProduct")
public class BackendSurveyProductController extends BackendBaseController{


    /**
     * 选择调查员级别（使用场景：1、商品新增页面使用）
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/selectLevel")
    public ModelAndView selectLevel(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyLevelDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
        model.put("apiRsp",apiFinalResponse);
        String name = req.getParameter("name");
        model.put("name",name==null?"":name);
        model.put("surveyCode",req.getParameter("surveyCode"));
        return new ModelAndView("/survey/surveyProduct/level",model);
    }

    /**
     * 确定调查员级别（使用场景：1、商品新增页面使用）
     *
     */
    @RequestMapping(value = "/choiceLevel")
    public String choiceLevel(HttpServletRequest req, HttpServletResponse rsp, String levels) {
        Map param = new HashMap();
        param.put("surveyCode",req.getParameter("surveyCode"));
        param.put("levels",levels);
        String retJson = "";
        String json = levels;
        req.getSession().setAttribute("json",json);
        return WebHelper.outputJson(json, rsp);
    }


    /**
     * 选择角色（使用场景：1、商品新增页面使用）
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/selectRole")
    public ModelAndView selectRole(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<BusinessRoleDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_BUEINESS_ROLE_LIST, null, req);
        model.put("apiRsp",apiFinalResponse);
        String roleName = req.getParameter("roleName");
        model.put("roleName",roleName==null?"":roleName);
        model.put("surveyCode",req.getParameter("surveyCode"));
        return new ModelAndView("/survey/surveyProduct/role",model);
    }
}
