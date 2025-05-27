package com.lefancrm.backend.web;

import com.google.gson.reflect.TypeToken;
import com.lefancrm.backend.dto.SurveyInvestigatorDto;
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
 * Created by wangwei on 2018/12/19.
 * 调查方认证
 */
@Controller
@RequestMapping(value = "/surveyInvestigator")
public class BackendSurveyInvestigatorController extends BackendBaseController{

    @RequestMapping(value = "/selectInvestigatorByOrgId")
    public String selectInvestigatorByOrgId(HttpServletRequest req, HttpServletResponse rsp) {
        Map<String, Object> appendMap = new HashMap<String, Object>();
        Long orgId = Long.parseLong(req.getParameter("orgId"));
        appendMap.put("orgId",orgId);
        return this.callApiAndOutput(BackendApiMethodEnum.BACKEND_SURVEY_INVESTIGATOR_BY_ORG, appendMap, req, rsp);
    }

}
