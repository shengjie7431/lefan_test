package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.SurveyConsignerDto;
import com.lefancrm.backend.dto.SurveyLevelDto;
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
 * Created by wangwei on 2018/12/17.
 * 委托人认证
 */
@Controller
@RequestMapping(value = "/surveyConsigner")
public class BackendSurveyConsignerController extends BackendBaseController{

    /**
     * 选择 委托方机构
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/credit")
    public ModelAndView credit(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();
        String id = req.getParameter("id");
        String btnCode = req.getParameter("btnCode");
        String surveyCode = req.getParameter("surveyCode");
        model.put("id",id);
        model.put("surveyCode",surveyCode);
        model.put("btnCode",btnCode);

        return new ModelAndView("/survey/surveyConsigner/credit",model);
    }
}
