package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.SurveyTaskInfoDto;
import com.lefancrm.base.dto.ApiFinalResponse;
import com.lefancrm.base.enums.BackendApiMethodEnum;
import com.lefancrm.base.web.WebHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwei on 2018/12/17.
 * 任务类型表
 */
@Controller
@RequestMapping(value = "/surveyTaskInfo")
public class BackendSurveyTaskInfoController extends BackendBaseController{

    /**
     * 选择任务类型
     * @param req
     * @param rsp
     * @return
     */
    @RequestMapping("/selectTaskInfo")
    public ModelAndView selectTaskInfo(HttpServletRequest req, HttpServletResponse rsp){
        Map model = new HashMap();

        TypeToken typeToken = new TypeToken<ApiFinalResponse<List<SurveyTaskInfoDto>>>() {};
        ApiFinalResponse apiFinalResponse= this.callApi(typeToken, BackendApiMethodEnum.BACKEND_SURVEY_LIST, null, req);
        model.put("apiRsp",apiFinalResponse);
        String name = req.getParameter("name");
        model.put("name",name==null?"":name);
        model.put("surveyCode",req.getParameter("surveyCode"));
        return new ModelAndView("/survey/surveyTaskInfo/taskInfo",model);
    }

    /**
     * 确定任务类型
     *
     */
    @RequestMapping(value = "/choiceTaskInfo")
    public String choiceTaskInfo(HttpServletRequest req, HttpServletResponse rsp) {
        Map param = new HashMap();
        param.put("surveyCode",req.getParameter("surveyCode"));
        String retJson = "";
        String json = this.callApi(BackendApiMethodEnum.BACKEND_SURVEY_INFO, param, req);
        req.getSession().setAttribute("json",json);
        return WebHelper.outputJson(json, rsp);
    }
}
