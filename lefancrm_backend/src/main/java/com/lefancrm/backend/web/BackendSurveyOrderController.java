package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.SurveyQaDto;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangwei on 2019-01-09.
 * 订单
 */
@Controller
@RequestMapping(value = "/surveyOrder")
public class BackendSurveyOrderController extends BackendBaseController{


    /**
    *   确认发货
     */
    @RequestMapping(value = "/deliveryPro")
    public ModelAndView deliveryPro(HttpServletRequest req, HttpServletResponse rsp) {
        Map model = new HashMap();
        model.put("id",req.getParameter("id"));
        model.put("surveyCode",req.getParameter("surveyCode"));
        model.put("btnCode",req.getParameter("btnCode"));
        return new ModelAndView("/survey/surveyOrder/deliveryPro",model);
    }
}
