package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.SurveyConsignerDto;
import com.lefancrm.backend.dto.SurveyConsignorDepartmentDto;
import com.lefancrm.backend.dto.SurveyConsignorDto;
import com.lefancrm.backend.dto.UserInfo;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.web.WebHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2018/12/17.
 * 委托人机构
 */
@Controller
@RequestMapping(value = "/surveyConsignor")
public class BackendSurveyConsignorController extends BackendBaseController{

    /**
     * 选择 委托方机构
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/selectConsignor")
    public ModelAndView selectConsignor(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
        model.put("apiRsp",apiFinalResponse);
        String name = req.getParameter("name");
        model.put("name",name==null?"":name);
        model.put("surveyCode",req.getParameter("surveyCode"));
        model.put("type",req.getParameter("type"));
        return new ModelAndView("/survey/surveyConsignor/consignor",model);
    }

    /**
     * 确定 委托方机构
     *
     */
    @RequestMapping(value = "/choiceConsignor")
    public String choiceConsignor(HttpServletRequest req, HttpServletResponse rsp) {
        Map param = new HashMap();
        param.put("surveyCode",req.getParameter("surveyCode"));
        param.put("type",req.getParameter("type"));
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_SURVEY_INFO, param, req);
        req.getSession().setAttribute("json",json);
        return WebHelper.outputJson(json, rsp);
    }

    /**
     * 获取 符合条件的委托人
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/selectConsigner")
    public ModelAndView selectConsigner(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        String btnCode = req.getParameter("btnCode");

        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("btnCode", btnCode);

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignerDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_CONSIGNER, appendMap, req);
        model.put("apiRsp",apiFinalResponse);
        String userName = req.getParameter("userName");
        String tel = req.getParameter("tel");
        model.put("userName",userName==null?"":userName);
        model.put("tel",tel==null?"":tel);
        model.put("consignorId",req.getParameter("id"));
        model.put("btnCode",btnCode);

        String departmentId = req.getParameter("departmentId");
        model.put("departmentId", departmentId);

        //该机构名下部门
        appendMap.clear();
        appendMap.put("consignorId", req.getParameter("id"));
        appendMap.put("menuType",1);//不分页
        appendMap.put("surveyCode","consignor");
        appendMap.put("btnCode",1000);
//        typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignorDepartmentDto>>>() {};
//        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req);
        typeToken = new TypeToken<ApiFinalResponse<Map<String,Object>>>() {};
        apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_INFO_BY_RELATION_ID, appendMap, req);
        Map map = (Map)apiFinalResponse.getResults();
        List<SurveyConsignorDepartmentDto> list = (List<SurveyConsignorDepartmentDto>)map.get("departmentList");
        model.put("departments",list);

        return new ModelAndView("/survey/surveyConsignor/consignerList",model);
    }


    @RequestMapping(value = "/selectConsignerByOrgId")
    public String selectConsignerByOrgId(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        Long consignorId = Long.parseLong(req.getParameter("consignorId"));
        appendMap.put("consignorId",consignorId);
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_CONSIGNER_BY_ORG, appendMap, req, rsp);
    }

//    @ResponseBody
//    @RequestMapping(value = "/selectConsignerByOrgId")
//    public List<SurveyConsignerDto> selectConsignerByOrgId(HttpServletRequest req, HttpServletResponse rsp) {
//        Map<String, Object> appendMap = new HashMap<String, Object>();
//        Long consignorId = Long.parseLong(req.getParameter("consignorId"));
//        appendMap.put("consignorId",consignorId);
//        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyConsignerDto>>>() {};
//        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_CONSIGNER_BY_ORG, appendMap, req);
//        List<SurveyConsignerDto> consigners = (List<SurveyConsignerDto>)apiFinalResponse.getResults();
//        return consigners;
//    }

    /**
     * 获取可加入的人员
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/selectUserInfo")
    public ModelAndView selectUserInfo(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        model.put("pageSize",req.getParameter("pageSize"));
        String btnCode = req.getParameter("btnCode");
        String surveyCode = req.getParameter("surveyCode");
        String userName = req.getParameter("userName");
        String userTel = req.getParameter("userTel");
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("btnCode", btnCode);
        appendMap.put("surveyCode", surveyCode);
        appendMap.put("userName", userName);
        appendMap.put("userTel", userTel);
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<UserInfo>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_SELECT_USER_INFO, appendMap, req);
        model.put("apiRsp",apiFinalResponse);

        model.put("userName",userName==null?"":userName);
        model.put("userTel",userTel==null?"":userTel);
        model.put("consignorId",req.getParameter("id"));
        model.put("btnCode",btnCode);
        model.put("surveyCode",surveyCode);
        return new ModelAndView("/survey/surveyConsignor/userList",model);
    }


    /**
     * 认证页面
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/entrust")
    public ModelAndView entrust(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();

        model.put("userId",req.getParameter("userId"));
        model.put("userName",req.getParameter("userName"));
        model.put("btnCode",req.getParameter("btnCode"));
        model.put("surveyCode",req.getParameter("surveyCode"));
        model.put("consignorId",req.getParameter("consignorId"));
        //委托机构信息
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode","consignor");
        appendMap.put("id", req.getParameter("consignorId"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyConsignorDto>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
        model.put("surveyConsignor",apiFinalResponse.getResults());

        //委托员信息（如二次认证，需将之前的数据带出）
        appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode","consignerUser");
        appendMap.put("userId", req.getParameter("userId"));
        typeToken = new TypeToken<ApiFinalResponse<SurveyConsignerDto>>() {};
        apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);
        model.put("surveyConsigner",apiFinalResponse.getResults());

        return new ModelAndView("/survey/surveyConsignor/entrust",model);
    }


}
