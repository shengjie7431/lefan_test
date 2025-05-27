package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.CaseEntrustInputDto;
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

/**
 * Created by wangwei on 2019-05-08.
 * 新增案件
 */
@Controller
@RequestMapping(value = "/caseEntrustInput")
public class BackendCaseEntrustInputController extends BackendBaseController{

    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse rsp) {
        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<CaseEntrustInputDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_ENTRUST_INPUT_LIST, null, req);
        req.setAttribute("apiRsp", apiFinalResponse);

        Map model = new HashMap();
        String injuredPerson = req.getParameter("injuredPerson");
        model.put("injuredPerson", injuredPerson);
        String injuredTel = req.getParameter("injuredTel");
        model.put("injuredTel", injuredTel);
        String agentType = req.getParameter("agentType");
        model.put("agentType", agentType);
        String checkState = req.getParameter("checkState");
        model.put("checkState", checkState);
        return new ModelAndView("/case/entrust/list",model);
    }


    @RequestMapping(value = "/info")
    public ModelAndView info(HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("id", req.getParameter("id"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<CaseEntrustInputDto>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken, BackendApiMethodEnum.BACKEND_CASE_ENTRUST_INPUT_INFO, appendMap, null);
        model.put("caseEntrustInput", apiFinalResponse.getResults());
        return new ModelAndView("/case/entrust/info", model);
    }

    /**
     * 数据处理
     */
    @RequestMapping(value = "/operate")
    public String operate(HttpServletRequest req, HttpServletResponse rsp) {
        String surveyCode = req.getParameter("surveyCode");
        Map<String, Object> appendMap = new HashMap<String, Object>();
        appendMap.put("surveyCode", surveyCode);//code标识
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_CASE_ENTRUST_INPUT_OPERATE, appendMap, req, rsp);
    }

    /**
     * 操作-弹窗页面
     */
    @RequestMapping(value = "/popup")
    public ModelAndView popup(HttpServletRequest req, HttpServletResponse rsp) {
        String btnCode = req.getParameter("btnCode");
        String id = req.getParameter("id");
        Map model = new HashMap();
        model.put("btnCode", btnCode);//code标识
        model.put("id", id);
        Map<String, Object> appendMap = new HashMap<String, Object>();
        //驳回页面
        if(("1100".equals(btnCode))){
            return new ModelAndView("/case/entrust/reson", model);
        }
        return null;
    }
}
