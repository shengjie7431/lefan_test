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
 * Created by wangwei on 2018/12/28.
 * 平台QA问题
 */
@Controller
@RequestMapping(value = "/surveyQa")
public class BackendSurveyQaController extends BackendBaseController{


    /**
    *   回复问题
     */
    @RequestMapping(value = "/answer")
    public ModelAndView answer(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        Map model = new HashMap();
        appendMap.put("id", req.getParameter("id"));
        appendMap.put("surveyCode", req.getParameter("surveyCode"));
        TypeToken typeToken = new TypeToken<ApiFinalResponse<SurveyQaDto>>() {};
        ApiFinalResponse apiFinalResponse = this.callApi(typeToken,BackendApiMethodEnum.BACKEND_SURVEY_INFO, appendMap, null);

        model.put("surveyQa",apiFinalResponse.getResults());
        model.put("surveyCode",req.getParameter("surveyCode"));
        model.put("btnCode",req.getParameter("btnCode"));
        return new ModelAndView("/survey/surveyQa/answer",model);
    }
}
